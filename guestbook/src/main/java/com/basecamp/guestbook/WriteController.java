package com.basecamp.guestbook;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		if(id!="null") {
			Connection con=null;
			Statement st;
			ResultSet rs = null;
			String query = "SELECT * from messages WHERE id="+id+" AND pass="+pass+";";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/guestbookdb","guestbook","rlaalsrb12");
				if(con!=null) {
					st = con.createStatement();
					rs = st.executeQuery(query);
					if(rs.next()) {	
						model.addAttribute("id", rs.getString("id"));
						model.addAttribute("email", rs.getString("email"));
						model.addAttribute("text", rs.getString("message"));
					}
					else {
						// 비밀번호 틀렸을 때 예외 처리
					}
				}
				con.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "write";
	}
}