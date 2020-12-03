package edu.wpi.cs3733.b20.kotlin.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.wpi.cs3733.b20.kotlin.demo.model.Approval;

public class ApprovalsDAO {
	static java.sql.Connection conn;
	final static String tblName1 = "approvals";

	public boolean addApproval(Approval approval) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName1 + " (altID, choiceUUID, username) values(?,?,?);");
			ps.setInt(1, approval.getAltID());
			ps.setString(2, approval.getChoiceUUID());
			ps.setString(3, approval.getUsername());
			ps.executeUpdate();
			ps.close();
		
			return true;
		} catch(Exception e){
			e.printStackTrace();
			throw new Exception("Failed to insert approval: " + e.getMessage());
		}
		}
	// get approval boolean, return true if this exists. 
	
	// get all approvals in a particular choice and alternative
	public ArrayList<Approval> getApprovalList(String choiceUUID, int altID) {
		ArrayList<Approval> approvals = new ArrayList<Approval>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM "+ tblName1 + " WHERE choiceUUID =? AND altID =?");
			ps.setString(1,choiceUUID);
			ps.setInt(2, altID);
			ResultSet approvalSet = ps.executeQuery();
			
			while(approvalSet.next()) {
				approvals.add(new Approval(approvalSet.getInt("altID"),approvalSet.getNString("choiceUUID"), approvalSet.getString("username")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return approvals;
		
		
		
		
		
		
		
	}
}
