package com.example.fullstack.maven.springboot.crud.springbootfullstackcrud;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.intuit.craft.networth.model.Asset;
import com.intuit.craft.networth.resource.AssetController;
import com.intuit.craft.networth.service.AssetsService;

import junit.framework.Assert;

@SpringBootTest
@AutoConfigureMockMvc
public class AssetControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AssetsService assetsService;

	@Test
	public void retrieveAllAssets() throws Exception {

		Mockito.when(
				assetsService.findAllAssets()).thenReturn(Arrays.asList( new Asset(1, "Cash", "Chequing", 20000),
						new Asset(2, "Cash", "Taxes", 1500),
						new Asset(3, "LongTerm", "Equipment", 2000)));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/networth/assets").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		System.out.println(result.getResponse());
		String expected = "[{category:Cash,description:Chequing,amount:20000.0,id:1},{category:Cash,description:Taxes,amount:1500.0,id:2},{category:LongTerm,description:Equipment,amount:2000.0,id:3}]";

		// [{"category":"Cash","description":"Chequing","amount":20000.0,"id":1},{"category":"Cash","description":"Taxes","amount":1500.0,"id":2},{"category":"LongTerm","description":"Equipment","amount":2000.0,"id":3}]

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
		Assert.assertEquals(response.getHeader(HttpHeaders.CONTENT_TYPE), MediaType.APPLICATION_JSON_VALUE);
		Assert.assertEquals(Integer.valueOf(HttpStatus.ACCEPTED.value()), Integer.valueOf(response.getStatus()));
		//(response.getHeader(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.APPLICATION_JSON_VALUE));
	}
	
	@Test
	public void addAssetSuccess() throws Exception {
		Asset mockAsset = new Asset(4, "LongTerm", "Home", 987000);
		String exampleAssetJson = "{\"category\":\"LongTerm\",\"description\":\"Home\",\"amount\":987000,\"id\":4}";

		// studentService.addCourse to respond back with mockCourse
		Mockito.when(
				assetsService.addAsset(Mockito.any(Asset.class))).thenReturn(mockAsset);

		// Send asset as body to /networth/assets
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/networth/assets")
				.accept(MediaType.APPLICATION_JSON).content(exampleAssetJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		System.out.println(result.getResponse());
		String expected = "{category:LongTerm,description:Home,amount:987000,id:4}";

		Assert.assertEquals(Integer.valueOf(HttpStatus.CREATED.value()), Integer.valueOf(response.getStatus()));
		Assert.assertEquals(response.getHeader(HttpHeaders.CONTENT_TYPE), MediaType.APPLICATION_JSON_VALUE);
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

	}
	
	@Test
	public void addAssetError() throws Exception {
		// Send asset as body to /networth/assets
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/networth/assets")
				.accept(MediaType.APPLICATION_JSON).content(new String("my asset"))
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		System.out.println(result.getResponse());

		Assert.assertEquals(Integer.valueOf(HttpStatus.BAD_REQUEST.value()), Integer.valueOf(response.getStatus()));
	}
	
	
	@Test
	public void updateAssetSuccess() throws Exception {
		Asset mockAsset = new Asset(3, "LongTerm", "Equipment", 5000);
		String exampleAssetJson = "{\"category\":\"LongTerm\",\"description\":\"Equipment\",\"amount\":5000,\"id\":3}";

		Mockito.when(
				assetsService.updateAmountById(3,5000)).thenReturn(mockAsset);

		// Send asset as body to /networth/assets
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/networth/assets")
				.accept(MediaType.APPLICATION_JSON).content(exampleAssetJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		System.out.println(result.getResponse());
		String expected = "{category:LongTerm,description:Equipment,amount:5000,id:3}";

		Assert.assertEquals(Integer.valueOf(HttpStatus.OK.value()), Integer.valueOf(response.getStatus()));
		Assert.assertEquals(response.getHeader(HttpHeaders.CONTENT_TYPE), MediaType.APPLICATION_JSON_VALUE);
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

	}
	
	
	@Test
	public void updateAssetError() throws Exception {
		Asset mockAsset = new Asset(3, "LongTerm", "Equipment", 2000);
		String exampleAssetJson = "{\"category\":\"LongTerm\",\"description\":\"Equipment\",\"amount\":5000,\"id\":3}";

		Mockito.when(
				assetsService.updateAmountById(Mockito.anyInt(),Mockito.anyDouble())).thenReturn(null);

		// Send asset as body to /networth/assets
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/networth/assets")
				.accept(MediaType.APPLICATION_JSON).content(exampleAssetJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		System.out.println(result.getResponse());
		//String expected = "{category:LongTerm,description:Equipment,amount:2000,id:3}";

		Assert.assertEquals(Integer.valueOf(HttpStatus.NOT_FOUND.value()), Integer.valueOf(response.getStatus()));

		//JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

	}
	
	@Test
	public void deleteAssetSuccess() throws Exception {
		Asset mockAsset = new Asset(3, "LongTerm", "Equipment", 2000);
		String exampleAssetJson = "{\"category\":\"LongTerm\",\"description\":\"Equipment\",\"amount\":5000,\"id\":3}";

		Mockito.when(
				assetsService.deleteById(Mockito.anyInt())).thenReturn(mockAsset);

		// Send asset as body to /networth/assets
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/networth/assets/3")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		System.out.println(result.getResponse());
		String expected = "{category:LongTerm,description:Equipment,amount:2000,id:3}";

		Assert.assertEquals(Integer.valueOf(HttpStatus.OK.value()), Integer.valueOf(response.getStatus()));

		//JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

	}

	
	@Test
	public void deleteAssetError() throws Exception {
		Asset mockAsset = new Asset(3, "LongTerm", "Equipment", 2000);
		String exampleAssetJson = "{\"category\":\"LongTerm\",\"description\":\"Equipment\",\"amount\":2000,\"id\":3}";

		Mockito.when(
				assetsService.deleteById(3)).thenReturn(null);

		// Send asset as body to /networth/assets
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/networth/assets/3")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		System.out.println(result.getResponse());
		//String expected = "{category:LongTerm,description:Equipment,amount:2000,id:3}";

		Assert.assertEquals(Integer.valueOf(HttpStatus.NOT_FOUND.value()), Integer.valueOf(response.getStatus()));

		//JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

	}

}
