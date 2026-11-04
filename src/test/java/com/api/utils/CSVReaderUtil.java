package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
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

	public static <T> Iterator<T> loadCsv(String pathOfCSVFile,Class<T> bean) {
		// Read CSV file in JAVA
		// Creating CSV reader object
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
		InputStreamReader isr = new InputStreamReader(is);
		CSVReader csvReader = new CSVReader(isr); // CSV Reader constructor requires a Reader

		// Write the code to map the csv to POJO
		// helps us to convert csv to pojo
		//Performing the mapping
		
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();
		//Parsing
		List<T> list = csvToBean.parse();
		return list.iterator();
		

	}
}
