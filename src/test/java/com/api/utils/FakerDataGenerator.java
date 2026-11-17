package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddesss;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
//Utility class
	private final static String COUNTRY = "India";
	private static Faker faker = new Faker(new Locale("en-IND"));
	private final static Random RANDOM = new Random();
	private final static int MST_SERVICE_LOCATION_ID = 0;
	private final static int MST_PLATFORM_ID = 2;
	private final static int MST_WARRANTY_STATUS_ID = 1;
	private final static int MST_OEM_ID = 1;
	private final static int PRODUCT_ID = 1;
	private final static int MST_MODEL_ID = 1;
	
	private final static int validProblemsId[]= {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};

	private FakerDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {
		Customer customer = generateFakeCustomerData();
		CustomerAddesss customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeProblemsList();
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
		return payload;
	}
	
	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		
		List<CreateJobPayload> payLoadList= new ArrayList<CreateJobPayload>();
		for(int i=0;i<=count;i++) {
		Customer customer = generateFakeCustomerData();
		CustomerAddesss customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeProblemsList();
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
		payLoadList.add(payload);
		}
		return payLoadList.iterator();
	}

	private static List<Problems> generateFakeProblemsList() {
		int count=RANDOM.nextInt(3)+1;
		String fakeRemark;
		int randomIndex;
		Problems problems;
		List<Problems> problemList = new ArrayList<Problems>();
		for(int i=1;i<=count;i++) {
		fakeRemark = faker.lorem().sentence(5);
		randomIndex = RANDOM.nextInt(validProblemsId.length);
		problems = new Problems(validProblemsId[randomIndex], fakeRemark);
		problemList.add(problems);
		}
		return problemList;
	}

	private static Customer generateFakeCustomerData() {
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("878#######");
		String alternateMobileNumber = faker.numerify("878#######");
		String customerEmailAddress = faker.internet().emailAddress();
		String altCustomerEmailAddress = faker.internet().emailAddress();

		Customer customer = new Customer(fname, lname, mobileNumber, alternateMobileNumber, customerEmailAddress,
				altCustomerEmailAddress);
		return customer;
	}

	private static CustomerAddesss generateFakeCustomerAddressData() {
		String flatNumber = faker.numerify("###");
		String apartmentNumber = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pinCode = faker.numerify("#####");

		String state = faker.address().state();

		CustomerAddesss customerAddress = new CustomerAddesss(flatNumber, apartmentNumber, streetName, landmark, area,
				pinCode, COUNTRY, state);

		return customerAddress;
	}

	private static CustomerProduct generateFakeCustomerProductData() {
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, PRODUCT_ID, MST_MODEL_ID);
		return customerProduct;
	}
}
