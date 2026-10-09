package com.api.tests;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.Service_Location;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddesss;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.SpecUtil.*;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	private CreateJobPayload createJobPayload;
	@BeforeMethod(description = "Creating create job API request payload")
	public void setup() {
		Customer customer= new Customer("Sanu", "Anand", "7059787222", "", "xyz@gmail.com", "");
		CustomerAddesss customerAddress = new CustomerAddesss("402", "CNR nest", "zsanu street","raja shree","Marathali", "560037", "India", "Karnataka");
		CustomerProduct customerProduct= new CustomerProduct(getTimeWithDaysAgo(10), "18712811579133", "18712811579133", "18712811579133", getTimeWithDaysAgo(10),
				Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems= new Problems(Problem.SMART_PHONE_IS_RUNNING_SLOW.getCode(), "Battery issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		createJobPayload = new CreateJobPayload(Service_Location.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESk.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
			
	}
	
	@Test(description = "Verify if the CreateJob API is able to create inwarrant job",groups = {"api","smoke","regression"} )
	public void createJobAPITest() {
		
		//Creating create job payload Object

		given()
		.spec(requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
