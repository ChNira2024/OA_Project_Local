package com.mashreq.oa.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AuditInput 
{
	private String serviceCode;
	private String userName;
	
	@DateTimeFormat(pattern = "dd-MM-yy")
	private Date updatedOn;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "AuditInput [serviceCode=" + serviceCode + ", userName=" + userName + ", updatedOn=" + updatedOn + "]";
	}
	
	
	

}
