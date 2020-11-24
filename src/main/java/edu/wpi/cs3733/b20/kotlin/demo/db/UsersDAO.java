package edu.wpi.cs3733.b20.kotlin.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.wpi.cs3733.b20.kotlin.demo.model.User;

public class UsersDAO {
	java.sql.Connection conn;
	final String tblName = "users";
	
	public UsersDAO() {
		try{
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}
	
	public boolean addUser(User user) throws Exception{
		try {
			
			//check to make sure user isn't already in database
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE choiceUUID = ? AND username = ?;");
			ps.setString(1, user.getChoiceUUID());
			ps.setString(2, user.getUsername());
			ResultSet resultSet = ps.executeQuery();
			
			//if user is already in database
			while(resultSet.next()) {
				//User foundUser = generateUser(resultSet);
				resultSet.close();
				return false;
			}
			
			if(user.getPassword() == null) {   //user has no password to insert into table
				PreparedStatement ps2 = conn.prepareStatement("INSERT INTO " + tblName + " (choiceUUID, username) values (?,?);");
				ps2.setString(1, user.getChoiceUUID());
				ps2.setString(2, user.getUsername());
				ps2.executeUpdate();
			} else {    //user has a password 
				PreparedStatement ps2 = conn.prepareStatement("INSERT INTO " + tblName + " (choiceUUID, username, password) values (?,?,?);");
				ps2.setString(1, user.getChoiceUUID());
				ps2.setString(2, user.getUsername());
				ps2.setString(3, user.getPassword());
				ps2.executeUpdate();
			}
			return true;
			
		} catch (Exception e) {
			throw new Exception("Failed to insert user: " + e.getMessage());
		}
	}
	
	/*
	public User getUser(String choiceUUID, String username) throws Exception{
		try {
			User user = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE choiceUUID=? AND username=?;");
			ps.setString(1, choiceUUID);
			ps.setString(2, username);
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				user = generateUser(resultSet);
			}
			resultSet.close();
			ps.close();
			
			return user;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting user: " + e.getMessage());
		}
	}
	
	private User generateUser(ResultSet resultSet) throws Exception{
		String choiceUUID = resultSet.getString("choiceUUID");
		String username = resultSet.getString("username");
		String password = " ";
		if (password == " ") {
			password = resultSet.getString("password");
			return new User(choiceUUID, username, password);
		} else {
			return new User(choiceUUID, username);
		}
	}
	*/
}
