package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddesss;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class CreateJobAPITest {
	

	
	
	
	@Test
	public void createJobAPITest() {
		
		//Creating create job payload Object
		
		Customer customer= new Customer("Sanu", "Anand", "7059787222", "", "xyz@gmail.com", "");
		CustomerAddesss customerAddress = new CustomerAddesss("402", "CNR nest", "zsanu street","raja shree","Marathali", "560037", "India", "Karnataka");
		CustomerProduct customerProduct= new CustomerProduct(getTimeWithDaysAgo(10), "16712811579133", "16712811579133", "16712811579133", getTimeWithDaysAgo(10), 1, 1);
		Problems problems= new Problems(1, "Battery issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
