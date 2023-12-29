package com.mashreq.oa.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashreq.oa.entity.BudgetDetailsOutput;
import com.mashreq.oa.entity.PaymentData;
import com.mashreq.oa.entity.Reversal;
import com.mashreq.oa.model.AuditTrailLog;
import com.mashreq.oa.service.BudgetDetailsUpdateService;

@RestController
@RequestMapping("/reversalRequest")
@CrossOrigin
public class BudgetDetailsUpdateController 
{
	private static Logger logger = LoggerFactory.getLogger(BudgetDetailsUpdateController.class);
	
	@Autowired
	private BudgetDetailsUpdateService service;
	
	@PostMapping("/updateBudgetItems")
    public ResponseEntity<BudgetDetailsOutput> updateBudgetItems(@RequestBody Reversal reversal,@RequestHeader Map<String,String> headers) 
	{
		logger.info("BudgetDetailsUpdateController class updateBudgetItems()-Reversal Data: "+reversal);
       
        try 
        {
        	logger.info("Calling updateBudgetDetails() in BudgetDetailsController");
        	String username=headers.get("username");
        	System.out.println("username:"+username);
        	BudgetDetailsOutput data = service.updateBudgetItems(reversal,username);

            if (data != null) 
            {
                return new ResponseEntity<>(data, HttpStatus.OK);
            } 
            else 
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PutMapping("/updateServiceCode")
    public ResponseEntity<BudgetDetailsOutput> updateServiceCode(@RequestBody Reversal reversalEdit,@RequestHeader Map<String,String> headers) 
	{
		logger.info("pymtReqId:"+reversalEdit.getPaymentId());
		logger.info("newServiceCode:"+reversalEdit.getNewServiceCode());
		logger.info("invoiceAmount:"+reversalEdit.getInvoiceAmount());
		
		logger.info("Calling updateServiceCode() in BudgetDetailsUpdateController");
        try 
        {
        	String username=headers.get("username");
        	System.out.println("username:"+username);
        	BudgetDetailsOutput data = service.updateServiceCode(reversalEdit,username);
        	logger.info("data:"+data);

            if (data != null) 
            {
                return new ResponseEntity<>(data, HttpStatus.OK);
            } 
            else 
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PostMapping("/fetchAuditTrail")
    public ResponseEntity<List<AuditTrailLog>> fetchAuditTrailLog(@RequestParam String serviceCode,@RequestParam String userName,@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date updatedOn) 
	{
		List<AuditTrailLog> data = null;
		logger.info("BudgetDetailsUpdateController class fetchAuditTrailLog()-Audit Data: ");
		System.out.println("serviceCode:"+serviceCode);
		System.out.println("userName:"+userName);
		System.out.println("updatedOn:"+updatedOn);
		
		// Convert java.util.Date to java.sql.Timestamp
        try 
        {

            data = service.getDataFromAuditTrailLog(serviceCode, userName, updatedOn);

            if (data != null && !data.isEmpty())
            {
                return new ResponseEntity<>(data, HttpStatus.OK);
            } 
            else 
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // or HttpStatus.NOT_FOUND 
            }
        } 
        catch (Exception e) 
        {
            // handle Exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PostMapping("/fetchAuditTrail2")
    public ResponseEntity<List<AuditTrailLog>> fetchAuditTrailLog2(@RequestParam String serviceCode,@RequestParam String userName,@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date updatedFrom,@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date updatedTo) 
	{
		List<AuditTrailLog> data = null;
		logger.info("BudgetDetailsUpdateController class fetchAuditTrailLog()-Audit Data: ");
		System.out.println("serviceCode:"+serviceCode);
		System.out.println("userName:"+userName);
		System.out.println("updatedFrom:"+updatedFrom);
		System.out.println("updatedTo:"+updatedTo);
		
		// Convert java.util.Date to java.sql.Timestamp
        try 
        {

            data = service.getDataFromAuditTrailLog2(serviceCode, userName, updatedFrom,updatedTo);

            if (data != null && !data.isEmpty())
            {
                return new ResponseEntity<>(data, HttpStatus.OK);
            } 
            else 
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // or HttpStatus.NOT_FOUND 
            }
        } 
        catch (Exception e) 
        {
            // handle Exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/getPaymentData")
	public ResponseEntity<List<PaymentData>> getPaymentData()
	{
		
		logger.info("Calling getPaymentData() in BudgetDetailsUpdateController");
        try 
        {
        	List<PaymentData> list = service.getListData();

            if (list != null) 
            {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } 
            else 
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	
	
}
