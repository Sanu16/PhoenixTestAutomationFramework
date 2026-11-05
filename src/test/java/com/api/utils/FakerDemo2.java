package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddesss;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	private final static String COUNTRY = "India";

	public static void main(String[] args) {
		// I want to createFakeJobAPI request payLoad
		// I want to create fake Customer Object

		Faker faker = new Faker(new Locale("en-IND")); // It will help me create India specific fake data

		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("878#######");
		String alternateMobileNumber = faker.numerify("878#######");
		String customerEmailAddress = faker.internet().emailAddress();
		String altCustomerEmailAddress = faker.internet().emailAddress();

		Customer customer = new Customer(fname, lname, mobileNumber, alternateMobileNumber, customerEmailAddress,
				altCustomerEmailAddress);
		System.out.println(customer);

		String flatNumber = faker.numerify("###");
		String apartmentNumber = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pinCode = faker.numerify("#####");

		String state = faker.address().state();

		CustomerAddesss customerAddress = new CustomerAddesss(flatNumber, apartmentNumber, streetName, landmark, area,
				pinCode, COUNTRY, state);

		System.out.println(customerAddress);

		// CustomerObject fake object creation
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, 1, 1);
		
		System.out.println(customerProduct);
		
		String fakeRemark=faker.lorem().sentence(10);
		
		//I want to generate the random number from 1 to 27;
		Random random= new Random();
		int problemId=random.nextInt(26)+1;
		Problems problems = new Problems(problemId, fakeRemark);
		System.out.println(problems);
		
		
		List<Problems>	problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload payload= new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		System.out.println(payload);
		
	
	}

}
