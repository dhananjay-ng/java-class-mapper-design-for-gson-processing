package student.web;

public class UserNotFoundException extends UserDaoException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UserNotFoundException(String arg0) {
		super(arg0);
	}

}
