package com.mashreq.oa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mashreq.oa.dao.BudgetDetailsUpdateDao;
import com.mashreq.oa.entity.AuditTrail;
import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.entity.PaymentData;
import com.mashreq.oa.entity.Reversal;
import com.mashreq.oa.model.AuditTrailLog;

@Service
public class BudgetItemsDetailsUpdateServiceImpl implements BudgetDetailsUpdateService 
{
	private static Logger logger = LoggerFactory.getLogger(BudgetItemsDetailsUpdateServiceImpl.class);

	@Autowired
	private BudgetDetailsUpdateDao dao;
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<BudgetDetailsOutput> updateBudgetItems(Reversal reversal,String username) 
	{
		
			logger.info("inside com.mashreq.oa.service.BudgetItemsDetailsUpdateServiceImpl class having updateBudgetItems()-Reversal Data: "+reversal);
			
			List<BudgetDetailsOutput> budgetItemData = null;

			// Fetch all records from OA_AUDIT_TRAIL based on payment ID
			List<AuditTrail> auditTrailRecords = dao.getAuditTrailRecords(reversal);
			logger.info("fetched auditTrailRecords: " + auditTrailRecords);
			
			String uploadedBy = username;
			Map<String, Double> serviceCodeDifferencesAmount = auditTrailRecords.stream()
			        .collect(Collectors.groupingBy(AuditTrail::getServiceCode,
			                Collectors.summingDouble(record -> {
			                    double oldValue = Double.parseDouble(record.getOldValue());
			                    double newValue = Double.parseDouble(record.getNewValue());
			                    return Math.abs(oldValue - newValue);
			                })));
	        // Summing up differences amount  from all service codes
	        Double consumedAmount = serviceCodeDifferencesAmount.values().stream().mapToDouble(Double::doubleValue).sum();
	        logger.info("consumedAmount:"+consumedAmount);
	        logger.info("inVoiceAmount:"+reversal.getInvoiceAmount());


	        BudgetDetailsOutput budgetItem = null;
	        if (consumedAmount.equals(reversal.getInvoiceAmount())) 
	        {
	            System.out.println("Invoice amount matches calculated value (consumedAmount).");

	           
	            
	            // Iterate over all AuditTrail2 records and update BudgetItem data
	            for (AuditTrail auditTrailRecord : auditTrailRecords) 
	            {
	                budgetItemData = dao.getBudgetItemById(auditTrailRecord.getId());

	                // Assuming there is only one BudgetDetailsOutput object for each AuditTrail2 record
	                if (!budgetItemData.isEmpty()) 
	                {
	                     budgetItem = budgetItemData.get(0);
	                    
	                    // Calculate consumedAmount separately for each AuditTrail2 service code 
	                    Double consumedAmountt = serviceCodeDifferencesAmount.get(auditTrailRecord.getServiceCode());

	                    Double updatedConsumerAmount = budgetItem.getConsumedAmount() - consumedAmountt;
	                    Double updatedBalanceAmount = budgetItem.getBalanceAmount() + consumedAmountt;

	                    budgetItem.setConsumedAmount(updatedConsumerAmount);
	                    budgetItem.setBalanceAmount(updatedBalanceAmount);

	                    // Save the updated BudgetItem data
	                    dao.updateBudgetItemTable(budgetItem);
	                    
	                }
	            }
	         // Insert Log into OA_AUDIT_TRAIL_LOG 
	           AuditTrail auditTrail =  auditTrailRecords.get(0);
	                dao.logAuditTrail(budgetItem, auditTrail, reversal.getInvoiceAmount(), uploadedBy, "Reversal Action Done");
	        }
	        else 
	        {
	            System.out.println("Invoice amount does not match calculated value (consumedAmount).");
	        }						
			return budgetItemData;
	}
	
	@Override
	public BudgetDetailsOutput updateServiceCode(Reversal reversalEdit,String username)
	{
		logger.info("BudgetItemsDetailsUpdateServiceImpl class having updateServiceCode() method ");
		
		List<BudgetDetailsOutput> budgetItemData = null;
		
		// Fetch all records from OA_AUDIT_TRAIL based on payment ID
		  List<AuditTrail> auditTrailRecords= dao.getAuditTrailRecords(reversalEdit);
		  logger.info("fetched auditTrailRecords: " + auditTrailRecords);
		  
			String uploadedBy = username;
		  
			Map<String, Double> serviceCodeDifferencesAmount = auditTrailRecords.stream()
			        .collect(Collectors.groupingBy(AuditTrail::getServiceCode,
			                Collectors.summingDouble(record -> {
			                    double oldValue = Double.parseDouble(record.getOldValue());
			                    double newValue = Double.parseDouble(record.getNewValue());
			                    return Math.abs(oldValue - newValue);
			                })));
			
	        // Summing up differences amount  from all service codes
	        Double consumedAmount = serviceCodeDifferencesAmount.values().stream().mapToDouble(Double::doubleValue).sum();
	        logger.info("consumedAmount:"+consumedAmount);
	        logger.info("inVoiceAmount:"+reversalEdit.getInvoiceAmount());


	        BudgetDetailsOutput budgetItem = null;
	        if (consumedAmount.equals(reversalEdit.getInvoiceAmount())) 
	        {
	            System.out.println("Invoice amount matches calculated value (consumedAmount).");

	            budgetItemData = new ArrayList<>();
	           
	            
	            // Iterate over all AuditTrail2 records and update BudgetItem data
	            for (AuditTrail auditTrailRecord : auditTrailRecords) 
	            {
	                budgetItemData = dao.getBudgetItemById(auditTrailRecord.getId());

	                // Assuming there is only one BudgetDetailsOutput object for each AuditTrail2 record
	                if (!budgetItemData.isEmpty()) 
	                {
	                     budgetItem = budgetItemData.get(0);//budgetItemData.stream().forEach(a->a.getBudgetItemId());
	                    
	                    // Calculate consumedAmount separately for each AuditTrail2 service code 
	                    Double consumedAmountt = serviceCodeDifferencesAmount.get(auditTrailRecord.getServiceCode());

	                    Double updatedConsumerAmount = budgetItem.getConsumedAmount() - consumedAmountt;
	                    Double updatedBalanceAmount = budgetItem.getBalanceAmount() + consumedAmountt;																					
	                    
	                    budgetItem.setConsumedAmount(updatedConsumerAmount);
	                    budgetItem.setBalanceAmount(updatedBalanceAmount);
	                    budgetItem.setServiceCode(reversalEdit.getNewServiceCode());

	                    // Save the updated BudgetItem data
	                    dao.updateBudgetItemTableWithServiceCode(budgetItem);
	                    
	                    dao.logAuditTrail(budgetItem, auditTrailRecord, reversalEdit.getInvoiceAmount(),uploadedBy,"Reversal Action Done");
	                }
	            }
				}
			
		 return budgetItem;
	}
	

	@Override
	public List<AuditTrailLog> getDataFromAuditTrailLog(String serviceCode, String userName, Date updatedOn) 
	{
		List<AuditTrailLog> listOfData = null;
		logger.info("BudgetItemsDetailsUpdateServiceImpl class having getDataFromAuditTrailLog() method ");
		listOfData = dao.fetchAuditTrailLog(serviceCode, userName, updatedOn);
		logger.info("BudgetItemsDetailsUpdateServiceImpl class having getDataFromAuditTrailLog() method listOfData"+listOfData);
		return listOfData;
	}
	
	@Override
	public List<AuditTrailLog> getDataFromAuditTrailLog2(String serviceCode, String userName, Date updatedFrom,Date updatedTo) 
	{
		List<AuditTrailLog> listOfData = null;
		logger.info("BudgetItemsDetailsUpdateServiceImpl class having getDataFromAuditTrailLog() method ");
		listOfData = dao.fetchAuditTrailLog2(serviceCode, userName, updatedFrom,updatedTo);
		logger.info("BudgetItemsDetailsUpdateServiceImpl class having getDataFromAuditTrailLog() method listOfData"+listOfData);
		return listOfData;
	}

	@Override
	public List<PaymentData> getListData() 
	{
		return dao.getPaymentRequests();
	}

	@Override
	public List<AuditTrailLog> fetchAuditTrailLogById(String pymtReqId) 
	{   
		logger.info("BudgetItemsDetailsUpdateServiceImpl class having fetchAuditTrailLogById() method ");
		return dao.getAuditTrailLogById(pymtReqId);
	}

	
	
}