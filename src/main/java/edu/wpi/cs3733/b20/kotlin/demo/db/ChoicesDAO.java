package edu.wpi.cs3733.b20.kotlin.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;
import edu.wpi.cs3733.b20.kotlin.demo.model.Choice;

public class ChoicesDAO {
	static java.sql.Connection conn;
	final static String tblName1 = "choices";
	final static String tblName2 = "alternatives";
	
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
		
			//PreparedStatement ps2 = conn.prepareStatement("INSERT INTO " +tblName2 + " (altID, choiceUUID, description) values (?,?,?)");
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
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("Failed to insert choice: " + e.getMessage());
		}
	}
	public Choice getChoice(String uuid) throws Exception{
		Choice choice = null;
		try {
		// search database for choice info
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName1 + " WHERE choiceUUID=?;");
        ps.setString(1,  uuid);
        ResultSet choiceSet = ps.executeQuery();
        //save choice info to memory.
        choiceSet.next();
        String resultUuid = choiceSet.getString("choiceUUID");
        if(!uuid.equals(resultUuid)) {
        	throw new Exception("UUID Mismatch");
        }
        String description = choiceSet.getString("description");
        int maxUsers = choiceSet.getInt("maxUsers");
        
        
        // grab all alternatives with choice uuid and place them in an array
        ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
        // get all alternative rows with ChoiceUUID as our selected choice
        PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM " + tblName2 + " WHERE choiceUUID=?;");
        ps2.setString(1, uuid);
        ResultSet alternativeSet = ps2.executeQuery();
        // iterate through all alternatives located from the database
        while(alternativeSet.next()) {
        	// create alternative w/ description from table
        	Alternative alt = new Alternative(alternativeSet.getString("description"));
        	// place alternative in correct index based on the altID
        	alternatives.add(alternativeSet.getInt("altID"), alt);
        }
        // create new choice from our found values        
        choice = new Choice(uuid, alternatives, maxUsers, description);
              
        // close no longer used items
        choiceSet.close();
        alternativeSet.close();
        ps.close();
        ps2.close();
        } catch(Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting choice: " + e.getMessage());
        }
        
        return choice;
	}
	
}
