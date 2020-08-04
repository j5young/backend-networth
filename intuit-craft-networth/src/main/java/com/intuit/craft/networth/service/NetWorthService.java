package com.intuit.craft.networth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.craft.networth.model.Asset;
import com.intuit.craft.networth.model.Liability;
import com.intuit.craft.networth.model.NetWorthCalculation;

@Service
public class NetWorthService {
	
	@Autowired
	private AssetsService assetManagementService;
	
	@Autowired
	private LiabilitiesService liabilityManagementService;

	public NetWorthCalculation getNetWorth() {
		
		List<Asset> allAssets = assetManagementService.findAllAssets();
		double totalAssets = allAssets.stream().mapToDouble(asset -> asset.getAmount()).sum();
		NetWorthCalculation netWorth = new NetWorthCalculation();
		netWorth.setTotalAssets(totalAssets);
		
		List<Liability> allLiabilities = liabilityManagementService.findAllLiabilities();
		double totalLiabilities = allLiabilities.stream().mapToDouble(liability -> liability.getAmount()).sum();
		netWorth.setTotalLiabilities(totalLiabilities);
		
		netWorth.setNetWorth(totalAssets-totalLiabilities);
		
        return netWorth;
	}

}
