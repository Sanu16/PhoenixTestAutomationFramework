package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVReaderUtil;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginApiDataProvider",parallel = true)
	public static Iterator<UserBean> loginApiDataProvider() {
		return CSVReaderUtil.loadCsv("testData/LoginCreds.csv");
	}
	//Data provider needs to return something 
	// 1. []
	// 2.[][]
	//Iterator<>
	
}
