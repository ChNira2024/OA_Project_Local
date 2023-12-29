package com.mashreq.oa.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashreq.oa.entity.PaymentData;
import com.mashreq.oa.entity.PaymentSearchInput;
import com.mashreq.oa.service.PaymentDataService;


@RestController
@RequestMapping("/payment")
@CrossOrigin(origins="http://localhost:3000")
public class PaymentDataController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentDataController.class);
	@Autowired
	private PaymentDataService paymentDataService;
	
//	@Autowired
//	public TokenService tokenService;
	
	@GetMapping("/getPaymentData")
	public List<PaymentData> getPaymentData(){
		logger.info("Calling getPaymentData() in PaymentDataController");
		try {
			
		List<PaymentData> list = paymentDataService.getListData();
		
		return list;
		
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.info("Exception in getPaymentData() in PaymentDataController"+e.getMessage());
			return null;
		}
	}
	
	@PostMapping("/searchPaymentData")
	public List<PaymentData> searchPaymentData(@RequestBody PaymentSearchInput searchInput,@RequestHeader Map<String,String> headers){
		logger.info("Calling searchPaymentData() in PaymentDataController");
//		if(!tokenService.validateToken(headers)) {
//			return null;
//		}
		try {
			
		List<PaymentData> searchList = paymentDataService.getSearchList(searchInput);
		System.out.println("In PaymentDataController, searchList datas are "+searchList);
		
		return searchList;
		}
		catch(Exception e) {
			e.printStackTrace();
			
			logger.info("Exception in searchPaymentData() in PaymentDataController"+e.getMessage());
			
			return null;
		}
	}
	
	//This method is used to get Building Id and buildingName based on MCName
//	@GetMapping("/getBuildings/{mgmtCompId}")
//	public List<Buildings> getBuildings(@PathVariable Integer mgmtCompId){
//		logger.info("Calling getBuildings() in PaymentDataController");
//		try {
//		List<Buildings> bd = paymentDataService.getBuildings(mgmtCompId);
//		return bd;
//		}
//		catch(Exception e) {
//			logger.info("Exception in getBuildings() in PaymentDataController"+e.getMessage());
//			return null;
//		}
//	}
}
