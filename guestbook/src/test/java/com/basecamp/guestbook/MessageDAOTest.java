package com.basecamp.guestbook;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/mybatis-config.xml"})
@WebAppConfiguration
public class MessageDAOTest {
	@Autowired
	private MessageDAO mdao;
	
	/**		Initailized Database Status
	 * 		+---------------------------------------------------------------------------+
	 * 		| TABLE messages                                                            |
	 * 		+----+----------------+--------+---------+---------------------+------------+
	 * 		| id | email          | pass   | message | submitTime          | updateTime |
	 * 		+----+----------------+--------+---------+---------------------+------------+
	 * 		| 1  | test1@test.com | 123456 | test1   | 2016-12-27 16:17:53 | NULL       |
	 * 		+----+----------------+--------+---------+---------------------+------------+
	 * 		| 2  | test2@test.com | 234567 | test2   | 2016-12-27 16:18:53 | NULL       |
	 * 		+----+----------------+--------+---------+---------------------+------------+
	 * 		| 3  | test3@test.com | 345678 | test3   | 2016-12-27 16:19:53 | NULL       |
	 *		+----+----------------+--------+---------+---------------------+------------+
	 */		
	
	
	/**
	 * Test whether it returns exactly three messages
	 * when MessageDAO calls SelectAll() with initialized DB.
	 * Initialized Database has three records.
	 */
	@Test
	@Transactional
	public void selectAllTest() throws Exception {
		ArrayList<Message> messages = (ArrayList<Message>) mdao.selectAll();
		assertEquals(messages.size(),3);
	}
	
	@Test
	@Transactional
	public void selectOneTest() throws Exception {
		int id=1;
		int pass=123456, wrongPass=234567;
		Message message = new Message().setId(id).setPass(pass);
		Message selectedMessage = mdao.selectOne(message);
		
		// Was selected record passed well?
		assertEquals(selectedMessage.getId(),id);
		assertEquals(selectedMessage.getPass(),pass);
		assertEquals(selectedMessage.getEmail(),"test1@test.com");
		assertEquals(selectedMessage.getMessage(),"test1");
		assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(selectedMessage.getSubmitTime()),"2016-12-27 16:17:53");
		assertNull(selectedMessage.getUpdateTime());
		
		message.setPass(wrongPass);
		selectedMessage = mdao.selectOne(message);
		
		// Is it return null value when I entered wrong password?
		assertNull(selectedMessage);
	}	
	
	/**
	 * Test whether insertion operation has committed properly.
	 * And it also tests whether stored record in DB has exactly same data as before.
	 */
	@Test
	@Transactional
	public void insertTest() throws Exception {
		int id=4;
		int pass=99999;
		String email="insertTest@nhnent.com";
		String text="insertTest...Hello..";
		Date now = new Date(System.currentTimeMillis());
		int numberOfRecordsBeforeInsert = ((ArrayList<Message>)mdao.selectAll()).size();
		Message message = new Message().setId(id).setEmail(email).setPass(pass).
				setMessage(text).setSubmitTime(now);
		int insertionResult = mdao.insert(message);
		
		// Was insertion operation executed properly?
		assertEquals(insertionResult,1);
		
		int numberOfRecordsAfterInsert = ((ArrayList<Message>)mdao.selectAll()).size();
		
		// Did Number of records in database increase? 
		assertEquals(numberOfRecordsAfterInsert,numberOfRecordsBeforeInsert+1);
		
		Message selectedMessage = mdao.selectOne(message);
		
		// Was inserted data stored well?
		assertNotNull(selectedMessage);
	}
	
	/**
	 * Test whether update operation has committed properly.
	 * And it also tests whether stored record in DB has modified well.
	 */
	@Test
	@Transactional
	public void updateTest() throws Exception {
		int id=1;
		int pass=654321;
		String email="updateTest@nhnent.com";
		String text="updateTest...Hello..";
		Date now = new Date(System.currentTimeMillis());
		int numberOfRecordsBeforeUpdate = ((ArrayList<Message>)mdao.selectAll()).size();
		Message message = new Message().setId(id).setEmail(email).setPass(pass).
				setMessage(text).setUpdateTime(now);
		int updateResult = mdao.update(message);
		
		// Was update operation executed properly?
		assertEquals(updateResult,1);
		
		int numberOfRecordsAfterUpdate = ((ArrayList<Message>)mdao.selectAll()).size();
		
		// Did Number of records in database increase? 
		assertEquals(numberOfRecordsAfterUpdate,numberOfRecordsBeforeUpdate);
		
		Message selectedMessage = mdao.selectOne(message);
		
		// Was updated data stored well?
		assertNotNull(selectedMessage);
	}
}