package com.mashreq.oa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DMartShopController {

	@GetMapping("/buy")
	public String buy()
	{
		return "Customer can buy product";
	}
	
	@GetMapping("/visit")
	public String visit()
	{
		return "Customer can visit shoppers";
	}
	
	@GetMapping("/details")
	public String customersDetails()
	{
		return "Admin can see customers details through his transaction";
	}
}
