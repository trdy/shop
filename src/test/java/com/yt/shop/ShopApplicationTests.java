package com.yt.shop;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringApplicationConfiguration(classes = ShopApplication.class)
@WebAppConfiguration
public class ShopApplicationTests {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void contextLoads() {
	}

	/**
	 *
	 mvc.perform(MockMvcRequestBuilders.get("/test")
	 .contentType(MediaType.APPLICATION_JSON_UTF8)
	 .param("lat", "123.123").param("lon", "456.456")
	 .accept(MediaType.APPLICATION_JSON))
	 .andExpect(MockMvcResultMatchers.status().isOk())
	 .andDo(MockMvcResultHandlers.print())
	 .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));
	 */

	@Test
	public void test1() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/test")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));

	}

}
