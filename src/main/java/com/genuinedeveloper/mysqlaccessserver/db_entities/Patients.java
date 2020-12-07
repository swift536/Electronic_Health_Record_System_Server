package com.genuinedeveloper.mysqlaccessserver.db_entities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/*
 * Incorporated JSON ordering for faster parsing on client side.
 */
@Entity
@JsonPropertyOrder({ "id",
		"name_first",
		"name_middle",
		"name_last",
		"gender",
		"dob_date",
		"dob_time",
		"phone",
		"addressLine1",
		"addressLine2",
		"city",
		"state",
		"postalCode",
		"country",
		"image_url"})
public class Patients extends com.genuinedeveloper.mysqlaccessserver.db_entities.Entity{
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Integer id;
	  
	  private String name_first;
	  
	  private String name_middle;
	  
	  private String name_last;
	  
	  @Enumerated(EnumType.STRING)
	  private Gender gender;
	  
	  private Date dob_date;
	  
	  private Time dob_time;
	  
	  private String phone;
	  
	  private String addressLine1;
	  
	  private String addressLine2;
	  
	  private String city;
	  
	  private String state;
	  
	  private Integer postalCode;
	  
	  private String country;
	  
	  private String imageUrl;
	  
	  @Transient
	  private byte[] image;
	  
	  public enum Gender {
		  MALE,
		  FEMALE
	  }
	  
	  public Patients () {
	  }
	  
	  /*
	   * Begin Getters
	   */
	  @Column(name = "id")
	  public Integer getId() {
		  
	    return id;
	    
	  }
	  
	  @Column(name = "name_first")
	  public String getNameFirst() {
		  
		  return name_first;
		  
	  }
	  
	  @Column(name = "name_middle")
	  public String getNameMiddle() {
		  
		  return name_middle;
		  
	  }
	  
	  @Column(name = "name_last")
	  public String getNameLast() {
		  
		  return name_last;
		  
	  }
	  
	  @Column(name = "gender")
	  public Gender getGender () {
		  
		  return gender;
		  
	  }
	  
	  @Column(name = "dob_date")
	  public Date getDobDate () {
		  
		  return dob_date;
		  
	  }
	  
	  @Column(name = "dob_time")
	  public Time getDobTime () {
		  
		  return dob_time;
		  
	  }
	  
	  @Column(name = "phone")
	  public String getPhone() {
		  
		  return phone;
		  
	  }
	  
	  @Column(name = "addressLine1")
	  public String getAddressLine1() {
		  
		  return addressLine1;
		  
	  }
	  
	  @Column(name = "addressLine2")
	  public String getAddressLine2() {
		  
		  return addressLine2;
		  
	  }
	  
	  @Column(name = "city")
	  public String getCity() {
		  
		  return city;
		  
	  }
	  
	  @Column(name = "state")
	  public String getState() {
		  
		  return state;
		  
	  }
	  
	  @Column(name = "postalCode")
	  public Integer getPostalCode() {
		  
		  return postalCode;
		  
	  }
	  
	  @Column(name = "country")
	  public String getCountry() {
		  
		  return country;
		  
	  }
	  
	  @Column(name="image_url")
	  public String getImageUrl () {
		  
		  return imageUrl;
		  
	  }
	  
	  @Transient
	  public byte[] getImage () {
		  
		  return image;
		  
	  }
	  /*
	   * Begin Setters
	   */
	  public void setId(Integer id) {
		  
		  this.id = id;
		  
	  }
	  
	  public void setNameFirst (String name) {
		  
		  name_first = name;
		  
	  }
	  
	  public void setNameMiddle (String name) {
		  
		  name_middle = name;
		  
	  }
	  
	  public void setNameLast (String name) {
		  
		  name_last = name;
		  
	  }
	  
	  public void setGender (Gender gender) {
		  
		  this.gender = gender;
		  
	  }
	  
	  public void setDobDate (Date date) {
		  
		  dob_date = date;
		  
	  }
	  
	  public void setDobTime (Time time) {
		  
		  dob_time = time;
		  
	  }
	  
	  public void setPhone (String number) {
		  
		  phone = number;
		  
	  }
	  
	  public void setAddressLine1 (String address) {
		  
		  addressLine1 = address;
		  
	  }
	  
	  public void setAddressLine2 (String address) {
		  
		  addressLine2 = address;
		  
	  }
	  
	  public void setCity (String name) {
		  
		  city = name;
		  
	  }
	  
	  public void setState (String name) {
		  
		  state = name;
		  
	  }
	  
	  public void setPostalCode (Integer number) {
		  
		  postalCode = number;
		  
	  }
	  
	  public void setCountry (String name) {
		  
		  country = name;
		  
	  }
	  
	  public void setImageUrl (String url) {
		  
		  imageUrl = url;
		  
	  }
	  
	  
	  public void setImage (byte[] arr) throws IOException {
		  
		  image = arr;

	  }

}
