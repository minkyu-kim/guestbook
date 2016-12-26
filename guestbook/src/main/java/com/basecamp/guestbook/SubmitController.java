package com.basecamp.guestbook;

import java.text.SimpleDateFormat;
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
		String password = request.getParameter("hashedPass");
		String text = request.getParameter("text");
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
		String regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		try {
			if(Pattern.matches(regex, email)) {
				if(id=="") {
					Message message = new Message().setEmail(email).setPass(Integer.parseInt(password)).
							setMessage(text).setSubmitTime(now);
					mdao.insert(message);
				}
				else {
					Message message = new Message().setEmail(email).setPass(Integer.parseInt(password)).
							setMessage(text).setUpdateTime(now).setId(Integer.parseInt(id));
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