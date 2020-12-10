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
		try {
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
		} catch (Exception e) {
			throw new Exception("Failed to add feedback: " + e.getMessage());
		}
	}

	public ArrayList<Feedback> getFeedbackList(String choiceUUID, int altID) {
		ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName1 + " WHERE choiceUUID =? AND altID =?");
			ps.setString(1, choiceUUID);
			ps.setInt(2, altID);
			ResultSet feedbackSet = ps.executeQuery();
			
			while(feedbackSet.next()) {
				feedbackList.add(new Feedback(feedbackSet.getString("choiceUUID"), feedbackSet.getInt("altID"), feedbackSet.getString("username"), feedbackSet.getString("timeStamp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feedbackList;
	}
}
