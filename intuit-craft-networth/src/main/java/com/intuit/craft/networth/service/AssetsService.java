package com.intuit.craft.networth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.intuit.craft.networth.model.Asset;

@Service
public class AssetsService {
	private static List<Asset> assets = new ArrayList<>();
	private static final String ASSET_CATEGORY_CASH_AND_INVESTMENTS = "Cash and Investments";
	private static final String ASSET_CATEGORY_LONG_TERM_ASSETS = "Long Term Assets";
	private static int idCounter = 0;

	  static {
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Chequing", 2000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Savings for Taxes", 4000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Rainy Day Fund", 506));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Savings for Fun", 5000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Savings for Travel", 2000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Savings for Personal Development", 2000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Investment 1", 2000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Investment 2", 2000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Investment 3", 2000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Investment 4", 2000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_CASH_AND_INVESTMENTS, "Investment 5", 2000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_LONG_TERM_ASSETS, "Primary Home", 2000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_LONG_TERM_ASSETS, "Secondary Home", 2000));
		  assets.add(new Asset(++idCounter, ASSET_CATEGORY_LONG_TERM_ASSETS, "Other", 2000));
	  }

	  public List<Asset> findAllAssets() {
		  return assets;
	  }
	  
	  	  
	  public Asset findAssetById(int id) {
		  for (Asset asset: assets) {
		    if (asset.getId() == id) {
		      return asset;
		    }
		  }
		  return null;
	  }
	  
	  public Asset updateAmountById(int id, double amount) {
		    Asset asset = findAssetById(id);

		    if (asset == null)
		      return null;
		    int index = assets.indexOf(asset);
		    asset.setAmount(amount);
		    assets.set(index, asset);		    	
		    return asset;
	  }
	  
	  public Asset deleteById(int id) {
		    Asset asset = findAssetById(id);

		    if (asset == null)
		      return null;

		    if (assets.remove(asset)) {
		      return asset;
		    }

		    return null;
	}
	  
	  public Asset addAsset(Asset input) {
		  Asset add = new Asset(++idCounter, input.getCategory(), input.getDescription(), input.getAmount());
		  assets.add(add);		    	
		  return add;
	  }

}
