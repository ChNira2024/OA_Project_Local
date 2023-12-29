package com.mashreq.oa.dao;

import com.mashreq.oa.entity.AuditTrail;
import com.mashreq.oa.entity.Reversal;

public interface ReversalDAO 
{
	public AuditTrail fetchAllAuditTrailData(Reversal reversal);

}
