package edu.wpi.cs3733.b20.kotlin.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.wpi.cs3733.b20.kotlin.demo.model.User;

public class TestUser {

	@Test
	public void testNewUser() {
		User user1 = new User("uuid", "user1","password1");
		
		assertEquals(user1.getPassword(), "password1");
		assertEquals(user1.getUsername(), "user1");
		assertEquals(user1.getChoiceUUID(), "uuid");
	}
}
