package com.basecamp.guestbook;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WriteController {
	@Autowired
	MessageDAO mdao;

	@RequestMapping(value = "/write")
	public String execute(Map<String, Object> model, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		if(id!=null) {
			try {
				Message message = new MessageDAO.MessageFiller().fill(request);
				message = mdao.selectOne(message);
				if(message!=null) {	
					model.put("id", message.getId());
					model.put("email", message.getEmail());
					model.put("text", message.getMessage());
				}
				else {
					model.put("onloadCode", "onload=wrongPassword()");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "write";
	}
}