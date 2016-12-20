package com.basecamp.guestbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubmitController {

	@RequestMapping(value = "/submit")
	public String home(Model model, HttpServletRequest request) {
		Connection con=null;
		Statement st;
		int rs;

		String email = request.getParameter("email");
		if(email==null) return "forward:/"; // http://...../submit을 직접 타이핑하여 접속한 경우
		String password = request.getParameter("hashedPass");
		String text = request.getParameter("text");
		String query = "INSERT INTO messages (email, pass, message) VALUES (\'" +
				email + "\', " + password + ", \'" + text + "\')";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/guestbookdb","guestbook","rlaalsrb12");
			if(con!=null) {
				String regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
				if(Pattern.matches(regex, email) && con!=null) {
					st = con.createStatement();
					rs = st.executeUpdate(query);
				}
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB connect fail.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "forward:/";
	}
}