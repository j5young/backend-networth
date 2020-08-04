package com.intuit.craft.networth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.intuit.craft.networth.model.Liability;

@Service
public class LiabilitiesService {
	private static List<Liability> liabilities = new ArrayList<>();
	private static final String LIABILITY_CATEGORY_SHORT_TERM = "Short Term Liabilities";
	private static final String LIABILITY_CATEGORY_LONG_TERM = "Long Term Liabilities";
	private static int idCounter = 0;

	  static {
		  liabilities.add(new Liability(++idCounter, LIABILITY_CATEGORY_SHORT_TERM, "Credit Card 1", 4342));
		  liabilities.add(new Liability(++idCounter, LIABILITY_CATEGORY_SHORT_TERM, "Credit Card 2", 322));
		  liabilities.add(new Liability(++idCounter, LIABILITY_CATEGORY_LONG_TERM, "Mortgage 1", 250999));
		  liabilities.add(new Liability(++idCounter, LIABILITY_CATEGORY_LONG_TERM, "Mortgage 2", 632634));
		  liabilities.add(new Liability(++idCounter, LIABILITY_CATEGORY_LONG_TERM, "Line of Credit", 10000));
		  liabilities.add(new Liability(++idCounter, LIABILITY_CATEGORY_LONG_TERM, "Investment Loan", 10000));
		  liabilities.add(new Liability(++idCounter, LIABILITY_CATEGORY_LONG_TERM, "Student Loan", 322));
		  liabilities.add(new Liability(++idCounter, LIABILITY_CATEGORY_LONG_TERM, "Car Loan", 322));
	  }


	  public List<Liability> findAllLiabilities() {
		  return liabilities;
	  }
	  	  
	  public Liability findLiabilityById(int id) {
		  for (Liability liability: liabilities) {
		    if (liability.getId() == id) {
		      return liability;
		    }
		  }
		  return null;
	  }
	  
	  public Liability updateAmountById(int id, double amount) {
		  	Liability liability = findLiabilityById(id);

		    if (liability == null)
		      return null;
		    int index = liabilities.indexOf(liability);
		    liability.setAmount(amount);
		    liabilities.set(index, liability);		    	
		    return liability;
	  }
	  
	  public Liability addLiability(Liability input) {
		  Liability add = new Liability(++idCounter, input.getCategory(), input.getDescription(), input.getAmount());
		  liabilities.add(add);		    	
		  return add;
	  }

	  public Liability deleteById(int id) {
		    Liability liability = findLiabilityById(id);

		    if (liability == null)
		      return null;

		    if (liabilities.remove(liability)) {
		      return liability;
		    }

		    return null;
	}
}
