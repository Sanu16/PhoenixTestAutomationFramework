package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;

import com.api.request.model.CreateJobPayload;
import com.api.tests.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Iterator<CreateJobBean> iterator=CSVReaderUtil.loadCsv("testData/CreateJobData.csv", CreateJobBean.class);
		ArrayList<CreateJobPayload> payLoadList = new ArrayList<CreateJobPayload>();
		while(iterator.hasNext()) {
			CreateJobBean c= iterator.next();
			CreateJobPayload payload =CreateJobBeanMapper.mapper(c);
			System.out.println(payload);
			payLoadList.add(payload);
		}
	
	}

}
