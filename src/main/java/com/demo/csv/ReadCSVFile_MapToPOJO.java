//package com.demo.csv;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.List;
//
//import com.dataproviders.api.bean.UserBean;
//import com.opencsv.CSVReader;
//import com.opencsv.bean.CsvToBean;
//import com.opencsv.bean.CsvToBeanBuilder;
//import com.opencsv.exceptions.CsvException;
//
//public class ReadCSVFile_MapToPOJO {
//
//	public static void main(String[] args) throws IOException, CsvException {
//		// Read CSV file in JAVA
//		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv"); 
//		InputStreamReader isr = new InputStreamReader(is);
//		CSVReader csvReader = new CSVReader(isr); // CSV Reader constructor requires a Reader
//
//		// Write the code to map the csv to POJO
//		// helps us to convert csv to pojo
//		CsvToBean<UserBean> csvToBean= new CsvToBeanBuilder(csvReader)
//				.withType(UserBean.class)
//				.withIgnoreEmptyLine(true)
//				.build();
//		
//		List<UserBean> userList= csvToBean.parse();
//		//System.out.println(userList.get(0).getUsername());
//		System.out.println(userList);
//				
//
//	}
//
//}
