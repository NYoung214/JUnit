package junit;

import junit.Validations.Authentication;

public class App {

	public static void main(String[] args) {
		String[] fields = {"username","password09"};
		boolean valid = true;
		
		if(!Authentication.isNotEmpty(fields)) {
			System.out.println("Username and password cannot be empty!");
			valid = false;
		}
		
		if(!Authentication.fieldsMatch(fields)) {
			System.out.println("Username and password cannot match!");
			valid = false;
		}
		
		if(!Authentication.containsPassword(fields)) {
			System.out.println("Username cannot contain password!");
			valid = false;
		}
		
		if(!Authentication.isValid(fields)) {
			System.out.println("Invalid username and password!");
			valid = false;
		}
		
		if(valid) {
			System.out.println("Username and Password accepted!");
		}

	}

}
