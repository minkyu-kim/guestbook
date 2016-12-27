package com.basecamp.guestbook;

import static org.junit.Assert.*;

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
	
	@Test
	public void testController() {
		Map<String, Object> model = new HashMap<String, Object>();
		String result = guestbookController.execute(model);
		assertEquals(result,"guestbook");
	}
}