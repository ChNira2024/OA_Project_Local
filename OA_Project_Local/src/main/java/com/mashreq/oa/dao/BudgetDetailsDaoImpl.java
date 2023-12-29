//package com.mashreq.oa.dao;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import com.mashreq.oa.entity.BudgetDetailsInput;
//import com.mashreq.oa.entity.BudgetDetailsOutput;
//
//@Repository
//public class BudgetDetailsDaoImpl implements BudgetDetailsDao {
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	//@Value("${MOLLAK.DBAPPEND}")
//	@Value("${DBAPPEND}")
//	private String DBAPPEND;
//
//	@Value("${OA-DBFLAG}")
//	private String DBFLAG;
//	private static final Logger logger = (Logger) LoggerFactory.getLogger(BudgetDetailsDaoImpl.class);
//
//	@Override
//	public List<BudgetDetailsOutput> getBudgetDetails(BudgetDetailsInput bdInput) {
//
//		try {
//			logger.info("Inside BudgetDetailsDaoImpl");
//
//		
//			  String query =
//			  "SELECT bi.BUDGET_ITEM_ID, bi.SERVICE_CODE, bi.SERVICE_NAME_EN, bi.TOTAL_COST, bi.VAT_AMOUNT, "
//			  +
//			  "bi.TOTAL_BUDGET, bi.CONSUMED_AMOUNT, bi.BALANCE_AMOUNT , bd.USAGE_EN , bd.BUDGET_PERIOD_CODE FROM "
//			  +DBAPPEND+"OA_BUDGET_ITEMS bi , " +
//			  ""+DBAPPEND+"OA_BUDGET_DETAILS bd, "+DBAPPEND+"OA_BUILDINGS bl, "
//			  +DBAPPEND+"OA_BUILDING_PROPGROUP_MAPPING bpm " +
//			  "WHERE bl.BUILDING_ID=bpm.BUILDING_ID AND bd.PROP_ID=bpm.PROP_ID AND " +
//			  "bi.BUDGET_ID=bd.BUDGET_ID AND bl.BUILDING_ID="+bdInput.getBuildingId()
//			  +" AND " + "bd.MGMT_COMP_ID="+bdInput.getMgmtCompId()
//			  +" AND bd.BUDGET_PERIOD_CODE LIKE('%"+bdInput.getBudgetYear()+"%')" +
//			  " ORDER BY bi.SERVICE_CODE ASC ";
//			 
//
//		/*	String query = "SELECT bi.BUDGET_ITEM_ID, bi.SERVICE_CODE, bi.SERVICE_NAME_EN, bi.TOTAL_COST, bi.VAT_AMOUNT, "
//					+ "bi.TOTAL_BUDGET, bi.CONSUMED_AMOUNT, bi.BALANCE_AMOUNT , bd.USAGE_EN , bd.BUDGET_PERIOD_CODE FROM "
//					+ DBAPPEND + "OA_BUDGET_ITEMS bi , " + "" + DBAPPEND + "OA_BUDGET_DETAILS bd, " + DBAPPEND
//					+ "OA_PROPERTY_GROUPS bl "
//					+ "WHERE bi.BUDGET_ID=bd.BUDGET_ID AND bl.PROP_ID=bd.PROP_ID AND bd.PROP_ID=" + bdInput.getPropId()
//					+ " AND " + "bd.MGMT_COMP_ID=" + bdInput.getMgmtCompId() + " AND bd.BUDGET_PERIOD_CODE LIKE('%"
//					+ bdInput.getBudgetYear() + "%')" + " ORDER BY bi.SERVICE_CODE ASC ";*/
//
//			logger.info("Query For BudgetDetails:::" + query);
//
//			List<BudgetDetailsOutput> bdDetails = jdbcTemplate.query(query,
//					BeanPropertyRowMapper.newInstance(BudgetDetailsOutput.class));
//
//			logger.info("Fetched Budget Details are:: " + bdDetails);
//
//			return bdDetails;
//
//		} catch (Exception e) {
//			logger.info("Exception in  getBudgetDetails in BudgetDetailsDaoImpl " + e.getCause());
//			return null;
//		}
//
//	}
//
//	@Override
//	public void updateBudgetDetails(BudgetDetailsOutput bdOutput) {
//
//		try {
//			logger.info("Calling  updateBudgetDetails() in BudgetDetailsDaoImpl");
//
//			logger.info("Budget Item Id is:: " + bdOutput.getBudgetItemId());
//			String updateQuery = "UPDATE " + DBAPPEND
//					+ "OA_BUDGET_ITEMS SET CONSUMED_AMOUNT=? , BALANCE_AMOUNT=? WHERE BUDGET_ITEM_ID="
//					+ bdOutput.getBudgetItemId() + " ";
//
//			Object[] args = { bdOutput.getConsumedAmount(), bdOutput.getBalanceAmount() };
//			jdbcTemplate.update(updateQuery, args);
//
//			logger.info("Data sucessfully updated in OA_BUDGET_ITEMS in BudgetDetailsDaoImpl");
//
//		} catch (Exception e) {
//			logger.info("Exception in updateBudgetDetails() in BudgetDetailsDaoImpl :: " + e.getCause());
//			e.printStackTrace();
//
//		}
//	}
//
//	@Override
//	public List<Integer> getBudgetYears() {
//		try {
//			logger.info("Inside getBudgetYears of BudgetDetailsDaoImpl");
//
//			String query = "SELECT BUDGET_YEAR FROM " + DBAPPEND + "OA_BUDGET_YEAR";
//
//			logger.info("Query For getBudgetYears:::" + query);
//
//			List<Integer> bdYears = jdbcTemplate.queryForList(query, Integer.class);
//
//			logger.info("Fetched Budget Details are:: " + bdYears);
//
//			return bdYears;
//
//		} catch (Exception e) {
//			logger.info("Exception in  getBudgetDetails in BudgetDetailsDaoImpl " + e.getCause());
//			return null;
//		}
//	}
//
//}
