package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	/*
	 * constructor will be private Static- Static method Job- Help me to read the
	 * CSV file and map it to bean
	 * 
	 */
	private CSVReaderUtil() {
		// we have made the constructor as private so that no can make the object of CSV
		// out side the class
		// SingleTon class constructors are private
	}

	public static void loadCsv(String pathOfCSVFile) {
		// Read CSV file in JAVA
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr); // CSV Reader constructor requires a Reader

		// Write the code to map the csv to POJO
		// helps us to convert csv to pojo
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();

		List<UserBean> userList = csvToBean.parse();
		// System.out.println(userList.get(0).getUsername());
		System.out.println(userList);

	}
}
