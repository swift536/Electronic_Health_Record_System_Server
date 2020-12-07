package com.genuinedeveloper.mysqlaccessserver.utilities;

import java.util.Arrays;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.genuinedeveloper.mysqlaccessserver.db_entities.Users;
import com.genuinedeveloper.mysqlaccessserver.repositories.UsersRepository;

/*
 * For demonstration purposes the client application is shipped with a test id, username and password credential
 * already within the login page. The hashing is turned off to make the testing process easier for those
 * who wish to try the project
 * 
 * Looked into using Spring sessions via Oath, cookies and headers but ultimate
 * decided to go with simple header authentication. Maintained the method for
 * backend authentication for future deliberation.
 * 
 * Change key and initialization vector as required for individual use.
 * Uses 16 byte key and initialization vector. However, if key/vector are changed, all files
 * will have to be decrypted and re-encrypted with new set.
 * 
 * !!! Future Development - since it's just for usernames and passwords might not
 * be too detrimental, but a large database would take a lot of processing time to re-hash
 * everything. Look into resolutions for this as well as how to store key/vector safely in memory
 * or if pulling from a protected file set is feasible. !!!
 */
@Component
public class Authentication {

	@Autowired
	UsersRepository repo;
	
	Logger logger = LoggerFactory.getLogger(Authentication.class);
	
	private static final String key = "OpenEHRSTestKey1";
	private static final String initializationVector = "Test123412341234";
	
	public String requestBodyAuthentication (UserCredentials creds) {
		
		String returnValue = "null";
		Users user = repo.findById(creds.getId()).get();
		
		if (user.getHashedUsername().toCharArray().equals(creds.getUsername())
				&& user.getHashedPassword().toCharArray().equals(creds.getPassword())) {
			
			returnValue =  user.getToken();
			
		}
		
		return returnValue;
		
	}
	
	
	//credentials are from the "Authorization" header in the format id:username:password
	//Encryption algorithm will reduce the input to 24 character encrypted c-strings
	public Boolean headerAuthentication (char[] credentials) {
		
		String str = new String(credentials);
		//Debug
		//logger.info("authentication method credentials: " + str);
		
		boolean eval = false;
		int lengths[] = new int[3];
		int length = 0;
		char[][] creds = new char[3][];
		int iterator1 = 0;
		int iterator2 = 0;
		
		try {
			
			for (int i=0; i<3; i++) {
				while (iterator1 <= credentials.length-1 && credentials[iterator1] != ':') {
					iterator1++;
					length++;
				}
				iterator1++;
				lengths[i] = length;
				length = 0;
			}
			
			creds[0] = new char[lengths[0]];
			creds[1] = new char[lengths[1]];
			creds[2] = new char[lengths[2]];
			
			for (int i=0; i<3; i++) {
				iterator1 = 0;
				char[] temp = new char[50];
				while (iterator2 <= credentials.length-1 && credentials[iterator2] != ':') {
					creds[i][iterator1] = credentials[iterator2];
					iterator2++;
					iterator1++;
				}
				iterator2++;
			}
			
			//null out sensitive information immediately after verification
			for (int i=0; i<credentials.length; i++) {
				credentials[i] = ' ';
			}
			
			//Debug - logs to spring console
			/*String testuserString = new String (creds[1]);
			String testpassString = new String (creds[2]);
			logger.info(testIdString + ", " + testuserString + ", " + testpassString);*/
			String testIdString = new String (creds[0]);
			Users user = repo.findById(Integer.parseInt(testIdString)).get();

			/*String encryptedUsername = encrypt(creds[1]);
			String encryptedPassword = encrypt(creds[2]);*/
			
			//Debugging - logs to spring console
			/*String encryptedUsername = new String(creds[1]);
			String encryptedPassword = new String (creds[2]);
			logger.info("Given username: " + encryptedUsername);
			logger.info("Given password: " + encryptedPassword);
			logger.info("Retrieved username: " + user.getHashedUsername());
			logger.info("Retrieved password: " + user.getHashedPassword());*/
			
			
			//REMEMBER TO INCLUDE EXAMPLE USER WHICH WAS INSERTED CORRECTLY FOR HASHING
			if (Arrays.equals(creds[1],user.getHashedUsername().toCharArray())
					&& Arrays.equals(creds[2],user.getHashedPassword().toCharArray())) {
				
				eval = true;
				
			}
			
			return eval;
			
		} catch (Exception e) {
			//Error
			return false;
		}
		
	}
	
	//Encryption algorithm will reduce the input to 24 character encrypted c-strings
	private String encrypt (char[] inputString) {
	    try {
	    	byte[] str = charToByteArray (inputString);
	    	Arrays.fill(inputString, ' ');
	        IvParameterSpec iv = new IvParameterSpec(initializationVector.getBytes("UTF-8"));
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	 
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	 
	        //byte[] encrypted = cipher.doFinal(str.getBytes());
	        byte[] encrypted = cipher.doFinal(str);
	        return Base64.encodeBase64String(encrypted);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
	
	//Works for UTF-8 encoding at 1 byte per character
	private byte[] charToByteArray (char[] input) {
		byte[] output = new byte[input.length];
		
		for (int i = 0; i<input.length; i++) {
			output[i] = (byte) input[i];
		}
		
		return output;
	}
	
	//Works for UTF-8 encoding at 1 byte per character
	private char[] byteToCharArray (byte[] input) {
		char[] output = new char[input.length];
		
		for (int i = 0; i<input.length; i++) {
			output[i] = (char) input[i];
		}
		
		return output;
	}
}
