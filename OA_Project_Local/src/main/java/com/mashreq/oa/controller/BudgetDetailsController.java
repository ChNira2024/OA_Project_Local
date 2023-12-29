package com.mashreq.oa.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashreq.oa.entity.BudgetDetailsInput;
import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.entity.ReserveFundSaveData;
import com.mashreq.oa.entity.ReserveFundUpdateData;
import com.mashreq.oa.model.MoneyTransfer;
import com.mashreq.oa.service.BudgetDetailsService;
import com.mashreq.oa.service.TokenService;

@RestController
@RequestMapping("/budget")
@CrossOrigin
public class BudgetDetailsController 
{
	/*
	@Autowired
	private BudgetDetailsService budgetDetailsService;
	@Autowired
	public TokenService tokenService; 
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BudgetDetailsController.class);
	
	@PostMapping("/budgetDetails")
	public List<BudgetDetailsOutput> getBudgetDetails(@RequestBody BudgetDetailsInput bdInput,@RequestHeader Map<String,String> headers)
	{
	if(!tokenService.validateToken(headers)){
			return null;
		}
		try {
			
			logger.info("Calling getBudgetDetails() in BudgetDetailsController");
			
			List<BudgetDetailsOutput> bdDetails = budgetDetailsService.getBudgetDetails(bdInput);
			for(BudgetDetailsOutput bean:bdDetails) {
				if(bean.getTotalCost()!=null && bean.getTotalCost()!=0) {
					bean.setTotalCost(Math.round(bean.getTotalCost()*100.0)/100.0);
				}
				if(bean.getVatAmount()!=null && bean.getVatAmount()!=0) {
					bean.setVatAmount(Math.round(bean.getVatAmount()*100.0)/100.0);
				}
				if(bean.getTotalBudget()!=null && bean.getTotalBudget()!=0) {
					bean.setTotalBudget(Math.round(bean.getTotalBudget()*100.0)/100.0);
				}
				if(bean.getConsumedAmount()!=null && bean.getConsumedAmount()!=0) {
					bean.setConsumedAmount(Math.round(bean.getConsumedAmount()*100.0)/100.0);
				}
				if(bean.getBalanceAmount()!=null && bean.getBalanceAmount()!=0) {
					bean.setBalanceAmount(Math.round(bean.getBalanceAmount()*100.0)/100.0);
				}
			}
			return bdDetails;
		}
		catch(Exception e)
		{
			logger.info("Exception in getBudgetDetails() in BudgetDetailsController :: "+e.getCause());
			return null;
		}
	}
	
	@PutMapping("/updateBudget")
	public void updateBudgetDetails(@RequestBody BudgetDetailsOutput bdOutput,@RequestHeader Map<String,String> headers)
	{
		if(!tokenService.validateToken(headers)){
			
		}
		try {
			logger.info("Calling updateBudgetDetails() in BudgetDetailsController");
			budgetDetailsService.updateBudgetDetails(bdOutput);
		}
		catch(Exception e)
		{
			logger.info("Exception in updateBudgetDetails() in BudgetDetailsController :: "+e.getCause());
		}
	}
	
	@GetMapping("/budgetYears")
	public List<Integer> getBudgetYears(){
		try {
			logger.info("Calling getBudgetYears() in BudgetDetailsController");
			List<Integer> budgetYears=budgetDetailsService.getBudgetYears();
			return budgetYears;
		}
		catch(Exception e)
		{
			logger.info("Exception in getBudgetYears() in BudgetDetailsController :: "+e.getCause());
			return null;
		}
	}
	
	public static void main(String[] args) {
		Double x=7543.678922;
		
		System.out.println("Value::"+Math.round(x*100.0)/100.0);
	}
*/
	private static Logger logger = LoggerFactory.getLogger(BudgetDetailsController.class);
	static {
		try {
			PropertyConfigurator
					.configure("C://sts//Niranjana/OA_Project//src//main//java//com//mashreq//oa//commons//log4j.properties");
			logger.info("com.mashreq.oa.controller::Log4J Setup ready");
			logger.debug("com.mashreq.oa.controller::BudgetDetailsController class is start");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("com.mashreq.oa.controller::BudgetDetailsController class Problem while setting up Log4J");
			;
		}
	}
	@Autowired
	private BudgetDetailsService service;
	
	@GetMapping("/fetchAllBudgetIteamDetails")
	public List<BudgetDetailsOutput> getAllBudgetItemDetails()
	{
		List<BudgetDetailsOutput> listOfdata=null;
		try {
			logger.info("com.mashreq.oa.controller::BudgetDetailsController class getAllBudgetItemDetails() method executed successfully");
			listOfdata = service.fetchAllBudgetItemsDetails();
		}
		catch(Exception e)
		{
			logger.error("com.mashreq.oa.controller::BudgetDetailsController class known DB exception is raised in getAllBudgetItemDetails() method:"+e.getMessage());
		}					
		return listOfdata;
	}
	
	@GetMapping("/fetchSingleBudgetIteamDetails")
	public BudgetDetailsOutput getSingleBudgetItemDetails(@RequestBody MoneyTransfer mt)
	{
		BudgetDetailsOutput listOfdata=null;
		try {
			logger.info("com.mashreq.oa.controller::BudgetDetailsController class getSingleBudgetItemDetails() method executed successfully");
			listOfdata = service.fetchSingleBudgetItemsDetails(mt);
		}
		catch(Exception e)
		{
			logger.error("com.mashreq.oa.controller::BudgetDetailsController class known DB exception is raised in getSingleBudgetItemDetails() method:"+e.getMessage());
		}					
		return listOfdata;
	}
	
	 public static char convertBooleanToChar(boolean value)    
	 {
	        return value ? 'Y' : 'N';
	 }
	@PostMapping("/dataSave")
	public String insertIntoBudgetItems(@RequestBody BudgetDetailsOutput inputs) 
	{
			String data=null;
		try {
			logger.info("com.mashreq.oa.controller::BudgetDetailsController class insertIntoBudgetItems() method executed successfully");
			data = service.registerBudgetItemsDetails(inputs.getBudgetItemId(),inputs.getServiceCode(),inputs.getServiceNameEn(),inputs.getBalanceAmount(),inputs.getConsumedAmount(),
					inputs.getTotalBudget(),inputs.getTotalCost(),inputs.getVatAmount(),inputs.getIsEditMode());
		}
		catch(Exception e)
		{
			logger.error("com.mashreq.oa.controller::BudgetDetailsController class known DB exception is raised in insertIntoBudgetItems() method:"+e.getMessage());
		}					
		return data;
	}
	
	@PostMapping("/dataUpdate")
	public ResponseEntity<String> BudgetItemsDataTable(@RequestBody BudgetDetailsOutput inputs) 
	{
			String data=null;
			try {
				logger.info("com.mashreq.oa.controller::BudgetDetailsController class BudgetItemsDataTable() method executed successfully");
				logger.info("Parameters Controler: " +"serviceNameEn:"+ inputs.getServiceNameEn() + ", " + "vatAmount:"+inputs.getVatAmount() + ", " +"totalBudget:"+ inputs.getTotalBudget() + ", " +"consumedAmount:"+ inputs.getConsumedAmount() + ", " +"balanceAmount:"+ inputs.getBalanceAmount() + ", " +"IsEdit:"+inputs.getIsEditMode() + ", " + "budgetItemId:"+inputs.getBudgetItemId() + ", " + "serviceCode:"+inputs.getServiceCode() + ", " +"totalCost:"+inputs.getTotalCost());
				data = service.updateeBudgetItemsDetails(inputs.getServiceNameEn(),inputs.getVatAmount(),inputs.getTotalBudget(),inputs.getConsumedAmount(),inputs.getBalanceAmount(),inputs.getIsEditMode(),inputs.getBudgetItemId(),inputs.getServiceCode(),inputs.getTotalCost() );
				return ResponseEntity.ok(data);
			}
			catch(Exception e)
			{
				logger.error("com.mashreq.oa.controller::BudgetDetailsController class known DB exception is raised in BudgetItemsDataTable() method:"+e.getMessage());
				return new ResponseEntity<String>("Data not updated",HttpStatus.NOT_FOUND);
			}
		//return data;
	}
	

	@PostMapping("/moneyTransfer")
	public String moneyTransfer(@RequestBody  MoneyTransfer mt)
	{
		BudgetDetailsOutput list=null;
		try {
			logger.info("com.mashreq.oa.controller::BudgetDetailsController class moneyTransfer() method executed successfully");
			service.moneyTransactionService(mt);
			return "Money Trasfered";
		}
		catch(Exception e)
		{
			logger.error("com.mashreq.oa.controller::BudgetDetailsController class known DB exception is raised in moneyTransfer() method:"+e.getMessage());
			return null;
		}					
	}
	
	@PostMapping("/moneyReversal")
	public String moneyReversal(@RequestBody  MoneyTransfer mt)
	{
		BudgetDetailsOutput list=null;
		try {
			logger.info("com.mashreq.oa.controller::BudgetDetailsController class moneyReversal() method executed successfully");
			service.moneyReversalService(mt);
			return "Money Reversal";
		}
		catch(Exception e)
		{
			logger.error("com.mashreq.oa.controller::BudgetDetailsController class known DB exception is raised in moneyReversal() method:"+e.getMessage());
			return null;
		}					
	}
	
	//===================================
	
	
}
