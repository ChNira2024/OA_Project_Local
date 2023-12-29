package com.mashreq.oa.dao;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mashreq.oa.entity.AuditTrail;
import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.entity.Reversal;

@Repository
public class ReversalDaoImpl implements ReversalDAO 
{

	private static Logger logger = LoggerFactory.getLogger(ReversalDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jt;
	
	@Override
	public AuditTrail fetchAllAuditTrailData(Reversal reversal) 
	{
		AuditTrail auditTrailFetchedData = null;
		String FETCH_QUERY = "select FIELDNAME,ID,OLDVALUE,NEWVALUE,UPDATEDON,UPDATEDBY,PYMT_REQ_ID from OA_AUDIT_TRAIL where PYMT_REQ_ID=?";
		try 
		{
			logger.info("inside com.mashreq.oa.dao.ReversalDaoImpl class having fetchAllAuditTrailData()");
			if(reversal.getPaymentId()!=null)
			{
				logger.info("reversal.getPaymentId is: "+reversal.getPaymentId());
			auditTrailFetchedData = jt.queryForObject(FETCH_QUERY,new BeanPropertyRowMapper<AuditTrail>(AuditTrail.class), reversal.getPaymentId());
			logger.info("auditTrailFetchData:"+auditTrailFetchedData);
			logger.info("ReversalDaoImpl.fetchAllAuditTrailData() Audit trail table data fetched successfully");
			String newValue = auditTrailFetchedData.getNewValue();
			String oldValue = auditTrailFetchedData.getOldValue();
			if (newValue != null && oldValue != null) 
			{
				try
				{
					//BigDecimal newBigDecimalValue = new BigDecimal(newValue);
					//BigDecimal oldBigDecimalValue = new BigDecimal(oldValue);
				   // String consumedAmount =  newBigDecimalValue.subtract(oldBigDecimalValue).toString();
					
					//convert String to int for substraction
					int updNewValue = Integer.parseInt(newValue);
					int updOldValue = Integer.parseInt(oldValue);
					
					//convert int to String(toString() we can't use becoz int doesnot have toString()
					String consumedAmount =  String.valueOf(updNewValue - updOldValue);
				    logger.info("ConsumedAmout:"+consumedAmount);
				    
				 Double ca =   Double.parseDouble(consumedAmount);
				 Double inVoiceAmount = reversal.getInvoiceAmount();
				 if(inVoiceAmount == ca)
				 {
					 //auditTrailFetchedData.getId();
					 BudgetDetailsOutput bdo = new BudgetDetailsOutput();
					 bdo.setConsumedAmount(ca);
				 }
				}
				catch (NumberFormatException e) 
				{
	                e.printStackTrace();
	            }
			}
			else
			{
				return null;
			}
			}
			else
			{
				logger.info("ReversalDaoImpl.fetchAllAuditTrailData() Audit trail table data not fetched becoz paytMentId is Null");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error("com.mashreq.oa.dao:ReversalDaoImpl class known DB exception is raised in fetchAllAuditTrailData() method:"+e.getMessage());
		}
		
		return auditTrailFetchedData;
	}

}
