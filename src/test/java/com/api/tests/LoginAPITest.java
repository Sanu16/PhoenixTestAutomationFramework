package com.api.tests;

import static io.restassured.RestAssured.*;


import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;

import static com.api.utils.ConfigManager.*;



import groovyjarjarantlr4.v4.runtime.atn.SemanticContext.AND;
import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
@Test
public void loginApiTest() throws IOException {
		//Read the property value that is goiong to be passed from the terminal
	
		System.out.println(System.getProperty("env"));
	
		//Rest Assured Code	
		UserCredentials userCredentila = new UserCredentials("iamfd", "password");
		given()
			.spec(SpecUtil.requestSpec(userCredentila))
			
		.when()
			.post("login")
		.then()
			.spec(SpecUtil.responseSpec_OK())
		.and()
			.body("message", equalTo("Success"))
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
			
	}
}
