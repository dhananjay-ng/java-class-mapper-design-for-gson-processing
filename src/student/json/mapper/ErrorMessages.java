package student.json.mapper;

import java.util.List;

public class ErrorMessages {
	
	public static List<String> errors;
	
	void add(String msg) {
		errors.add(msg);
	}
	List<String> list(){
		return errors;
	}

}
