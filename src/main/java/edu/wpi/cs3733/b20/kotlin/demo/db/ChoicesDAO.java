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
	
	public boolean deleteChoices(double numDays) throws Exception{
		int numSecs = (int)(numDays * 86400);
		System.out.println("delete all more than " + numSecs);
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName1 + " WHERE timeCreated < (NOW() - INTERVAL " + numSecs + " second);");
			int numDelete = ps.executeUpdate();
			System.out.print(numDelete);
			System.out.print(ps);
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to delete choices more than " + numDays + " old, " + e.getMessage());
		}
	}

	public Choice getChoice(String uuid) throws Exception{
		Choice choice = null;
		try {
			//Search database for a choice with a matching UUID
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName1 + " WHERE choiceUUID=?;");
			ps.setString(1,  uuid);
			ResultSet choiceSet = ps.executeQuery();
			choiceSet.next();
			
			String resultUuid = choiceSet.getString("choiceUUID");
			if(!uuid.equals(resultUuid)) 
				throw new Exception("UUID Mismatch");
			String description = choiceSet.getString("description");
			int maxUsers = choiceSet.getInt("maxUsers");

			//Search database for alts with matching UUID
			PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM " + tblName2 + " WHERE choiceUUID=?;");
			ps2.setString(1, uuid);
			ResultSet alternativeSet = ps2.executeQuery();
			
			FeedbackDAO feedbackDAO = new FeedbackDAO();
			ApprovalsDAO appDAO = new ApprovalsDAO();
			DisapprovalsDAO disDAO = new DisapprovalsDAO();
			ArrayList<Alternative> alternatives = new ArrayList<Alternative>();


			while(alternativeSet.next()) {
				Alternative alt = new Alternative(alternativeSet.getString("description"), feedbackDAO.getFeedbackList(uuid, alternativeSet.getInt("altID")),appDAO.getApprovalList(uuid, alternativeSet.getInt("altID")), disDAO.getDisapprovalList(uuid, alternativeSet.getInt("altID")));
				alternatives.add(alternativeSet.getInt("altID"), alt);
			}
			choice = new Choice(uuid, alternatives, maxUsers, description);

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
	
	/*
	 * Function returns a list of all choices in the database
	 */
	public ArrayList<Choice> getAllChoices() throws Exception{
		ArrayList<Choice> allChoices = new ArrayList<Choice>();
		try {
			PreparedStatement p1 = conn.prepareStatement("SELECT * FROM " + tblName1 + ";");
			ResultSet resultSet = p1.executeQuery();

			while(resultSet.next()) {
				Choice c = generateChoice(resultSet);
				allChoices.add(c);
			}
			resultSet.close();
			return allChoices;
		} catch (Exception e) {
			throw new Exception("Failed to get all Choices" + e.getMessage());
		}
	}

	/*
	 * Function generates a choice for admin display of a choice
	 * Displays ID, timeCreated, and a boolean for chosen status
	 */
	private Choice generateChoice(ResultSet resultSet) throws Exception{
		try {
			String choiceUUID = resultSet.getString("choiceUUID");
			String description = resultSet.getString("description");
			String timeCreated = resultSet.getString("timeCreated");
			Boolean isChosen = convertToBoolean(resultSet.getString("isChosen")); //Not sure if this will work right

			if(isChosen) 
				return new Choice(choiceUUID, description, timeCreated, true);
			else 
				return new Choice(choiceUUID, description, timeCreated, false);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to Generate Choice" + e.getMessage());
		}
	}

	/*
	 * Function converts a 0 or 1 into a boolean
	 */
	private boolean convertToBoolean(String val) {
		boolean retVal = false;
		if ("1".equalsIgnoreCase(val))
			retVal = true;
		return retVal;
	}
	
	public boolean isChoiceOpen(String uuid) {
	// looks for the state of the choice and returns true if the choice exists & is open
		try {
			Choice choice = this.getChoice(uuid);
			// if finalAlternative has been set to not null
			if(choice.finalAlternative == null) {
				return true;
			}
			return false;
			
		} catch (Exception e) {
			// assume that if the try fails, the Choice has been deleted/does not exist
			return false;
		}
		
	}

	public Boolean addFinalAlternative(String uuid, int altID) {
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE " + tblName1 + " SET finalAlternative = ?, isChosen =?  WHERE choiceUUID=? ;");	
			ps.setInt(1, altID);
			ps.setBoolean(2, true);
			ps.setString(3, uuid);
			ps.executeUpdate();
			ps.close();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
