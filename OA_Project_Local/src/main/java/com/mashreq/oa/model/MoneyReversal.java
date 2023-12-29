package com.mashreq.oa.model;

public class MoneyReversal 
{

	private String serviceCode;
	
	private double amount;
	
	private Integer budgetItemId;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

	public Integer getBudgetItemId() {
		return budgetItemId;
	}

	public void setBudgetItemId(Integer budgetItemId) {
		this.budgetItemId = budgetItemId;
	}

	@Override
	public String toString() {
		return "MoneyTransfer [serviceCode=" + serviceCode + ", amount=" + amount + ", budgetItemId=" + budgetItemId
				+ "]";
	}

	
	
	
}
