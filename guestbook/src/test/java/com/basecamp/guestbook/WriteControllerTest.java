package com.basecamp.guestbook;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
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
	
	/**
	 * Test whether WriteController.execute() returns correct address. 
	 */
	@Test
	public void executeSubmitPrepareTest() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		MockHttpServletRequest request = new MockHttpServletRequest();
		String result = writeController.execute(model,request);
		assertNull(model.get("id"));
		assertNull(model.get("email"));
		assertNull(model.get("text"));
		assertNull(model.get("onloadCode"));
		assertEquals(result,"write");
	}
	
	@Test
	public void executeUpdatePrepareWithValidPasswordTest() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		MockHttpServletRequest request = new MockHttpServletRequest();
		String id = "1";
		String pass = "123456";
		request.setParameter("id", id);
		request.setParameter("pass", pass);
		String result = writeController.execute(model,request);
		assertEquals(model.get("id"),1);
		assertEquals(model.get("email"),"test1@test.com");
		System.out.println(model.get("email"));
		assertEquals(model.get("text"),"test1");
		assertEquals(result,"write");
	}
	
	@Test
	public void executeUpdatePrepareWithInvalidPasswordTest() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		MockHttpServletRequest request = new MockHttpServletRequest();
		String id = "1";
		String pass = "999999";
		request.setParameter("id", id);
		request.setParameter("pass", pass);
		String result = writeController.execute(model,request);
		assertEquals(model.get("onloadCode"),"onload=wrongPassword()");
		assertEquals(result,"write");
	}
}