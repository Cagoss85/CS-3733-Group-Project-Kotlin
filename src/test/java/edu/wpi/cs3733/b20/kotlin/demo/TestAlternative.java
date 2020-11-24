package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;
import edu.wpi.cs3733.b20.kotlin.demo.model.Approval;
import edu.wpi.cs3733.b20.kotlin.demo.model.Disapproval;
import edu.wpi.cs3733.b20.kotlin.demo.model.Feedback;
import edu.wpi.cs3733.b20.kotlin.demo.model.User;

public class TestAlternative {
	
	@Test
	public void testAlt() {
		Alternative testAlt = new Alternative("Alternative One");
		assertEquals("Alternative One", testAlt.getDescription());
		testAlt.setDescription("desc");
		assertEquals("desc", testAlt.getDescription());
		
		User phil = new User("phil");
		Approval app1 = new Approval("50", phil);
		
		assertEquals(0, testAlt.getApprovals().size());
		testAlt.addApproval(app1);
		assertEquals(1, testAlt.getApprovals().size());
		
		User dan = new User("dan");
		Disapproval disp1 = new Disapproval("50", dan);
		
		assertEquals(0, testAlt.getDisapprovals().size());
		testAlt.addDisapproval(disp1);
		assertEquals(1, testAlt.getDisapprovals().size());
		
		Feedback feed1 = new Feedback("50", dan, "this is a test");
		Feedback feed2 = new Feedback("50", phil, "this is a test2");
		assertEquals(0, testAlt.getFeedback().size());
		testAlt.addFeedback(feed1);
		assertEquals(1, testAlt.getFeedback().size());
		testAlt.addFeedback(feed2);
		assertEquals(2, testAlt.getFeedback().size());
		
		testAlt.removeApproval(app1);
		assertEquals(0, testAlt.getApprovals().size());
		
		testAlt.removeDisapproval(disp1);
		assertEquals(0, testAlt.getDisapprovals().size());
	
	}
}
