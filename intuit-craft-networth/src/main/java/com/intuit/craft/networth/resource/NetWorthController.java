package com.intuit.craft.networth.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craft.networth.model.NetWorthCalculation;
import com.intuit.craft.networth.service.NetWorthService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class NetWorthController {
	
	  @Autowired
	  private NetWorthService netWorthManagementService;

	  @GetMapping("/networth")
	  public NetWorthCalculation getNetWorth() {
	    return netWorthManagementService.getNetWorth();
	  }
}
