package com.mashreq.oa.dao;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.model.MoneyTransfer;

@Repository
public class BudgetItemsDetailsFetchDAOImpl implements BudgetDetailsDao
{
	private static Logger logger = LoggerFactory.getLogger(BudgetItemsDetailsFetchDAOImpl.class);

	static 
	{
		try 
		{
			PropertyConfigurator.configure("C://sts//Niranjana/OA_Project//src//main//java//com//mashreq//oa//commons//log4j.properties");
			logger.info("com.mashreq.oa.dao::Log4J Setup ready");
			logger.debug("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class is start");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class Problem while setting up Log4J");;
		}
	}
	
	@Autowired
	private JdbcTemplate jt;
	
	
	@Override
	public List<BudgetDetailsOutput> fetchAllBudgetItemsDetails() 
	{
		String resQuery = "select *from oa_budget_items";
		List<BudgetDetailsOutput> listOfData =null;
		
		try {
			logger.info("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class fetchAllBudgetItemsDetails() is executed successfully");
			listOfData=jt.query(resQuery, BeanPropertyRowMapper.newInstance(BudgetDetailsOutput.class));
			System.out.println("ListOfData:"+listOfData);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class known DB exception is raised in fetchAllBudgetItemsDetails() method:"+e.getMessage());
		}
		return listOfData;
	}


	@Override
	public BudgetDetailsOutput fetchSingleBudgetItemsDetails(MoneyTransfer mt) 
	{
		System.out.println("BudgetItemsDetailsFetchDAOImpl.fetchSingleBudgetItemsDetails()");
		String resQuery = "select budget_item_id,service_code,service_name_en,total_cost,vat_amount,total_budget,consumed_amount,balance_amount,is_edit_mode from OA_BUDGET_ITEMS where budget_item_id=? and service_code=?";
		System.out.println("resQuery:"+resQuery);
		BudgetDetailsOutput data =null;
		Object[] args = {mt.getBudgetItemId(),mt.getServiceCode()};
		try {
			logger.info("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class fetchSingleBudgetItemsDetails() is executed successfully");
			//data= jt.query(resQuery,args, BeanPropertyRowMapper.newInstance(BudgetDetailsOutput.class)); //for List<BudgetDetailsOutput>
			data = jt.queryForObject(resQuery, new BeanPropertyRowMapper<BudgetDetailsOutput>(BudgetDetailsOutput.class),args);
			System.out.println("data:"+data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class known DB exception is raised in fetchSingleBudgetItemsDetails() method:"+e.getMessage());
		}
		return data;
	}

	 public static char convertBooleanToChar(boolean value)
	    {
	        return value ? 'Y' : 'N';
	    }
	@Override
	public String insertBudgetItemsDetails(Integer budget_item_id,String service_code,String service_name_en,Double total_cost,Double vat_amount,Double total_budget,Double consumed_amount,Double balance_amount,Boolean is_edit_mode) 
	{
		System.out.println("BudgetItemsDetailsFetchDAOImpl.insertBudgetItemsDetails()");
		String INSERT_DATA= "INSERT INTO HR.OA_BUDGET_ITEMS(budget_item_id,service_code,service_name_en,total_cost,vat_amount,total_budget,consumed_amount,balance_amount,is_edit_mode)VALUES(BUDGET_ITEMS.NEXTVAL,?,?,?,?,?,?,?,?)";
		System.out.println("INSERT_DATA:"+INSERT_DATA);
		int count=0;
		try {
			logger.info("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class insertBudgetItemsDetails() is executed successfully");
			count = jt.update(INSERT_DATA, service_code,service_name_en,total_cost,vat_amount,total_budget,consumed_amount,balance_amount,convertBooleanToChar(is_edit_mode));
			return "";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class known DB exception is raised in insertBudgetItemsDetails() method:"+e.getMessage());
			return null;
		}
	}


	@Override
	public String updateBudgetItemData(String serviceNameEn,Double vatAmount,Double totalBudget,Double consumedAmount,Double balanceAmount,Boolean isEditMode,Integer budgetItemId,String serviceCode,Double totalCost) 
	{
		String UPDATE_DATA= "UPDATE OA_BUDGET_ITEMS SET SERVICE_NAME_EN=?,VAT_AMOUNT=?,TOTAL_BUDGET=?,CONSUMED_AMOUNT=?,BALANCE_AMOUNT=?,IS_EDIT_MODE=? WHERE BUDGET_ITEM_ID=? AND SERVICE_CODE=? AND TOTAL_COST=?";
		int count=0;
		try {
			logger.info("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class updateBudgetItemData() is executed successfully");
			logger.info("Executing SQL query: " + UPDATE_DATA);
			logger.info("Parameters DAOImpl: " +"serviceNameEn:"+ serviceNameEn + ", " + "vatAmount:"+vatAmount + ", " +"totalBudget:"+ totalBudget + ", " +"consumedAmount:"+ consumedAmount + ", " +"balanceAmount:"+ balanceAmount + ", " + convertBooleanToChar(isEditMode) + ", " + "budgetItemId:"+budgetItemId + ", " + "serviceCode:"+serviceCode + ", " +"totalCost:"+totalCost);
			count=jt.update(UPDATE_DATA,serviceNameEn,vatAmount,totalBudget,consumedAmount,balanceAmount,convertBooleanToChar(isEditMode),budgetItemId,serviceCode,totalCost);
			if (count > 0) 
			{
			    return "Data updated successfully";
			} 
			else
			{
			    throw new RuntimeException("Failed to update data");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class known DB exception is raised in updateBudgetItemData() method:"+ e.getMessage());
		}
		System.out.println(count);
		return "";
	}


	@Override
	public String updateBudgetItemDataTable(Double consumedAmount, Double balanceAmount, String serviceCode) 
	{
		String UPDATE_DATA= "UPDATE OA_BUDGET_ITEMS SET CONSUMED_AMOUNT=?,BALANCE_AMOUNT=? WHERE BUDGET_ITEM_ID=?";
		int count=0;
		try {
			logger.info("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class updateBudgetItemDataTable() is executed successfully");
			logger.info("Executing SQL query: " + UPDATE_DATA);
			logger.info("Parameters DAOImpl: " + ", " + ", " + ", " +"consumedAmount:"+ consumedAmount + ", " +"balanceAmount:"+ balanceAmount + ", " + ", " +  ", " + "serviceCode:"+serviceCode);
			count=jt.update(UPDATE_DATA,consumedAmount,balanceAmount,serviceCode);
			if (count > 0) 
			{
			    return "Data updated successfully";
			} 
			else
			{
			    throw new RuntimeException("Failed to update data");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.dao.Impl::BudgetItemsDetailsFetchDAOImpl class known DB exception is raised in updateBudgetItemDataTable() method:"+ e.getMessage());
		}
		System.out.println(count);
		return "";
	}
	
}
