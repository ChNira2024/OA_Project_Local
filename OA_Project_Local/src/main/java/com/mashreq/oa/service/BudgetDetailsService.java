package com.mashreq.oa.service;

import java.util.List;

import com.mashreq.oa.entity.BudgetDetailsInput;
import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.model.MoneyReversal;
import com.mashreq.oa.model.MoneyTransfer;

public interface BudgetDetailsService {
	
	// List<BudgetDetailsOutput> getBudgetDetails(BudgetDetailsInput bdInput);

	//public void updateBudgetDetails(BudgetDetailsOutput bdOutput);

	//public List<Integer> getBudgetYears();
	public List<BudgetDetailsOutput> fetchAllBudgetItemsDetails();
	
	public BudgetDetailsOutput fetchSingleBudgetItemsDetails(MoneyTransfer mt);
	
	public String registerBudgetItemsDetails(Integer budget_item_id,String service_code,String service_name_en,Double total_cost,Double vat_amount,Double total_budget,Double consumed_amount,Double balance_amount,Boolean is_edit_mode);
	
	public String updateeBudgetItemsDetails(String serviceNameEn,Double vatAmount,Double totalBudget,Double consumedAmount,Double balanceAmount,Boolean isEditMode,Integer budgetItemId,String serviceCode,Double totalCost);
	
	public String moneyTransactionService(MoneyTransfer mt);
	public String moneyReversalService(MoneyTransfer tk);
	
	public String updateBudgetItemDataTable(Double consumedAmount,Double balanceAmount,String serviceCode);
	
	//public void updateBudgetItems(String paymentRequestId, Double userInvoiceAmount);
	

}
