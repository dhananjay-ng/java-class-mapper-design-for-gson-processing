package student.web;

import java.util.List;
import java.util.regex.Pattern;

import java.util.regex.Matcher;

public class UserValidator {
	public void validateForAdd(User user, List<String> message) {
		if (user.getUserId() == null || user.getUserId().trim().length() == 0) {
			message.add("UserId Can not be empty");
		}
		if (user.getUserName() == null || user.getUserName().trim().length() == 0) {
			message.add("UserName can not be empty");
		}
		if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
			message.add("Password can not be empty");
		} 
		else {
			String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
			Pattern pattern;
			Matcher matcher;

			pattern = Pattern.compile(PASSWORD_PATTERN);

			matcher = pattern.matcher(user.getPassword());
			if (!matcher.matches()) {
				message.add("password must have 8 or more characters, "//
						+ "one or more number, "//
						+ "one or more lowercase character, "//
						+ "one or more uppercase character, "//
						+ "one or more special character.");

			}

		}

	}
}
