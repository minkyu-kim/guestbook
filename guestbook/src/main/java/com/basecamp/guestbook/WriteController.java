package com.basecamp.guestbook;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WriteController {
	@Autowired
	MessageDAO mdao;

	@RequestMapping(value = "/write")
	public String execute(Model model, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		if(id!=null) {
			try {
				Message message = new MessageDAO.MessageFiller().fill(request);
				message = mdao.selectOne(message);
				if(message!=null) {	
					model.addAttribute("id", message.getId());
					model.addAttribute("email", message.getEmail());
					model.addAttribute("text", message.getMessage());
				}
				else {
					model.addAttribute("onloadCode", "onload=wrongPassword()");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "write";
	}
}