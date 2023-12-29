//package com.mashreq.oa.service;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.mashreq.oa.dao.BudgetDetailsDao;
//import com.mashreq.oa.entity.BudgetDetailsInput;
//import com.mashreq.oa.entity.BudgetDetailsOutput;
//
//@Service
//public class BudgetDetailsServiceImpl implements BudgetDetailsService {
//
//	@Autowired
//	private BudgetDetailsDao budgetDetailsDao;
//	@Autowired
//	public HttpSession session;
//	
//	private static final Logger logger = (Logger) LoggerFactory.getLogger(BudgetDetailsServiceImpl.class);
//	
//	@Override
//	public List<BudgetDetailsOutput> getBudgetDetails(BudgetDetailsInput bdInput) {
//		
//		logger.info("Inside getBudgetDetails() in BudgetDetailsServiceImpl");
//		
//		List<BudgetDetailsOutput> bdDetails = budgetDetailsDao.getBudgetDetails(bdInput);
//		/*Set<BudgetDetailsOutput> s=new HashSet<BudgetDetailsOutput>();
//		s.addAll(bdDetails);
//		bdDetails=new ArrayList<BudgetDetailsOutput>();
//		bdDetails.addAll(s);*/
//		return bdDetails;
//		
//	}
//
//	@Override
//	public void updateBudgetDetails(BudgetDetailsOutput bdOutput) {
//		
//		logger.info("Inside updateBudgetDetails() in BudgetDetailsServiceImpl");
//		
//		budgetDetailsDao.updateBudgetDetails(bdOutput);
//		
//	}
//
//	@Override
//	public List<Integer> getBudgetYears() {
//		try {
//			logger.info("Calling getBudgetYears() in BudgetDetailsService");
//			List<Integer> budgetYears=budgetDetailsDao.getBudgetYears();
//			return budgetYears;
//		}
//		catch(Exception e)
//		{
//			logger.info("Exception in getBudgetYears() in BudgetDetailsService :: "+e.getCause());
//			return null;
//		}
//	}
//
//}
