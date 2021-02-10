package junit.Validations;

import static org.junit.jupiter.api.Assertions.*;
import static junit.Validations.Authentication.*;

import org.junit.jupiter.api.Test;

class TestAuthentication {

	// Test isEmpty Method
	@Test
	void testEmptyUser() {
		String[]fields = {"","password"};
		assertFalse(isNotEmpty(fields));
	}
	
	@Test
	void testEmptyPass() {
		String[]fields = {"username",""};
		assertFalse(isNotEmpty(fields));
	}
	
	@Test
	void testNullUser() {
		String[]fields = {null,"password"};
		assertFalse(isNotEmpty(fields));
	}
	
	@Test
	void testNullPass() {
		String[]fields = {"username",null};
		assertFalse(isNotEmpty(fields));
	}
	
	// Test fieldsMatch method
	@Test
	void testMatchWithCase() {
		String[]fields = {"username","username"};
		assertFalse(fieldsMatch(fields));
	}
	
	@Test
	void testMatchWithoutCase() {
		String[]fields = {"username","USERNAME"};
		assertFalse(fieldsMatch(fields));
	}
	
	@Test
	void testMatchNoMatterTheCase() {
		String[]fields = {"UsErNaMe","uSeRnAmE"};
		assertFalse(fieldsMatch(fields));
	}
	
	// Test containsPassword method
	@Test
	void testPassInUser() {
		String[]fields = {"username","name"};
		assertFalse(containsPassword(fields));
	}
	
	@Test
	void testUserInPass(){
		String[]fields = {"name","username"};
		assertFalse(containsPassword(fields));
	}
	
	@Test
	void testPassInUserWithCase() {
		String[]fields = {"userNaMe","name"};
		assertFalse(containsPassword(fields));
	}
	
	@Test
	void testUserInPassWithCase() {
		String[]fields = {"name","userNaMe"};
		assertFalse(containsPassword(fields));
	}
	
	
	
	// Test isValid method
	// Username: No spaces, dashes in between min 5, max 40
	// Password: Minimum 8, no max, 1 letter, 1 number
	@Test
	void testUserSpace() {
		String[]fields = {"user name","password01"};
		assertFalse(isValid(fields));
	}
	
	@Test
	void testPassSpace() {
		String[]fields = {"username","pass word01"};
		assertFalse(isValid(fields));
	}
	
	@Test
	void testUserDash() {
		String[]fields = {"user_name","password01"};
		assertFalse(isValid(fields));
	}
	
	@Test
	void testPassDash() {
		String[]fields = {"username","pass_word01"};
		assertFalse(isValid(fields));
	}
	
	@Test
	void testUserShort() {
		String[]fields = {"user","pass_word01"};
		assertFalse(isValid(fields));
	}
	
	@Test
	void testPassShort() {
		String[]fields = {"username","pass01"};
		assertFalse(isValid(fields));
	}
	
	@Test
	void testUserLong() {
		String[]fields = {"usernameiswaytoolongtobeacceptedbythisapplicationbecausethemaxis40chars","pass_word01"};
		assertFalse(isValid(fields));
	}
	
	@Test
	void testPassLong() {
		String[]fields = {"username","passwordcanbeaslongasitwantstobebecausethereisnomaxcharacterlimitalthoughthisisprobablyabadthing01"};
		assertTrue(isValid(fields));
	}
	
	@Test
	void testPassLettersOnly() {
		String[]fields = {"username","passwordhasnoletters"};
		assertFalse(isValid(fields));
	}
	
	@Test
	void testPassNumbersOnly() {
		String[]fields = {"username","0123456789"};
		assertFalse(isValid(fields));
	}

}
