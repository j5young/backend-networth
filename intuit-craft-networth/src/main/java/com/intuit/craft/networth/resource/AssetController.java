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

import com.intuit.craft.networth.model.Asset;
import com.intuit.craft.networth.service.AssetsService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class AssetController {
	
	  @Autowired
	  private AssetsService assetManagementService;
	  
	  @PostMapping("/networth/assets")
	  public ResponseEntity<Asset> addAsset(@RequestBody Asset assetInput) {
		  Asset asset = new Asset();

		  asset = assetManagementService.addAsset(assetInput);
		  //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(asset.getId())
			//        .toUri();
		  if(asset != null)
			  return new ResponseEntity<Asset>(asset, HttpStatus.CREATED);
		  return ResponseEntity.badRequest().build();
	  }

	  @GetMapping("/networth/assets")
	  public List<Asset> getAllAssets() {
	    return assetManagementService.findAllAssets();
	  }
	  
	  @PutMapping("/networth/assets")
	  public ResponseEntity<Asset> updateAssetAmount(@RequestBody Asset assetInput) {

	    Asset asset = assetManagementService.updateAmountById(assetInput.getId(),assetInput.getAmount());

	    if(asset != null)
	    	return new ResponseEntity<Asset>(asset, HttpStatus.OK);
	    
	    return ResponseEntity.notFound().build();
	  }
	  
	  @DeleteMapping("/networth/assets/{id}")
	  public ResponseEntity<Void> deleteLiability(@PathVariable int id) {

	    Asset asset = assetManagementService.deleteById(id);

	    if (asset != null) {
	      return ResponseEntity.ok().build();
	    }

	    return ResponseEntity.notFound().build();
	  }
}
