package students;

import java.util.regex.Pattern;

public class Validation {
	public static boolean isValidMail(String str) {	
		String pattern = "^(.+)@(.+)$";
		return !Pattern.matches(pattern,str);
		
	}
	public static boolean isValidPhoneNumber(String str) {
		
		String pattern = "(\\+\\d{2,}[.-])?(\\d{3})[.-]?(\\d{3})[.-]?(\\d{4})[.-]?";
		return !Pattern.matches(pattern, str);

	}
	public static boolean isValidId(String str) {
		if(str.length()==10 && str.substring(2,4).equalsIgnoreCase("kd")) {
			char c = str.charAt(7);
			if(c == '5' || c == '4' || c == '3' || c == '2')
				return false;
			return true;
		}
		return true;
	}

}
