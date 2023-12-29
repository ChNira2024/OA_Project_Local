package com.mashreq.oa.service;

import java.util.Date;
import java.util.List;

import com.mashreq.oa.entity.AuditTrail;
import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.entity.PaymentData;
import com.mashreq.oa.entity.Reversal;
import com.mashreq.oa.model.AuditTrailLog;

public interface BudgetDetailsUpdateService 
{
	public BudgetDetailsOutput updateBudgetItems(Reversal reversal,String username);
	
	//public void updateBudgetItems(int budgetItemId, String serviceCode, Double consumedAmount, Double balanceAmount);
	
	List<AuditTrailLog> getDataFromAuditTrailLog(String serviceCode, String userName, Date updatedOn);
	
	public List<AuditTrailLog> getDataFromAuditTrailLog2(String serviceCode, String userName,Date updatedFrom,Date updatedTo);

	public List<PaymentData> getListData();
	
	public List<AuditTrailLog> fetchAuditTrailLogById(String pymtReqId) ;
	
	public BudgetDetailsOutput updateServiceCode(Reversal reversalEdit,String username);

	
}
