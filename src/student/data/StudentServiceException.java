package student.data;

public class StudentServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	StudentServiceException(String s) {
		super(s);

	}

	public StudentServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
