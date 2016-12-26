package com.basecamp.guestbook;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO {
	@Autowired
	SqlSessionTemplate session;

	public int insert(Message message) throws Exception{
		int count=0;
		count = session.insert("com.basecamp.guestbook.insert", message);
		return count;
	}

	public int update(Message message) throws Exception{
		int count=0;
		count = session.update("com.basecamp.guestbook.update", message);
		return count;
	}

	public Message selectOne(Message message) throws Exception{
		Message returnMessage=null;
		returnMessage = session.selectOne("com.basecamp.guestbook.selectOne", message);
		return returnMessage;
	}

	public List<Message> selectAll() throws Exception{
		List<Message> returnMessages = new ArrayList<Message>();
		returnMessages = session.selectList("com.basecamp.guestbook.selectAll");
		return returnMessages;
	}
}