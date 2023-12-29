package com.mashreq.oa.service;

import java.util.List;

import com.mashreq.oa.entity.PaymentData;
import com.mashreq.oa.entity.PaymentSearchInput;


public interface PaymentDataService {
	
	//public List<Long> uploadExcel(MultipartFile file, String username, Integer mgmtCompId);

	public List<PaymentData> getListData();

	public List<PaymentData> getSearchList(PaymentSearchInput searchInput);
	
	//public List<Buildings> getBuildings(Integer mgmtCompId);

	//public List<PropertyGroups> getProperties(Integer mgmtCompId);

}
