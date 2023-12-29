package com.mashreq.oa.dao;

import java.util.Date;
import java.util.List;

import com.mashreq.oa.entity.AuditTrail;
import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.entity.PaymentData;
import com.mashreq.oa.entity.Reversal;
import com.mashreq.oa.model.AuditTrailLog;

public interface BudgetDetailsUpdateDao 
{
	
	
	public List<AuditTrail> getAuditTrailRecords(Reversal reversal);
	
	public BudgetDetailsOutput getBudgetItemById(int budgetItemId);
	
	public void updateBudgetItemTable(BudgetDetailsOutput budgetItem);
	
	public void logAuditTrail(BudgetDetailsOutput budgetItem, AuditTrail auditTrail,Double amount,String username, String comment);//for reversal
	
	public List<AuditTrailLog> fetchAuditTrailLog(String serviceCode,String userName,Date updatedOn);
	
	public List<AuditTrailLog> fetchAuditTrailLog2(String serviceCode,String userName,Date updatedFrom,Date updatedTo);
	
	public List<PaymentData> getPaymentRequests();

	public List<AuditTrailLog> getAuditTrailLogById(String pymtReqId);
	
	public AuditTrailLog updateServiceCode(String pymtReqId, String newServiceCode);
	
	public void updateBudgetItemTableWithServiceCode(BudgetDetailsOutput budgetItem);//update service code
	public void logAuditTrailWithServiceCode(BudgetDetailsOutput budgetItem, AuditTrail auditTrail,Double amount);//for update service code

}
