package com.api.tests;

import static io.restassured.RestAssured.*;


import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.utils.ConfigManager.*;

public class CountApiTest {
	@Test
	public void verifyCountAPIResponse() {
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.header("Authorization",AuthTokenProvider.getToken(Role.FD))
			.log().uri()
			.log().method()
			.log().headers()
		.when()
			.get("/dashboard/count")
		.then()
			.log().all()
			.statusCode(200)
			.body("message", equalTo("Success"))
			.time(lessThan(1000L))
			.body("data", notNullValue())
			.body("data.size()", equalTo(3))
			.body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.body("data.label", everyItem(not(blankOrNullString())))
			.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
		
		
	}
	@Test
	public void countApiTest_MissingAutToken() {
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.log().uri()
		.log().method()
		.log().headers()
	.when()
		.get("/dashboard/count")
	.then()
		.log().all()
		.statusCode(401);
		
	}

}
