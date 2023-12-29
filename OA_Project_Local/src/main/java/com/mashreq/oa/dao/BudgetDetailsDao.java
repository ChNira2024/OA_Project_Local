package com.mashreq.oa.dao;

import java.util.List;

import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.model.MoneyTransfer;

public interface BudgetDetailsDao {
	
	//public List<BudgetDetailsOutput> getBudgetDetails(BudgetDetailsInput bdInput);

	//public void updateBudgetDetails(BudgetDetailsOutput bdOutput);

	//public List<Integer> getBudgetYears();
	
	public List<BudgetDetailsOutput> fetchAllBudgetItemsDetails();
	
	public BudgetDetailsOutput fetchSingleBudgetItemsDetails(MoneyTransfer mt);
	
	public String insertBudgetItemsDetails(Integer budget_item_id,String service_code,String service_name_en,Double total_cost,Double vat_amount,Double total_budget,Double consumed_amount,Double balance_amount,Boolean is_edit_mode);
	
	public String updateBudgetItemData(String serviceNameEn,Double vatAmount,Double totalBudget,Double consumedAmount,Double balanceAmount,Boolean isEditMode,Integer budgetItemId,String serviceCode,Double totalCost);
	
	public String updateBudgetItemDataTable(Double consumedAmount,Double balanceAmount,String serviceCode);

	
	

}
