package com.basecamp.guestbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String html="";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/guestbookdb","guestbook","rlaalsrb12");
			if(con!=null) {
				st = con.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					html+="<tr>";
					html+="<td>"+rs.getString("id")+"</td>";
					html+="<td>"+rs.getString("email")+"</td>";
					html+="<td>"+rs.getString("message").replaceAll("\n", "</br>")+"</td>";
					html+="<td>"+rs.getString("submitTime");
					if(rs.getString("updateTime")!=null) {
						html+="<br>"+rs.getString("updateTime");
					}
					html+="</td>";
					html+="<td><input type=\"button\" id=\"modify"+rs.getString("id")+"\" value=\"¼öÁ¤\"/></td>";
			        html+="</tr>";
				}
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
		
		model.addAttribute("table", html);
		
		return "guestbook";
	}
}