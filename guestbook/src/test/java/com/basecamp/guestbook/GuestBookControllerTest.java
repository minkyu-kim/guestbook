package com.basecamp.guestbook;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
public class GuestBookControllerTest {
	@Autowired
	private GuestBookController guestbookController;
	
	/**
	 * Test whether GuestBookController.execute() returns correct address.
	 * And it also test whether ArrayList of Messages has all record data.
	 */
	@Test
	public void executeTest() {
		Map<String, Object> model = new HashMap<String, Object>();
		String result = guestbookController.execute(model);
		assertEquals(((ArrayList<Message>)model.get("messages")).size(),3);
		assertEquals(result,"guestbook");
	}
}