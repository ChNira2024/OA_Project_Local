package com.mashreq.oa.service;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashreq.oa.dao.ReserveFundSearchDAO;
import com.mashreq.oa.entity.ReserveFundSearchInputData;

@Service
public class ReserveFundServiceImpl implements ReserveFundSearchService {
	private static Logger logger = Logger.getLogger(ReserveFundServiceImpl.class);
	static 
	{
		try 
		{
			PropertyConfigurator.configure("C://sts//Niranjana/OA_Project//src//main//java//com//mashreq//oa//commons//log4j.properties");
			logger.info("com.mashreq.oa.service.Impl::Log4J Setup ready");
			logger.debug("com.mashreq.oa.service.Impl::ReserveFundServiceImpl class is start");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.service.Impl::ReserveFundServiceImpl class Problem while setting up Log4J");
		}
	}
	@Autowired
	private ReserveFundSearchDAO dao;
	@Override
	public List<ReserveFundSearchInputData> fetchDataDetailsByService(Long mgmtId, Long bldId) {
		System.out.println("selected id is:"+mgmtId+" "+bldId);	
		List<ReserveFundSearchInputData> list =null;
		try {
			logger.info("com.mashreq.oa.service.Impl::ReserveFundServiceImpl class fetchDataDetailsByService() is executed successfully");
			list=dao.getDataDetailsDAO(mgmtId,bldId);
		}
		catch(Exception e) 
		{
			e.printStackTrace();	
			logger.error("com.mashreq.oa.service.Impl::ReserveFundServiceImpl class known DB exception is raised in fetchDataDetailsByService() method"+e.getMessage());
		}
		return list;
	}
	
	@Override
	public String registerReserveFundData(Integer reserve_Fund_Id, String account_Number, String cif_Number,
			Integer mgmnt_Comp_Id, Integer building_Id, String reserve_Account_Number, String is_Active, String branch_Code) {
		String data=null;
		try {
			logger.info("com.mashreq.oa.service.Impl::ResrveFundServiceImpl class registerReserveFundData() is executed successfully");
			data =dao.insertReserveFundData(reserve_Fund_Id,account_Number,cif_Number, mgmnt_Comp_Id,building_Id,reserve_Account_Number,is_Active,branch_Code);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.service.Impl::ReserveFundServiceImpl class known DB exception is raised in registerReserveFundData() method"+e.getMessage());
		}
		return "data inserted";
	}

	@Override
	public String updateReserveFundDataDetails(Integer reserveFundId, String accountNumber, String reserveAccountNumber, String isActive, String branchCode) {
		String data = null;
		try {
			logger.info("com.mashreq.oa.service.Impl:ReserveFundServiceImpl class updateReserveFundDataDetails() is executed successfully");
			data=dao.updateReserveFundData(reserveFundId, accountNumber,reserveAccountNumber, isActive,branchCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.service.Impl.ReserveFundServiceImpl class known DB exception is raised in updateReserveFundDataDetails() method:"+e.getMessage());
		}
		
		return data;
	}
}
