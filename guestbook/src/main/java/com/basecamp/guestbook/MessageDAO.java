package com.basecamp.guestbook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
	Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection=connection;
	}
	
	public int insert(Message message) throws Exception{
		Statement stmt=null;
		int rs=0;
		String query = "INSERT INTO messages (email, pass, message, submitTime) VALUES (\'" +
				message.getEmail() + "\', " + message.getPass() + ", \'" +
				message.getMessage() + "\', \'" + message.getSubmitTime() + "\')";
		try {
			stmt=connection.createStatement();
			rs=stmt.executeUpdate(query);
			System.out.println(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
		return rs;
	}
	
	public int update(Message message) throws Exception{
		Statement stmt=null;
		int rs=0;
		String query = "UPDATE messages SET email=\'"+message.getEmail()+"\', pass=\'"+message.getPass()+
				"\', message=\'"+message.getMessage()+"\', updateTime=\'"+message.getUpdateTime()+
				"\' WHERE id=\'"+message.getId()+"\'";
		try {
			stmt=connection.createStatement();
			rs=stmt.executeUpdate(query);
			System.out.println(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
		return rs;
	}
	
	public List<Message> selectWithIdAndPass(int id, int pass) throws Exception{
		Statement stmt=null;
		ResultSet rs=null;
		String query = "SELECT * from messages WHERE id="+id+" AND pass="+pass+";";
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery(query);
			System.out.println(query);
			while (rs.next()) {
				Message message = new Message().
					setId(rs.getInt("id")).setEmail(rs.getString("email")).setMessage(rs.getString("message")).
					setSubmitTime(rs.getDate("submitTime").toString()+" "+rs.getTime("submitTime").toString());
				if(rs.getDate("updateTime")!=null) {
					message.setUpdateTime(rs.getDate("updateTime").toString()+" "+rs.getTime("updateTime").toString());
				}
				messages.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
		return messages;
	}
	
	public List<Message> selectAll() throws Exception{
		Statement stmt=null;
		ResultSet rs=null;
		String query="SELECT * from messages ORDER BY id DESC";
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery(query);
			System.out.println(query);
			while (rs.next()) {
				Message message = new Message().
					setId(rs.getInt("id")).setEmail(rs.getString("email")).setMessage(rs.getString("message")).
					setSubmitTime(rs.getDate("submitTime").toString()+" "+rs.getTime("submitTime").toString());
				if(rs.getDate("updateTime")!=null) {
					message.setUpdateTime(rs.getDate("updateTime").toString()+" "+rs.getTime("updateTime").toString());
				}
				messages.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {if(rs != null) rs.close();} catch(Exception e) {}
			try {if(stmt != null) stmt.close();} catch(Exception e) {}
		}
		return messages;
	}
}