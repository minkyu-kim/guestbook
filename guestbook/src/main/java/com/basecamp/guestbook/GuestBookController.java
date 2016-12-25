package com.basecamp.guestbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestBookController {
	
	@RequestMapping(value = "/")
	public String home(Model model) {
		Connection con=null;
		Statement st;
		ResultSet rs = null;
		String query = "SELECT * from messages ORDER BY id DESC;";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/guestbookdb","guestbook","rlaalsrb12");
			ArrayList<Message> messages = new ArrayList<Message>();
			if(con!=null) {
				st = con.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					Message message = new Message().
						setId(rs.getInt("id")).setEmail(rs.getString("email")).setMessage(rs.getString("message")).
						setSubmitTime(rs.getDate("submitTime").toString()+" "+rs.getTime("submitTime").toString());
					if(rs.getDate("updateTime")!=null) {
						message.setUpdateTime(rs.getDate("updateTime").toString()+" "+rs.getTime("updateTime").toString());
					}
					messages.add(message);
				}
				model.addAttribute("messages", messages);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB connect fail.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "guestbook";
	}
}