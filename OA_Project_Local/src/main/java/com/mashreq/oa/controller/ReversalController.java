package com.mashreq.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashreq.oa.entity.AuditTrail;
import com.mashreq.oa.entity.Reversal;
import com.mashreq.oa.service.ReversalService;

@RestController
@RequestMapping("/reversalRequest")
public class ReversalController 
{
	private static Logger logger = LoggerFactory.getLogger(ReversalController.class);
	
	@Autowired
	private ReversalService reversalService;
	
	@GetMapping("/fetchAuditTrailData")
	public AuditTrail fetchAllAuditData(@RequestBody Reversal inputs) 
	{
		System.out.println("inputs:"+inputs);
		AuditTrail response = null;
		try {
			logger.info("inside com.mashreq.oa.controller.ReversalController class having fetchAllAuditData()");
			response = reversalService.getAllAuditTrailTableData(inputs);
			logger.info("response:"+response);
			System.out.println("Response:"+response);
		} 
		catch (DataAccessException dae) 
		{
			dae.printStackTrace();
			logger.error("com.mashreq.oa.controller:ReversalController class known DB exception is raised in fetchAllAuditData() method"+dae.getMessage());
		}
		return response;
	}
}
