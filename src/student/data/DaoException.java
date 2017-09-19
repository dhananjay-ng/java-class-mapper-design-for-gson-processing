package student.data;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	DaoException(String s) {
		super(s);

	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

}
