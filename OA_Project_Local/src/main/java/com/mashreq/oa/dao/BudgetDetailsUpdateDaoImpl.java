package com.mashreq.oa.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mashreq.oa.entity.AuditTrail;
import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.entity.PaymentData;
import com.mashreq.oa.entity.Reversal;
import com.mashreq.oa.model.AuditTrailLog;

@Repository
public class BudgetDetailsUpdateDaoImpl implements BudgetDetailsUpdateDao {
	private static Logger logger = LoggerFactory.getLogger(BudgetDetailsUpdateDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AuditTrail> getAuditTrailRecords(Reversal reversal) {
		// Implement logic to fetch audit trail records based on payment ID using
		// jdbcTemplate
		List<AuditTrail> auditTrailFetchedData = null;
		String FETCH_QUERY = "select FIELDNAME,ID,OLDVALUE,NEWVALUE,UPDATEDON,UPDATEDBY,SERVICECODE,PYMT_REQ_ID from OA_AUDIT_TRAIL where PYMT_REQ_ID=?";
		Object[] args = { reversal.getPaymentId() };
		try {
			logger.info("inside com.mashreq.oa.dao.BudgetDetailsUpdateDaoImpl class having getAuditTrailRecords()");
			if (reversal.getPaymentId() != null) {
				logger.info("reversal.getPaymentId is: " + reversal.getPaymentId());
				if (reversal.getPaymentId() != null) {
					Object[] args1 = { reversal.getPaymentId() };
					args = args1;
				}
				auditTrailFetchedData = jdbcTemplate.query(FETCH_QUERY,
						BeanPropertyRowMapper.newInstance(AuditTrail.class), args); // this is for List<AuditTrail>
				// auditTrailFetchedData = jdbcTemplate.queryForObject(FETCH_QUERY, new BeanPropertyRowMapper<AuditTrail>(AuditTrail.class),args); //this is for"AuditTrail"
				logger.info("auditTrailFetchData:" + auditTrailFetchedData);
				logger.info(
						"BudgetDetailsUpdateDaoImpl.getAuditTrailRecords() Audit trail table data fetched successfully");
			} else {
				logger.info(
						"BudgetDetailsUpdateDaoImpl.getAuditTrailRecords() Audit trail table data not fetched becoz paytMentId is Null");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"com.mashreq.oa.dao:BudgetDetailsUpdateDaoImpl class known DB exception is raised in getAuditTrailRecords() method:"
							+ e.getMessage());
		}
		return auditTrailFetchedData;
	}

	@Override
	public List<BudgetDetailsOutput> getBudgetItemById(int budgetItemId) {
		logger.info("inside com.mashreq.oa.dao:BudgetDetailsUpdateDaoImpl class having getBudgetItemById() method");
		// Implement logic to fetch BudgetItem based on budgetItemId using jdbcTemplate
		List<BudgetDetailsOutput> budgetItemData = null;
		Object[] args = { budgetItemId };
		String sql = "SELECT BUDGET_ITEM_ID,SERVICE_CODE,SERVICE_NAME_EN,TOTAL_COST,VAT_AMOUNT,TOTAL_BUDGET,CONSUMED_AMOUNT,BALANCE_AMOUNT,IS_EDIT_MODE FROM OA_BUDGET_ITEMS WHERE budget_item_id = ?";
		//budgetItemData = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<BudgetDetailsOutput>(BudgetDetailsOutput.class), args);//for single
		budgetItemData = jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(BudgetDetailsOutput.class),args);;
		logger.info("fetched data from oa_budget_items table by using bugetItemId: " + budgetItemData);
		return budgetItemData;
	}

	@Override
	public void updateBudgetItemTable(BudgetDetailsOutput budgetItem) {
		logger.info("inside com.mashreq.oa.dao:BudgetDetailsUpdateDaoImpl class having updateBudgetItemTable() method");
		// Implement logic to update BudgetItem using jdbcTemplate
		String sql = "UPDATE OA_BUDGET_ITEMS SET service_code = ?,consumed_amount = ?, balance_amount = ? WHERE budget_item_id = ?";
		jdbcTemplate.update(sql, budgetItem.getServiceCode(),budgetItem.getConsumedAmount(), budgetItem.getBalanceAmount(),budgetItem.getBudgetItemId());
		//return new BudgetDetailsOutput(budgetItem.getConsumedAmount(),budgetItem.getBalanceAmount(),budgetItem.getBudgetItemId());
	}
	
	@Override
	public void updateBudgetItemTableWithServiceCode(BudgetDetailsOutput budgetItem) {
		logger.info("inside com.mashreq.oa.dao:BudgetDetailsUpdateDaoImpl class having updateBudgetItemTableWithServiceCode() method");
		// Implement logic to update BudgetItem using jdbcTemplate
		String sql = "UPDATE OA_BUDGET_ITEMS SET consumed_amount = ?, balance_amount = ? ,service_code=? WHERE budget_item_id = ?";
		jdbcTemplate.update(sql, budgetItem.getConsumedAmount(), budgetItem.getBalanceAmount(),budgetItem.getServiceCode(),budgetItem.getBudgetItemId());
	}
	
	@Override
	public void logAuditTrail(BudgetDetailsOutput budgetItemsData, AuditTrail auditTrail,Double amount,String username, String comment) 
	{
		logger.info("inside com.mashreq.oa.dao:BudgetDetailsDaoImpl class having logAuditTrail() method");
        //insert into OA_AUDIT_TRAIL_LOG using jdbcTemplate  (here COMMENT is a reserved keyword in database so we are taking as "COMMENT")
        String sql = "INSERT INTO OA_AUDIT_TRAIL_LOG (ID, PYMT_REQ_ID, SERVICE_CODE, REVERSAL_AMOUNT, UPDATEDBY, UPDATEDON, \"COMMENT\")VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Timestamp updatedOn = new Timestamp(System.currentTimeMillis()); //currentDateTime
//        jdbcTemplate.update(sql,auditTrail.getId(), auditTrail.getPymtReqId(),budgetItemsData.getServiceCode(),amount,username,auditTrail.getUpdatedOn(),comment);
        jdbcTemplate.update(sql,auditTrail.getId(), auditTrail.getPymtReqId(),budgetItemsData.getServiceCode(),amount,username,updatedOn,comment);
    }
	

	@Override
	public void logAuditTrailWithServiceCode(BudgetDetailsOutput budgetItemsData, AuditTrail auditTrail,Double amount) 
	{
		logger.info("inside com.mashreq.oa.dao:BudgetDetailsDaoImpl class having logAuditTrailWithServiceCode() method");
        //insert into OA_AUDIT_TRAIL_LOG using jdbcTemplate  (here COMMENT is a reserved keyword in database so we are taking as "COMMENT")
        String sql = "INSERT INTO OA_AUDIT_TRAIL_LOG (ID,PYMT_REQ_ID, SERVICE_CODE, REVERSAL_AMOUNT)VALUES (?,?, ?, ?)";
        jdbcTemplate.update(sql,auditTrail.getId(),auditTrail.getPymtReqId(),budgetItemsData.getServiceCode(),amount);
    }
	
	@Override
	public AuditTrailLog updateServiceCode(String pymtReqId, String newServiceCode) 
	{
		logger.info("BudgetDetailsUpdateDaoImpl class having updateServiceCode() method ");
		String updateQuery = "UPDATE OA_AUDIT_TRAIL_LOG SET SERVICE_CODE = ? WHERE PYMT_REQ_ID = ?";
		try {
			jdbcTemplate.update(updateQuery, newServiceCode, pymtReqId);

		} catch (Exception e) {
			logger.info("Inside updateServiceCode() catch");
			logger.info(e.getMessage() + " == " + e.getCause());
		}
		// return getAuditTrailLogById(pymtReqId);
		return null;
	}

	@Override
	public List<AuditTrailLog> fetchAuditTrailLog(String serviceCode, String userName, Date updatedOn) {
		List<AuditTrailLog> fetchedListData = null;
		logger.info("BudgetDetailsUpdateDaoImpl class having fetchAuditTrailLog() method ");
		String sql = "SELECT ID as budgetItemId,PYMT_REQ_ID,SERVICE_CODE,REVERSAL_AMOUNT,UPDATEDBY,UPDATEDON,\"COMMENT\" FROM OA_AUDIT_TRAIL_LOG WHERE SERVICE_CODE = ? AND UPDATEDBY = ? AND UPDATEDON >= ?";

		// Convert java.util.Date to java.sql.Timestamp
		// Timestamp updatedOnn = new Timestamp(updatedOn.getTime());
		// System.out.println("updatedOnn:"+updatedOnn);

		Calendar calendarOn = Calendar.getInstance();
		calendarOn.setTime(updatedOn);
		java.sql.Date updatedOnn = new java.sql.Date(calendarOn.getTimeInMillis());

		try {
			fetchedListData = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AuditTrailLog.class),
					new Object[] { serviceCode, userName, updatedOnn });
			logger.info("BudgetDetailsUpdateDaoImpl class having fetchAuditTrailLog() method fetchedListData: "
					+ fetchedListData);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			logger.error(">>>>>>" + dae.getMessage());
		}
		return fetchedListData;
	}

	@Override
	public List<AuditTrailLog> fetchAuditTrailLog2(String serviceCode, String userName, Date updatedFrom,
			Date updatedTo) {
		List<AuditTrailLog> fetchedListData = null;
		logger.info("BudgetDetailsUpdateDaoImpl class having fetchAuditTrailLog() method ");

		/*
		String sql = "SELECT ID as budgetItemId,PYMT_REQ_ID,SERVICE_CODE,REVERSAL_AMOUNT,UPDATEDBY,UPDATEDON,\"COMMENT\" FROM OA_AUDIT_TRAIL_LOG "
				+ "WHERE SERVICE_CODE = ? AND UPDATEDBY = ? AND UPDATEDON BETWEEN TO_DATE(?,'dd-MM-yy') AND TO_DATE(?,'dd-MM-yy')";   //ALSO WORKING
	
		*/
		
		String sql = "SELECT ID as budgetItemId,PYMT_REQ_ID,SERVICE_CODE,REVERSAL_AMOUNT,UPDATEDBY,UPDATEDON,\"COMMENT\" FROM OA_AUDIT_TRAIL_LOG "
				+ "WHERE SERVICE_CODE = ? AND UPDATEDBY = ? AND UPDATEDON >= TO_DATE(?,'dd-MM-yy') AND UPDATEDON < TO_DATE(?,'dd-MM-yy')";
		
		// Convert java.util.Date to java.sql.Timestamp
		// Timestamp updatedOnn = new Timestamp(updatedOn.getTime());
		// System.out.println("updatedOnn:"+updatedOnn);

//		 Calendar calendarFrom = Calendar.getInstance();
//	     calendarFrom.setTime(updatedFrom);
//	     java.sql.Date updatedFromm = new java.sql.Date(updatedFrom.getTime());
//	     
//	     Calendar calendarTo = Calendar.getInstance();
//	     calendarTo.setTime(updatedTo);
//	     java.sql.Date updatedToo = new java.sql.Date(updatedTo.getTime());

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
		String newUpdatedFrom = formatter.format(updatedFrom);
		System.out.println(newUpdatedFrom);

		String newUpdatedTo = formatter.format(updatedTo);
		System.out.println(newUpdatedTo);
		
		String updatedBy = userName; 

		try {
			fetchedListData = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AuditTrailLog.class),
					new Object[] { serviceCode, userName, newUpdatedFrom, newUpdatedTo });
			logger.info("BudgetDetailsUpdateDaoImpl class having fetchAuditTrailLog() method fetchedListData: "
					+ fetchedListData);
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			logger.error(">>>>>>" + dae.getMessage());
		}

		return fetchedListData;
	}

	@Override
	public List<PaymentData> getPaymentRequests() {
		List<PaymentData> compReqs = null;
		try {

			String sql = "SELECT PYMT_REQ_ID, MATRIX_REF_NO, SUB_PRODUCT, DEBIT_ACCOUNT_NUMBER_DESC,BENEFICIARY_NAME, INITIATOR_DATE, PAYMENT_CURRENCY,SUPPLIER_ID,\r\n"
					+ "INVOICE_AMOUNT,BUDGET_YEAR,SERVICE_CODE,MGMT_COMP_ID,BUILDING_ID,BIFURCATION, CUSTOMER_REFERENCE, INITIATOR_NAME_DATE_TIME, STATUS, REMARKS \r\n"
					+ "FROM oa_payment_requests WHERE STATUS IN('APPROVED','REJECTED')";

			compReqs = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(PaymentData.class));
		} catch (Exception e) {
			logger.info("Inside getCompletedRequests() catch");
			logger.info(e.getMessage() + " == " + e.getCause());
		}
		return compReqs;
	}

	@Override
	public List<AuditTrailLog> getAuditTrailLogById(String pymtReqId) {
		logger.info("BudgetDetailsUpdateDaoImpl class having getAuditTrailLogById() method ");
		List<AuditTrailLog> auditTrailLog = null;
		Object[] args = { pymtReqId };
		String query = "SELECT PYMT_REQ_ID,SERVICE_CODE FROM OA_AUDIT_TRAIL_LOG WHERE PYMT_REQ_ID = ?";

		try {
			auditTrailLog = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(AuditTrailLog.class), args);
			logger.info("BudgetDetailsUpdateDaoImpl class having getAuditTrailLogById() method fetchedData: "
					+ auditTrailLog);
		} catch (EmptyResultDataAccessException e) {
			logger.info("Inside getAuditTrailLogById() catch - No data found for pymtReqId: " + pymtReqId);
		} catch (Exception e) {
			logger.error("Error in getAuditTrailLogById()", e);
			logger.info("lOGGER ERROR MSG: " + e.getMessage() + " == " + e.getCause());
		}
		return auditTrailLog;
	}


}