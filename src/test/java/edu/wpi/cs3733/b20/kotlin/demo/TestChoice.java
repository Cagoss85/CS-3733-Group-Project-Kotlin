package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.model.Alternative;
import edu.wpi.cs3733.b20.kotlin.demo.model.Choice;
import edu.wpi.cs3733.b20.kotlin.demo.model.User;

public class TestChoice {
	
	@Test
	public void testChoiceConst() {
		ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
		Choice choice = new Choice("testUUID", alternatives, 5, "testChoice");
		assertEquals("testUUID", choice.getUuid());
		assertEquals(0, choice.getAlternatives().size());
		assertEquals(5, choice.getMaxUsers());
		assertEquals("testChoice", choice.getDescription());
		assertEquals(System.currentTimeMillis(), choice.getTimeCreated());
		
		choice.setDescription("desc2");
		assertEquals("desc2", choice.getDescription());
		
		ArrayList<Alternative> alt2 = new ArrayList<Alternative>();
		Alternative alt1 = new Alternative("alt1");
		alt2.add(alt1);
		choice.setAlternatives(alt2);
		assertEquals(1, choice.getAlternatives().size());
		
		choice.setMaxUsers(9);
		assertEquals(9, choice.getMaxUsers());
		
		choice.setTimeCreated(50);
		assertEquals(50, choice.getTimeCreated());
		
		assertEquals(0, choice.getUsers().size());
		User user = new User("testUser");
		choice.addUser(user);
		assertEquals(1, choice.getUsers().size());
	}
}
