package com.example.fullstack.maven.springboot.crud.springbootfullstackcrud;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.intuit.craft.networth.model.Asset;
import com.intuit.craft.networth.model.Liability;
import com.intuit.craft.networth.model.NetWorthCalculation;
import com.intuit.craft.networth.service.AssetsService;
import com.intuit.craft.networth.service.LiabilitiesService;

@SpringBootTest
@AutoConfigureMockMvc
public class NetWorthServiceTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AssetsService assetsService;
	
	@MockBean
	private LiabilitiesService liabilitiesService;

	@Test
	public void retrieveNetWorthSuccess() throws Exception {
		
		NetWorthCalculation netWorth = new NetWorthCalculation(203500,106500,97000);

		Mockito.when(
				assetsService.findAllAssets()).thenReturn(Arrays.asList( new Asset(1, "Cash", "Chequing", 200000),
						new Asset(2, "Cash", "Taxes", 1500),
						new Asset(3, "LongTerm", "Equipment", 2000)));

		
		Mockito.when(
				liabilitiesService.findAllLiabilities()).thenReturn(Arrays.asList( new Liability(1, "ShortTerm", "Mastercard", 5000),
						new Liability(2, "ShortTerm", "Visa", 1500),
						new Liability(3, "LongTerm", "Mortgage1", 100000)));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/networth").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		

		System.out.println(result.getResponse());
		String expected = "{totalAssets:203500,totalLiabilities:106500,netWorth:97000}";

		// [{"category":"Cash","description":"Chequing","amount":20000.0,"id":1},{"category":"Cash","description":"Taxes","amount":1500.0,"id":2},{"category":"LongTerm","description":"Equipment","amount":2000.0,"id":3}]

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void retrieveNetWorthEmpty() throws Exception {
		
		NetWorthCalculation netWorth = new NetWorthCalculation(203500,106500,97000);

		Mockito.when(
				assetsService.findAllAssets()).thenReturn(Collections.EMPTY_LIST);

		
		Mockito.when(
				liabilitiesService.findAllLiabilities()).thenReturn(Collections.EMPTY_LIST);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/networth").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		

		System.out.println(result.getResponse());
		String expected = "{totalAssets:0,totalLiabilities:0,netWorth:0}";

		// [{"category":"Cash","description":"Chequing","amount":20000.0,"id":1},{"category":"Cash","description":"Taxes","amount":1500.0,"id":2},{"category":"LongTerm","description":"Equipment","amount":2000.0,"id":3}]

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
