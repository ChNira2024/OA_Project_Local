package com.mashreq.oa.service;

import java.util.Date;
import java.util.List;

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
//	@Transactional
	public BudgetDetailsOutput updateBudgetItems(Reversal reversal,String username) 
	{
		logger.info("inside com.mashreq.oa.service.BudgetItemsDetailsUpdateServiceImpl class having updateBudgetItems()");
		
		BudgetDetailsOutput budgetItemData = null;

		logger.info("BudgetItemsDetailsUpdateServiceImpl class updateBudgetItems()-Reversal Data: " + reversal);

		
		// Fetch all records from OA_AUDIT_TRAIL based on payment ID
		List<AuditTrail> auditTrailRecords = dao.getAuditTrailRecords(reversal);
		logger.info("fetched auditTrailRecords: " + auditTrailRecords);
		
		String fromScreen = "Updated From Screen By";
		String uploadedBy = fromScreen + ": " + username;

		// Iterate over audit trail records
		 for (AuditTrail auditTrailRecord : auditTrailRecords) 
		{
		
			// Subtract new value from old value to get ConsumedAmount
			 //Double consumedAmount = Double.parseDouble(auditTrailRecord.getNewValue()) - Double.parseDouble(auditTrailRecord.getOldValue());
			 
			 //Subtract new value from old value to get ConsumedAmount(get absolute value)
			 Double consumedAmount = Math.abs(Double.parseDouble(auditTrailRecord.getNewValue()) - Double.parseDouble(auditTrailRecord.getOldValue()));
			
			
			logger.info("Consumed Amount: " +consumedAmount);
			logger.info("invoice amount: " + reversal.getInvoiceAmount());

			/*
			  if(consumedAmount.equals(reversal.getInvoiceAmount())) 
			   {
			    logger.info(">>>>>>>>>Equal"); 
			  } 
			  else 
			  { logger.info("Not Equal"); 
			  }
			  
			 */

			// Fetch BudgetItem based on budgetItemId
			budgetItemData = dao.getBudgetItemById(auditTrailRecord.getId());
			logger.info("fetched data from oa_budget_items table by using budgetItemId: " + budgetItemData);

			// Add validation
			if (consumedAmount.equals(reversal.getInvoiceAmount())) 
			{
				// Update BudgetItem based on your logic
				if (budgetItemData != null) 
				{
					Double updatedConsumerAmount = budgetItemData.getConsumedAmount() - consumedAmount;
					Double updatedBalanceAmount = budgetItemData.getBalanceAmount() + consumedAmount;

					budgetItemData.setConsumedAmount(updatedConsumerAmount);
					budgetItemData.setBalanceAmount(updatedBalanceAmount);

					// Save the updated BudgetItem
					dao.updateBudgetItemTable(budgetItemData);
				}
				// insert Log into OA_AUDIT_TRAIL_LOG
				dao.logAuditTrail(budgetItemData, auditTrailRecord, reversal.getInvoiceAmount(),uploadedBy,"Reversal Action Done");
			}
			
		}//for-loop end
		return budgetItemData;
	}
	
	@Override
	public BudgetDetailsOutput updateServiceCode(Reversal reversalEdit,String username)
	{
		logger.info("BudgetItemsDetailsUpdateServiceImpl class having updateServiceCode() method ");
		
		BudgetDetailsOutput budgetItemData = null;
		
		// Fetch all records from OA_AUDIT_TRAIL based on payment ID
		  List<AuditTrail> auditTrailRecords= dao.getAuditTrailRecords(reversalEdit);
		  logger.info("fetched auditTrailRecords: " + auditTrailRecords);
		  
		  String fromScreen = "Updated From Screen By";
			String uploadedBy = fromScreen + ": " + username;
		  
		// Iterate over audit trail records
			 for (AuditTrail auditTrailRecord : auditTrailRecords) 
			{
				//Subtract new value from old value to get ConsumedAmount(get absolute value)
				 Double consumedAmount = Math.abs(Double.parseDouble(auditTrailRecord.getNewValue()) - Double.parseDouble(auditTrailRecord.getOldValue()));
				logger.info("Consumed Amount: " +consumedAmount);
				logger.info("invoice amount: " + reversalEdit.getInvoiceAmount());
				
				// Fetch BudgetItem based on budgetItemId
				 budgetItemData = dao.getBudgetItemById(auditTrailRecord.getId());
				 logger.info("BudgetItemsDetailsUpdateServiceImpl class having updateServiceCode() fetched data from oa_budget_items table by using budgetItemId: " + budgetItemData);
				 
				// Add validation
				if (consumedAmount.equals(reversalEdit.getInvoiceAmount())) 
				{

					// Update BudgetItem based on your logic
					if (budgetItemData != null) 
					{
						Double updatedConsumerAmount = budgetItemData.getConsumedAmount() - consumedAmount;
						Double updatedBalanceAmount = budgetItemData.getBalanceAmount() + consumedAmount;

						budgetItemData.setConsumedAmount(updatedConsumerAmount);
						budgetItemData.setBalanceAmount(updatedBalanceAmount);
						budgetItemData.setServiceCode(reversalEdit.getNewServiceCode());

						// Save the updated BudgetItem
						dao.updateBudgetItemTableWithServiceCode(budgetItemData);
						
						
					}
					// insert Log into OA_AUDIT_TRAIL_LOG
					dao.logAuditTrail(budgetItemData, auditTrailRecord, reversalEdit.getInvoiceAmount(),uploadedBy,"Reversal Action Done");
//					dao.logAuditTrailWithServiceCode(budgetItemData, auditTrailRecord, reversalEdit.getInvoiceAmount());
				}
			}
		 return budgetItemData;
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