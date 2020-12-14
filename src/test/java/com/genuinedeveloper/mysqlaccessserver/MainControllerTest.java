package com.genuinedeveloper.mysqlaccessserver;

/*@RunWith(SpringRunner.class)
//@WebMvcTest(MainController.class)
@AutoConfigureMockMvc
@SpringBootTest
class MainControllerTest {

	@MockBean
	PatientsRepository patientsRepository;
	@MockBean
	PatientsRepository usersRepository;
	@MockBean
	PatientsRepository allergiessRepository;
	@MockBean
	PatientsRepository surgeriessRepository;
	@MockBean
	PatientsRepository medicationssRepository;
	@MockBean
	PatientsRepository conditionsRepository;
	@MockBean
	PatientsRepository recordsRepository;
	
	@Autowired 
	MockMvc mvc;
	
	@Autowired
	MainController mc;
	
	@Test
	public void test() throws Exception {
		
        String response = this.mvc.perform(post("/users/add")
        		.contentType("application/json")
                .content(new Users("John", "Wick", null, "testusername","testpassword","sq","sq","sq","sa","sa","sa").toString()))
					.andReturn()
					.getResponse()
					.getContentAsString();
		//boolean bool = mc.addUser(new Users("John", "Wick", null, "testusername","testpassword","sq","sq","sq","sa","sa","sa"));
		
		//assert bool == true;
        
        response = "";
		
	}

}*/
