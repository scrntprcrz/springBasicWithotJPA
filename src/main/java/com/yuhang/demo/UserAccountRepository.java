package com.yuhang.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAccountRepository {


	Connection con = null;
	
	public UserAccountRepository() {

		String url = "jdbc:mysql://localhost:3306/restdb?serverTimezone=UTC";
		String username = "root";
		String password = "root";
		
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
	    }
		catch(Exception e)
		{
			e.getStackTrace();
			System.out.println(e);
		}
	}
	
	
	public List<UserAccount> getUserAccounts() {
		
		List<UserAccount> userAccounts = new ArrayList<>();
		String sql = "select * from useraccount";
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				UserAccount ua = new UserAccount();
				ua.setId(rs.getInt(1));
				ua.setName(rs.getString(2));
				ua.setBalance(rs.getInt(3));
				ua.setHolding(rs.getString(4));
				
				userAccounts.add(ua);
			}
		}
		catch(Exception e)
		{
			
			System.out.println(e);
		}
		return userAccounts;
	}
	
	public UserAccount getUserAccount(int id) {
		
		UserAccount ua = new UserAccount();		
		String sql = "select * from userAccount where id=" + id;
		try
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next())
			{
				
				ua.setId(rs.getInt(1));
				ua.setName(rs.getString(2));
				ua.setBalance(rs.getInt(3));
				ua.setHolding(rs.getString(4));

			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return ua;
	}

	
	public void addUserAccount(UserAccount ua) {
		String sql = "insert into userAccount values (?, ?, ?, ?)";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, ua.getId());
			st.setString(2, ua.getName());
			st.setInt(3, ua.getBalance());
			st.setString(4, ua.getHolding());
			
			st.executeUpdate();			

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
		

	public void updateUserAccount(UserAccount ua) {

		String sql = "update userAccount set name=?, balance=?, holding=? where id=?";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
						
			st.setString(1, ua.getName());
			st.setInt(2, ua.getBalance());
			st.setString(3, ua.getHolding());
			st.setInt(4, ua.getId());
			st.executeUpdate();			

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	
	
	public void deleteUserAccount(int id) {
		
		String sql = "delete from userAccount where id=?";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
						
			st.setInt(1, id);
			st.executeUpdate();			

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	} 

}
