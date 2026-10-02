package com.api.tests;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddesss;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateJobAPITest {
	

	
	
	
	@Test
	public void createJobAPITest() {
		
		//Creating create job payload Object
		Customer customer= new Customer("Sanu", "Anand", "7059787222", "", "xyz@gmail.com", "");
		CustomerAddesss customerAddress = new CustomerAddesss("402", "CNR nest", "zsanu street","raja shree","Marathali", "560037", "India", "Karnataka");
		CustomerProduct customerProduct= new CustomerProduct("2025-12-31T18:30:00.000Z", "46712812579148", "46712812579148", "46712812579148", "2025-12-31T18:30:00.000Z", 1, 1);
		Problems problems= new Problems(1, "Battery issue");
		Problems[] problemArray = new Problems[1];
		problemArray[0]=problems;
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemArray);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK());
		
	}

}
