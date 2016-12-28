package com.basecamp.guestbook;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/mybatis-config.xml"})
@WebAppConfiguration
public class WriteControllerTest {
	@Autowired
	private WriteController writeController;
	@Autowired
	HttpServletRequest request;
	
	/**
	 * Test whether WriteController.execute() returns correct address. 
	 */
	@Test
	public void executeTest() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String result = writeController.execute(model,request);
		assertEquals(result,"write");
	}
}