package junit.Validations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authentication {
	
	public static boolean isNotEmpty(String[] fields) {
		boolean valid = true;
		//check for empty fields
		for(int i = 0; i < fields.length; i++) {
			if(fields[i] == null || fields[i].length() < 1) {
				valid = false;
			}
		}
		return valid;
	}
	
	public static boolean fieldsMatch(String[] fields) {
		boolean valid = true;
		// check for matching username and password
		if(fields[0].equalsIgnoreCase(fields[1])) {
			valid = false;
		}
		return valid;
	}
	
	public static boolean containsPassword(String[] fields) {
		boolean valid = true;
		//username cannot contain password
		String userNameCheck = fields[0].toLowerCase();
		String passwordCheck = fields[1].toLowerCase();
		if(userNameCheck.contains(passwordCheck) || passwordCheck.contains(userNameCheck)) {
			valid = false;
		}
		return valid;
	}
	
	public static boolean isValid(String[] fields) {
		
		// **********************************************************
		//			Password Regex
		// ^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$ 						<-- minimum 8, no max, 1 letter, 1 number
		// ********** Regex for username validation ******************
		// ^(?=.{5,40}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$	<-- No spaces, dashes in between min 5, max 40
		// ^(?=[a-zA-Z0-9]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$ 			(<---- use this if the above does not work)
		// ***********************************************************
		
		Pattern userPattern = Pattern.compile("^(?=[a-zA-Z0-9]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");		
		Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
		Matcher matcher = null;

		//compare to regex patterns
		//compare username
		boolean valid = true;
		boolean match;
		for(int i=0; i < 2; i++) {
			switch(i) {
			case 0:	// username check
				matcher = userPattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					valid = false;
				}
				break;
			case 1:	// password check
				matcher = passPattern.matcher(fields[i]);
				match = matcher.find();
				if(!match) {
					valid = false;
				}
				break;
			default:
				break;
			}
		}
		return valid;
	}
}
