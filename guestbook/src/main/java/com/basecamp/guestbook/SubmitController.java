package com.basecamp.guestbook;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubmitController {

	@RequestMapping(value = "/submit")
	public String home(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		Connection con=null;

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		if(email==null) return "forward:/"; // http://...../submit을 직접 타이핑하여 접속한 경우
		String password = request.getParameter("hashedPass");
		String text = request.getParameter("text");
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
		String regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/guestbookdb","guestbook","rlaalsrb12");
			MessageDAO mdao = new MessageDAO();
			mdao.setConnection(con);
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
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "forward:/";
	}
}