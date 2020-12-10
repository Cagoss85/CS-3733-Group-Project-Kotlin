package edu.wpi.cs3733.b20.kotlin.demo.db;

import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import edu.wpi.cs3733.b20.kotlin.demo.model.Feedback;

public class FeedbackDAO {
	public FeedbackDAO(){
		try {
			conn = DatabaseUtil.connect();
		} catch(Exception e) {
			conn = null;
		}
	}
	static java.sql.Connection conn;
	final static String tblName1 = "feedback";
	
	public boolean addFeedback(Feedback feedback) throws Exception{
		ChoicesDAO choicesDAO = new ChoicesDAO();
		
		try {
			if(choicesDAO.isChoiceOpen(feedback.getChoiceUUID())) {
				PreparedStatement p1 = conn.prepareStatement("INSERT INTO " + tblName1 + " (choiceUUID, altID, username, text, timestamp) values(?,?,?,?,?);");
				p1.setString(1, feedback.getChoiceUUID());
				p1.setInt(2, feedback.getAltID());
				p1.setString(3, feedback.getUsername());
				p1.setString(4,  feedback.getText());
				Timestamp time = new Timestamp(feedback.getTimestamp());
				p1.setTimestamp(5, time);
				p1.executeUpdate();
				p1.close();
				return true;
			}
			else { throw new Exception("Failed to add feedback: Choice closed");}
			
		} catch (Exception e) {
			throw new Exception("Failed to add feedback: " + e.getMessage());
		}
		}
		

	public ArrayList<Feedback> getFeedbackList(String choiceUUID, int altID) throws Exception{
		ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName1 + " WHERE choiceUUID =? AND altID =?");
			ps.setString(1, choiceUUID);
			ps.setInt(2, altID);
			ResultSet feedbackSet = ps.executeQuery();
			while(feedbackSet.next()) {
				Feedback f = generateFeedback(feedbackSet);
				feedbackList.add(f);
			}
			feedbackSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feedbackList;
	}

	private Feedback generateFeedback(ResultSet feedbackSet) throws Exception{
		try {
			String choiceUUID = feedbackSet.getString("choiceUUID");
			int altID = feedbackSet.getInt("altID");
			String username = feedbackSet.getString("username");
			String text = feedbackSet.getString("text");
			String timestampString = feedbackSet.getString("timestamp");
			return new Feedback(choiceUUID, altID, username, text, timestampString);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Failed to generate feedback");
		}
	}
}
