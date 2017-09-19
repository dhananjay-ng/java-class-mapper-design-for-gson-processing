package student.data;


import java.util.List;

public class ValidatorException extends ServiceException {
    
    private static final long serialVersionUID = 1L;

    private final List<String> messages;

    public ValidatorException(String message, List<String> messages) {
        super(message);
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

}
