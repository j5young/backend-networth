package com.intuit.craft.networth.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craft.networth.model.Liability;
import com.intuit.craft.networth.service.LiabilitiesService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class LiabilityController {
	
	public static final String SHORT_TERM = "short";
	public static final String LONG_TERM = "long";
	
	  @Autowired
	  private LiabilitiesService liabilityManagementService;
	  
	  @PostMapping("/networth/liabilities")
	  public ResponseEntity<Liability> addLiability(@RequestBody Liability liabilityInput) {
		  Liability liability = new Liability();

		  liability = liabilityManagementService.addLiability(liabilityInput);
	    
		  return new ResponseEntity<Liability>(liability, HttpStatus.CREATED);
	  }

	  @GetMapping("/networth/liabilities")
	  public List<Liability> getAllLiabilities() {
		  return liabilityManagementService.findAllLiabilities();
	  }
	  
	  @PutMapping("/networth/liabilities")
	  public ResponseEntity<Liability> updateLiabilityAmount(@RequestBody Liability liabilityInput) {
		  Liability liability = new Liability();

		  liability = liabilityManagementService.updateAmountById(liabilityInput.getId(),liabilityInput.getAmount());
	    
		  return new ResponseEntity<Liability>(liability, HttpStatus.OK);
	  }

	  
	  @DeleteMapping("/networth/liabilities/{id}")
	  public ResponseEntity<Void> deleteLiability(@PathVariable int id) {

	    Liability liability = liabilityManagementService.deleteById(id);

	    if (liability != null) {
	      return ResponseEntity.noContent().build();
	    }

	    return ResponseEntity.notFound().build();
	  }
}
