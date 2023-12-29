package com.mashreq.oa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashreq.oa.dao.ReversalDAO;
import com.mashreq.oa.entity.AuditTrail;
import com.mashreq.oa.entity.Reversal;

@Service
public class ReversalServiceImpl implements ReversalService 
{
	private static Logger logger = LoggerFactory.getLogger(ReversalServiceImpl.class);

	@Autowired
	private ReversalDAO reversalDao;
	
	@Override
	public AuditTrail getAllAuditTrailTableData(Reversal reversal) 
	{
		AuditTrail getAllAuditData = null;
		try {
			logger.info("inside com.mashreq.oa.service:ReversalServiceImpl class getAllAuditTrailTableData()");
			getAllAuditData=reversalDao.fetchAllAuditTrailData(reversal);
		}
		catch(Exception e) 
		{
			e.printStackTrace();	
			logger.error("com.mashreq.oa.service.:ReversalServiceImpl class known DB exception is raised in getAllAuditTrailTableData() method"+e.getMessage());
		}
		return getAllAuditData;
	}

}
