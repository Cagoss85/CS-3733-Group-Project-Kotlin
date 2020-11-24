package edu.wpi.cs3733.b20.kotlin.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.wpi.cs3733.b20.kotlin.demo.model.User;

public class UsersDAO {
	java.sql.Connection conn;
	final String tblName = "users";
	final String tblName2 = "choices";
	
	public UsersDAO() {
		try{
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}
	
	public boolean isThereSpaceFor(User user) throws Exception{
		try {
			//figure out how many users are currently in a choice
			PreparedStatement ps1 = conn.prepareStatement("SELECT COUNT(*) FROM " + tblName + " WHERE choiceUUID=?");
			ps1.setString(1, user.getChoiceUUID());
			ResultSet set1 = ps1.executeQuery();
			set1.next();
			String numCurrentUsersStr = set1.getString("COUNT(*)");
			int numCurrentUsers = Integer.parseInt(numCurrentUsersStr);
			
			
			
			//figure out how many users a choice can have
			PreparedStatement ps2 = conn.prepareStatement("SELECT maxUsers FROM " + tblName2 + " WHERE choiceUUID=?");
			ps2.setString(1, user.getChoiceUUID());
			ResultSet set2 = ps2.executeQuery();
			set2.next();
			String maxUsersStr = set2.getString("maxUsers");
			int maxUsers = Integer.parseInt(maxUsersStr);
			
			if(maxUsers - numCurrentUsers > 0) {
				return true;
			}
			
		} catch(Exception e) {
			throw new Exception("Failed to get number of users: " + e.getMessage());
		}
		return false;
		
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
				//User foundUser = generateUseesultSet);
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

	public User getUser(User user) throws Exception {
		try {
			User sampleUser = null;
			PreparedStatement ps1 = conn.prepareStatement("Select * FROM " + tblName + " WHERE choiceUUID=? AND username=?;");
			ps1.setString(1, user.getChoiceUUID());
			ps1.setString(2, user.getUsername());
			ResultSet resultSet = ps1.executeQuery();
			
			while(resultSet.next()) {
				sampleUser = generateUser(resultSet);
			}
			resultSet.close();
			ps1.close();
			
			return sampleUser;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to search for user: " + e.getMessage());
		}
	}
	
	public boolean checkForUser(User user) throws Exception {
		try {

			PreparedStatement ps1 = conn.prepareStatement("Select * FROM " + tblName + " WHERE choiceUUID=? AND username=?;");
			ps1.setString(1, user.getChoiceUUID());
			ps1.setString(2, user.getUsername());
			ResultSet resultSet = ps1.executeQuery();
			
			if(resultSet.next()) {
				return true;
			} else {
				return false;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to search for user: " + e.getMessage());
		}
	}
	
	private User generateUser(ResultSet resultSet) throws Exception{
		try {
			String choiceUUID = resultSet.getString("choiceUUID");
			String username = resultSet.getString("username");
			String password = resultSet.getString("password");
			if(resultSet.wasNull()) {
				password = " ";
			}
			if (password == " ") {
				return new User(choiceUUID, username);
			} else {
				return new User(choiceUUID, username, password);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to Generate User: " + e.getMessage());
		}
		
	}

	
}
