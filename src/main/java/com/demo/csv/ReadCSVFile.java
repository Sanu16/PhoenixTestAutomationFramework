package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
		// Read CSV file in JAVA
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
				
		/*
		 * File csvfile = new File(
		 * "C:\\Users\\sanua\\OneDrive\\Desktop\\SDET With Jatin\\Java Module\\PhoenixTestAutomationFramework\\src\\main\\resources\\testData\\LoginCreds.csv"
		 * ); FileReader fr = new FileReader(csvfile);
		 */
		
		InputStreamReader isr= new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr); // CSV Reader constructor requires a Reader

		List<String[]> dataList = csvReader.readAll();

		for (String[] dataArray : dataList) {
			for (String data : dataArray) {
				System.out.print(data + " ");
				
			}
			System.out.println("");
		}

	}

}
