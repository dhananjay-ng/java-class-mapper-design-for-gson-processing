package student.data;

public class DuplicateStudentException extends StudentDaoException {

	public DuplicateStudentException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateStudentException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
