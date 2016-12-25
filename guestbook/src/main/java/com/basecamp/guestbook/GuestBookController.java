package com.basecamp.guestbook;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestBookController {

	@RequestMapping(value = "/")
	public String home(Model model) {
		Connection con=null;		
		MessageDAO mdao = new MessageDAO();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/guestbookdb","guestbook","rlaalsrb12");
			mdao.setConnection(con);
			model.addAttribute("messages", mdao.selectAll());
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "guestbook";
	}
}