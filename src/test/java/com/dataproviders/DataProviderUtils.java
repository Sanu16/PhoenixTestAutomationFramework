package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.tests.CreateJobBeanMapper;
import com.api.utils.CSVReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginApiDataProvider",parallel = true)
	public static Iterator<UserBean> loginApiDataProvider() {
		return CSVReaderUtil.loadCsv("testData/LoginCreds.csv",UserBean.class);
	}
	//Data provider needs to return something 
	// 1. []
	// 2.[][]
	//Iterator<>
	@DataProvider(name = "CreateJobAPIDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		
		Iterator<CreateJobBean> createJobBeanIterator =CSVReaderUtil.loadCsv("testData/CreateJobData.csv", CreateJobBean.class);
		List<CreateJobPayload>  payLoadList= new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		while(createJobBeanIterator.hasNext()) {
			tempBean=createJobBeanIterator.next();
			tempPayload=CreateJobBeanMapper.mapper(tempBean);
			payLoadList.add(tempPayload);
			
		}
		return payLoadList.iterator();
	}
	
	
	@DataProvider(name = "CreateJobAPIFakerDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> createJobFakeDataProvider() {
		Iterator<CreateJobPayload> payloadIterator =FakerDataGenerator.generateFakeCreateJobData(100);
		return payloadIterator;
	}
	
}
