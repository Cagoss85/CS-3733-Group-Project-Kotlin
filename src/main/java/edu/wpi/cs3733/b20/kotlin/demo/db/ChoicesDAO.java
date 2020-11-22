package edu.wpi.cs3733.b20.kotlin.demo.db;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;
import edu.wpi.cs3733.b20.kotlin.demo.model.Choice;

public class ChoicesDAO {
	java.sql.Connection conn;
	final String tblName1 = "choices";
	final String tblName2 = "alternatives";
	
	public ChoicesDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}
	
	public boolean addChoice(Choice choice) throws Exception{
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName1 + " (choiceUUID, description, maxUsers, timeCreated) values(?,?,?,?);");
			ps.setString(1, choice.getUuid());
			ps.setString(2, choice.getDescription());
			ps.setInt(3, choice.getMaxUsers());
			Timestamp time = new Timestamp(choice.getTimeCreated());
			ps.setTimestamp(4,time);
			ps.executeUpdate();
			ps.close();
		
//			PreparedStatement ps2 = conn.prepareStatement("INSERT INTO " +tblName2 + " (altID, choiceUUID, description) values (?,?,?)");
			int i = 0;
			for(Alternative a : choice.getAlternatives()) {
				PreparedStatement ps2 = conn.prepareStatement("INSERT INTO " +tblName2 + " (altID, choiceUUID, description) values (?,?,?);");
				ps2.setInt(1,i);
				ps2.setString(2, choice.getUuid());
				ps2.setString(3, a.getDescription());
				ps2.executeUpdate();
				ps2.close();
				i++;
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Failed to insert choice: " + e.getMessage());
		}
	}
}
