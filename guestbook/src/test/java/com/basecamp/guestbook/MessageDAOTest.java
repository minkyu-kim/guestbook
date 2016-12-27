package com.basecamp.guestbook;

import static org.junit.Assert.*;

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
public class MessageDAOTest {
	@Autowired
	private MessageDAO mdao;
	
	/**
	 * 최초에 DB에 레코드가 3개 있는 상태에서 SelectAll()을 하였을 때 3개의 Message를 리턴하는지 확인
	 */
	@Test
	public void selectAllTest() throws Exception {
		ArrayList<Message> messages = (ArrayList<Message>) mdao.selectAll();
		assertEquals(messages.size(),3);
	}
}