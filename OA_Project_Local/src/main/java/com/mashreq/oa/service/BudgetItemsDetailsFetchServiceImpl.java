package com.mashreq.oa.service;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashreq.oa.dao.BudgetDetailsDao;
import com.mashreq.oa.dao.BudgetItemsDetailsFetchDAOImpl;
import com.mashreq.oa.dao.ReserveFundSearchDAO;
import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.entity.ReserveFundSearchInputData;
import com.mashreq.oa.model.MoneyTransfer;

@Service
public class BudgetItemsDetailsFetchServiceImpl implements BudgetDetailsService
{
	private static Logger logger = LoggerFactory.getLogger(BudgetItemsDetailsFetchServiceImpl.class);

	static 
	{
		try 
		{
			PropertyConfigurator.configure("C://sts//Niranjana/OA_Project//src//main//java//com//mashreq//oa//commons//log4j.properties");
			logger.info("com.mashreq.oa.dao::Log4J Setup ready");
			logger.debug("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchServiceImpl class is start");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchServiceImpl class Problem while setting up Log4J");;
		}
	}
	
	@Autowired
	private BudgetDetailsDao dao;

	@Override
	public List<BudgetDetailsOutput> fetchAllBudgetItemsDetails() 
	{
		List<BudgetDetailsOutput> list =null;
		try {
			logger.info("com.mashreq.oa.service.Impl::BudgetItemsDetailsFetchServiceImpl class fetchAllBudgetItemsDetails() is executed successfully");
			list=dao.fetchAllBudgetItemsDetails();
		}
		catch(Exception e) 
		{
			e.printStackTrace();	
			logger.error("com.mashreq.oa.service.Impl::BudgetItemsDetailsFetchServiceImpl class known DB exception is raised in fetchAllBudgetItemsDetails() method"+e.getMessage());
		}
		return list;
	}

	@Override
	public BudgetDetailsOutput fetchSingleBudgetItemsDetails(MoneyTransfer mt) 
	{
		BudgetDetailsOutput list =null;
		try {
			logger.info("com.mashreq.oa.service.Impl::BudgetItemsDetailsFetchServiceImpl class fetchSingleBudgetItemsDetails() is executed successfully");
			list=dao.fetchSingleBudgetItemsDetails(mt);
		}
		catch(Exception e) 
		{
			e.printStackTrace();	
			logger.error("com.mashreq.oa.service.Impl::BudgetItemsDetailsFetchServiceImpl class known DB exception is raised in fetchSingleBudgetItemsDetails() method"+e.getMessage());
		}
		return list;
	}
	
	@Override
	public String moneyTransactionService(MoneyTransfer tk) 
	{
		double balancedAmount=0;
		try {
			logger.info("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchServiceImpl class moneyTransactionService() is executed successfully");
			BudgetDetailsOutput dataOf = dao.fetchSingleBudgetItemsDetails(tk);
			System.out.println("dataOf:"+dataOf);
				System.out.println("tk.getServiceCode():"+tk.getServiceCode());
				System.out.println("dataOf.getServiceCode():"+dataOf.getServiceCode());
				System.out.println("tk.getBudgetItemId():"+tk.getBudgetItemId());
				System.out.println("dataOf.getBudgetItemId():"+dataOf.getBudgetItemId());
				if(tk.getServiceCode().equals(dataOf.getServiceCode()) && tk.getBudgetItemId().equals(dataOf.getBudgetItemId()))
				{
					System.out.println(">>>>>>>>..");
					
					balancedAmount = dataOf.getBalanceAmount() - tk.getAmount();
					
					Double updatedConsumedAmount = dataOf.getConsumedAmount()+tk.getAmount();
					logger.info("Parameters ServiceImpl moneyTransactionService(): " +"serviceNameEn:"+ dataOf.getServiceNameEn() + ", " + "vatAmount:"+dataOf.getVatAmount() + ", " +"totalBudget:"+ dataOf.getTotalBudget() + ", " +"consumedAmount:"+ updatedConsumedAmount + ", " +"balanceAmount:"+ balancedAmount + ", " +"isEditMode:"+dataOf.getIsEditMode()+", " +"budgetItemId:"+tk.getBudgetItemId() + ", " + "serviceCode:"+tk.getServiceCode() + ", " +"totalCost:"+tk.getAmount());
					
					String updatedServiceName = dataOf.getServiceNameEn();
					System.out.println("updatedServiceName:"+updatedServiceName);
					
					Double updatedVatAmount = dataOf.getVatAmount();
					System.out.println("updatedVatAmount:"+updatedVatAmount);
					
					Double updatedTotalBudget = dataOf.getTotalBudget();
					System.out.println("updatedTotalBudget:"+updatedTotalBudget);
					
					System.out.println("updatedConsumedAmount:"+updatedConsumedAmount);
					
					System.out.println("balancedAmount:"+balancedAmount);
					
					Boolean updatedEditMode = dataOf.getIsEditMode();
					System.out.println("updatedEditMode:"+updatedEditMode);
					
					Integer updatedBudgetItemId = dataOf.getBudgetItemId();
					System.out.println("updatedBudgetItemId:"+updatedBudgetItemId);
					
					String updatedServiveCode = dataOf.getServiceCode();
					System.out.println("updatedServiveCode:"+updatedServiveCode);
					
					Double updatedTotalCost = dataOf.getTotalCost();
					System.out.println("updatedTotalCost:"+updatedTotalCost);
					
					dao.updateBudgetItemData(updatedServiceName,updatedVatAmount,updatedTotalBudget,updatedConsumedAmount,balancedAmount,updatedEditMode,updatedBudgetItemId,updatedServiveCode,updatedTotalCost);
					return "money transfr successfully";
				}
				else
				{
					System.out.println("we don't have Service code or building id");
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchServiceImpl class known DB exception is raised in moneyTransactionService() method:"+e.getMessage());
		}
		return null;
	}

	@Override
	public String registerBudgetItemsDetails(Integer budget_item_id,String service_code,String service_name_en,Double total_cost,Double vat_amount,Double total_budget,Double consumed_amount,Double balance_amount,Boolean is_edit_mode) 
	{
		String data=null;
		try {
			logger.info("com.mashreq.oa.service.Impl::BudgetItemsDetailsFetchServiceImpl class registerBudgetItemsDetails() is executed successfully");
			data =dao.insertBudgetItemsDetails(budget_item_id,service_code,service_name_en,total_cost,vat_amount,total_budget,consumed_amount,balance_amount,is_edit_mode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.service.Impl::BudgetItemsDetailsFetchServiceImpl class known DB exception is raised in registerBudgetItemsDetails() method"+e.getMessage());
		}
		return "data inserted";
	}

	@Override
	public String updateeBudgetItemsDetails(String serviceNameEn,Double vatAmount,Double totalBudget,Double consumedAmount,Double balanceAmount,Boolean isEditMode,Integer budgetItemId,String serviceCode,Double totalCost) 
	{
		String data = null;
		try {
			logger.info("com.mashreq.oa.service.Impl:BudgetItemsDetailsFetchServiceImpl class updateeBudgetItemsDetails() is executed successfully");
			logger.info("Parameters ServiceImpl: " +"serviceNameEn:"+ serviceNameEn + ", " + "vatAmount:"+vatAmount + ", " +"totalBudget:"+ totalBudget + ", " +"consumedAmount:"+ consumedAmount + ", " +"balanceAmount:"+ balanceAmount + ", " + isEditMode + ", " + "budgetItemId:"+budgetItemId + ", " + "serviceCode:"+serviceCode + ", " +"totalCost:"+totalCost);
			data=dao.updateBudgetItemData(serviceNameEn,vatAmount, totalBudget, consumedAmount, balanceAmount, isEditMode,budgetItemId,serviceCode,totalCost);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.service.Impl.BudgetItemsDetailsFetchServiceImpl class known DB exception is raised in updateeBudgetItemsDetails() method:"+e.getMessage());
		}
		
		return data;
	}

	@Override
	public String moneyReversalService(MoneyTransfer tk)
	{
		double balancedAmount=0;
		try {
			logger.info("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchServiceImpl class moneyTransactionService() is executed successfully");
			BudgetDetailsOutput dataOf = dao.fetchSingleBudgetItemsDetails(tk);
			System.out.println("dataOf:"+dataOf);
				System.out.println("tk.getServiceCode():"+tk.getServiceCode());
				System.out.println("dataOf.getServiceCode():"+dataOf.getServiceCode());
				System.out.println("tk.getBudgetItemId():"+tk.getBudgetItemId());
				System.out.println("dataOf.getBudgetItemId():"+dataOf.getBudgetItemId());
				if(tk.getServiceCode().equals(dataOf.getServiceCode()) && tk.getBudgetItemId().equals(dataOf.getBudgetItemId()))
				{
					System.out.println(">>>>>>>>..");
					
					balancedAmount = dataOf.getBalanceAmount() + tk.getAmount();
					
					Double updatedConsumedAmount = dataOf.getConsumedAmount()-tk.getAmount();
					logger.info("Parameters ServiceImpl moneyTransactionService(): " +"serviceNameEn:"+ dataOf.getServiceNameEn() + ", " + "vatAmount:"+dataOf.getVatAmount() + ", " +"totalBudget:"+ dataOf.getTotalBudget() + ", " +"consumedAmount:"+ updatedConsumedAmount + ", " +"balanceAmount:"+ balancedAmount + ", " +"isEditMode:"+dataOf.getIsEditMode()+", " +"budgetItemId:"+tk.getBudgetItemId() + ", " + "serviceCode:"+tk.getServiceCode() + ", " +"totalCost:"+tk.getAmount());
					
					String updatedServiceName = dataOf.getServiceNameEn();
					System.out.println("updatedServiceName:"+updatedServiceName);
					
					Double updatedVatAmount = dataOf.getVatAmount();
					System.out.println("updatedVatAmount:"+updatedVatAmount);
					
					Double updatedTotalBudget = dataOf.getTotalBudget();
					System.out.println("updatedTotalBudget:"+updatedTotalBudget);
					
					System.out.println("updatedConsumedAmount:"+updatedConsumedAmount);
					
					System.out.println("balancedAmount:"+balancedAmount);
					
					Boolean updatedEditMode = dataOf.getIsEditMode();
					System.out.println("updatedEditMode:"+updatedEditMode);
					
					Integer updatedBudgetItemId = dataOf.getBudgetItemId();
					System.out.println("updatedBudgetItemId:"+updatedBudgetItemId);
					
					String updatedServiveCode = dataOf.getServiceCode();
					System.out.println("updatedServiveCode:"+updatedServiveCode);
					
					Double updatedTotalCost = dataOf.getTotalCost();
					System.out.println("updatedTotalCost:"+updatedTotalCost);
					
					dao.updateBudgetItemData(updatedServiceName,updatedVatAmount,updatedTotalBudget,updatedConsumedAmount,balancedAmount,updatedEditMode,updatedBudgetItemId,updatedServiveCode,updatedTotalCost);
					return "money reversed successfully";
				}
				else
				{
					System.out.println("we don't have Service code or building id");
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchServiceImpl class known DB exception is raised in moneyTransactionService() method:"+e.getMessage());
		}
		return null;
	}

	@Override
	public String updateBudgetItemDataTable(Double consumedAmount, Double balanceAmount, String serviceCode) 
	{
		return dao.updateBudgetItemDataTable(consumedAmount, balanceAmount, serviceCode);
		 
	}
}
