package edu.wpi.cs3733.b20.kotlin.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.wpi.cs3733.b20.kotlin.demo.model.Approval;
import edu.wpi.cs3733.b20.kotlin.demo.model.Disapproval;

public class ApprovalsDAO {
	public ApprovalsDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}
	static java.sql.Connection conn;
	final static String tblName1 = "approvals";
	final static String tblName2 = "disapprovals";

	public boolean addApproval(Approval approval) throws Exception {
		DisapprovalsDAO disDAO = new DisapprovalsDAO();
		// return true if things are done correctly, false if there is an error
		if(approvalExists(approval)) {
			// delete approval from DAO 
			try {	
				PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName1 + " WHERE choiceUUID=? AND altID=? AND username=?;");
				ps.setString(1, approval.getChoiceUUID());
				ps.setInt(2, approval.getAltID());
				ps.setString(3, approval.getUsername());
				ps.executeUpdate();
				ps.close();
				return true;
			}catch(Exception e) {
				//return false;
				throw new Exception("Failed to delete approval: "+ e.getMessage());
			}
		}
		else {
			// add approval to DAO as it does not exist
			if(disDAO.disapprovalExists(new Disapproval(approval.getChoiceUUID(), approval.getAltID(), approval.getUsername()))) {
				disDAO.addDisapproval(new Disapproval(approval.getChoiceUUID(), approval.getAltID(), approval.getUsername()));
			}

			try {
				PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName1 + " (choiceUUID,altID,username) values(?,?,?);");
				ps.setString(1, approval.getChoiceUUID());
				ps.setInt(2, approval.getAltID());
				ps.setString(3, approval.getUsername());
				ps.executeUpdate();
				ps.close();

				return true;
			} catch(Exception e){
				e.printStackTrace();
				//return false;
				throw new Exception("Failed to insert approval: " + e.getMessage());
			}

		}

	}
	// get approval boolean, return true if this exists. 
	public boolean approvalExists(Approval approval) {
		try{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM "+ tblName1 + " WHERE choiceUUID =? AND altID =? AND username =?");

			ps.setString(1,approval.getChoiceUUID());
			ps.setInt(2, approval.getAltID());
			ps.setString(3, approval.getUsername());
			ResultSet results = ps.executeQuery();

			if(results.next()) {
				return true;
			} else {
				return false;

			}
		} catch(Exception e) {
			return false;
		}
	}
	// get all approvals in a particular choice and alternative
	public ArrayList<Approval> getApprovalList(String choiceUUID, int altID) {
		ArrayList<Approval> approvals = new ArrayList<Approval>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM "+ tblName1 + " WHERE choiceUUID =? AND altID =?");
			ps.setString(1,choiceUUID);
			ps.setInt(2, altID);
			ResultSet approvalSet = ps.executeQuery();

			while(approvalSet.next()) {
				approvals.add(new Approval(approvalSet.getNString("choiceUUID"), approvalSet.getInt("altID"), approvalSet.getString("username")));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return approvals;







	}
}
