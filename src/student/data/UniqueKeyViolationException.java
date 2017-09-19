package student.data;

public class UniqueKeyViolationException extends DaoException {

    private static final long serialVersionUID = 1L;

    public UniqueKeyViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueKeyViolationException(String message) {
        super(message);
    }

}
