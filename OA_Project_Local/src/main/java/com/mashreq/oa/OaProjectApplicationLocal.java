package com.mashreq.oa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OaProjectApplicationLocal {
	private static final Logger logger = LoggerFactory.getLogger(OaProjectApplicationLocal.class);

	public static void main(String[] args) {
		SpringApplication.run(OaProjectApplicationLocal.class, args);
		logger.info("OA_Project_Local has been Stated Successfully..");
	}

}
