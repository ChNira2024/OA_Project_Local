package com.mashreq.oa.entity;

public class AuditTrailEdit 
{

	private Long pymtReqId;
    private String serviceCode;
	public Long getPymtReqId() {
		return pymtReqId;
	}
	public void setPymtReqId(Long pymtReqId) {
		this.pymtReqId = pymtReqId;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	@Override
	public String toString() {
		return "AuditTrailEdit [pymtReqId=" + pymtReqId + ", serviceCode=" + serviceCode + "]";
	}
    
    
}
