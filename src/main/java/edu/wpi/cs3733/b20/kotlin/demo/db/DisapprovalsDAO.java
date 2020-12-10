package edu.wpi.cs3733.b20.kotlin.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.wpi.cs3733.b20.kotlin.demo.model.Approval;
import edu.wpi.cs3733.b20.kotlin.demo.model.Disapproval;

public class DisapprovalsDAO {

	public DisapprovalsDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}
	static java.sql.Connection conn;
	final static String tblName1 = "approvals";
	final static String tblName2 = "disapprovals";

	public boolean addDisapproval(Disapproval disapproval) throws Exception {
		ApprovalsDAO appDAO = new ApprovalsDAO();
		ChoicesDAO choicesDAO = new ChoicesDAO();
		// return true if things are done correctly, false if there is an error
		if(choicesDAO.isChoiceOpen(disapproval.getChoiceUUID())){
		if(disapprovalExists(disapproval)) {
			// delete disapproval from DAO 
			try {	
				PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName2 + " WHERE choiceUUID=? AND altID=? AND username=?;");
				ps.setString(1, disapproval.getChoiceUUID());
				ps.setInt(2, disapproval.getAltID());
				ps.setString(3, disapproval.getUsername());
				ps.executeUpdate();
				ps.close();
				return true;
			}catch(Exception e) {
				//return false;
				throw new Exception("Failed to delete approval: "+ e.getMessage());
			}
		}
		else {
			// add disapproval to DAO as it does not exist
			if(appDAO.approvalExists(new Approval(disapproval.getChoiceUUID(), disapproval.getAltID(), disapproval.getUsername()))) {
				appDAO.addApproval(new Approval(disapproval.getChoiceUUID(), disapproval.getAltID(), disapproval.getUsername()));
			}
			try {
				PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName2 + " (choiceUUID,altID,username) values(?,?,?);");
				ps.setString(1, disapproval.getChoiceUUID());
				ps.setInt(2, disapproval.getAltID());
				ps.setString(3, disapproval.getUsername());
				ps.executeUpdate();
				ps.close();

				return true;
			} catch(Exception e){
				e.printStackTrace();
				//return false;
				throw new Exception("Failed to insert disapproval: " + e.getMessage());
			}

		}
		}
		else {
			throw new Exception("Failed to insert approval: " + "choice is closed");
		}

	}
	// get approval boolean, return true if this exists. 
	public boolean disapprovalExists(Disapproval disapproval) {
		try{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM "+ tblName2 + " WHERE choiceUUID =? AND altID =? AND username =?");

			ps.setString(1,disapproval.getChoiceUUID());
			ps.setInt(2, disapproval.getAltID());
			ps.setString(3, disapproval.getUsername());
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
	// get all disapprovals in a particular choice and alternative
	public ArrayList<Disapproval> getDisapprovalList(String choiceUUID, int altID) {
		ArrayList<Disapproval> disapprovals = new ArrayList<Disapproval>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM "+ tblName2 + " WHERE choiceUUID =? AND altID =?");
			ps.setString(1,choiceUUID);
			ps.setInt(2, altID);
			ResultSet disapprovalSet = ps.executeQuery();

			while(disapprovalSet.next()) {
				disapprovals.add(new Disapproval(disapprovalSet.getNString("choiceUUID"), disapprovalSet.getInt("altID"), disapprovalSet.getString("username")));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return disapprovals;




	}
}
