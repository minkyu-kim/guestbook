package com.basecamp.guestbook;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WriteController {

	@RequestMapping(value = "/write")
	public String home(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		if(id!=null) {
			Connection con=null;
			MessageDAO mdao = new MessageDAO();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/guestbookdb","guestbook","rlaalsrb12");
				mdao.setConnection(con);
				ArrayList<Message> messages = (ArrayList<Message>)mdao.selectWithIdAndPass(Integer.parseInt(id), Integer.parseInt(pass));
				if(messages.size()!=0) {	
					model.addAttribute("id", messages.get(0).getId());
					model.addAttribute("email", messages.get(0).getEmail());
					model.addAttribute("text", messages.get(0).getMessage());
				}
				else {
					model.addAttribute("onloadCode", "onload=wrongPassword()");
				}
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "write";
	}
}