package student.web;

public class DuplicateUserException extends UserDaoException {

	public DuplicateUserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DuplicateUserException(String arg0) {
		super(arg0);
	}

	private static final long serialVersionUID = 1L;

}
