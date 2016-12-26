package com.basecamp.guestbook;

import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubmitController {
	@Autowired
	MessageDAO mdao;

	@RequestMapping(value = "/submit")
	public String execute(Model model, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		if(email==null) return "forward:/"; // http://...../submit을 직접 타이핑하여 접속한 경우
		Date now = new Date(System.currentTimeMillis());
		request.setAttribute("submitTime", now);
		request.setAttribute("updateTime", now);
		String regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		try {
			if(Pattern.matches(regex, email)) {
				if(id=="") {
					Message message = new MessageDAO.MessageFiller().fill(request);
					mdao.insert(message);
				}
				else {
					Message message = new MessageDAO.MessageFiller().fill(request);
					mdao.update(message);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "forward:/";
	}
}