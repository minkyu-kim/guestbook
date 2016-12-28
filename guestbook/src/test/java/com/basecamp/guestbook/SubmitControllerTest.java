package com.basecamp.guestbook;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/mybatis-config.xml"})
@WebAppConfiguration
public class SubmitControllerTest {
	@Autowired
	private SubmitController submitController;
	@Autowired
	private MessageDAO mdao;

	/**
	 * Test whether SubmitController.execute() returns correct address. 
	 */
	@Test
	public void executeWithNullIdTest() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		String result = submitController.execute(request);
		assertEquals(result,"forward:/");
	}

	@Test
	@Transactional
	public void executeInsertOperationTest() throws Exception {
		String id = "";
		String pass = "123456";
		String email = "test@nhnent.com";
		String text = "test message";
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("id", id);
		request.setParameter("pass", pass);
		request.setParameter("email", email);
		request.setParameter("text", text);
		int numberOfRecordsBeforeInsert = ((ArrayList<Message>)mdao.selectAll()).size();
		String result = submitController.execute(request);
		int numberOfRecordsAfterInsert = ((ArrayList<Message>)mdao.selectAll()).size();
		assertEquals(numberOfRecordsAfterInsert,numberOfRecordsBeforeInsert+1);
		assertEquals(result,"forward:/");
	}

	@Test
	@Transactional
	public void executeInsertOperationWithInvalidEmailTest() throws Exception {
		String id = "";
		String pass = "123456";
		String email = "test@nhnent.company";
		String text = "test message";
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("id", id);
		request.setParameter("pass", pass);
		request.setParameter("email", email);
		request.setParameter("text", text);
		int numberOfRecordsBeforeInsert = ((ArrayList<Message>)mdao.selectAll()).size();
		String result = submitController.execute(request);
		int numberOfRecordsAfterInsert = ((ArrayList<Message>)mdao.selectAll()).size();
		assertEquals(numberOfRecordsAfterInsert,numberOfRecordsBeforeInsert);
		assertEquals(result,"forward:/");
	}
	
	@Test
	@Transactional
	public void executeUpdateOperationTest() throws Exception {
		String id = "1";
		String pass = "123456";
		String email = "test@nhnent.com";
		String text = "test message";
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("id", id);
		request.setParameter("pass", pass);
		request.setParameter("email", email);
		request.setParameter("text", text);
		String result = submitController.execute(request);
		Message message = mdao.selectOne(new MessageDAO.MessageFiller().fill(request));
		assertEquals(message.getPass(), Integer.parseInt(pass));
		assertEquals(message.getEmail(), email);
		assertEquals(message.getMessage(), text);
		assertEquals(result,"forward:/");
	}
}