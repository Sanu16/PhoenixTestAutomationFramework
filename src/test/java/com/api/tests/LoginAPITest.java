package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import static com.api.utils.SpecUtil.*;

public class LoginAPITest {
	private UserCredentials userCredentila;
	
	@BeforeMethod(description = "Create the payload for the login API")
	public void setUp() {
		userCredentila= new UserCredentials("iamfd", "password");	
	}
	
@Test(description = "Verify if login api is working for FD user", groups ={"api","regressio","smoke"})
public void loginApiTest() throws IOException {
	
		//Read the property value that is goiong to be passed from the terminal
	
		//System.out.println(System.getProperty("env"));
	
		//Rest Assured Code	
		
		given()
			.spec(requestSpec(userCredentila))
			
		.when()
			.post("login")
		.then()
			.spec(responseSpec_OK())
		.and()
			.body("message", equalTo("Success"))
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
			
	}
}
