package com.api.tests;

import static io.restassured.RestAssured.*;


import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
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
			.baseUri(getProperty("BASE_URI"))
		.and()
			.contentType(ContentType.JSON)
		.and()
			.accept(ContentType.JSON)
		.and()
			.body(userCredentila)
			.log().uri()
			.log().method()
			.log().headers()
			.log().body()
		.when()
			.post("login")
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1000L))
		.and()
			.body("message", equalTo("Success"))
		.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
			
	}
}
