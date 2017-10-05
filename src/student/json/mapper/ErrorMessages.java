package student.json.mapper;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessages {
	
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public ErrorMessages(){
		errors=new ArrayList<>();
	}
	private  List<String> errors;
	
	void addErrors(String msg) {
		errors.add(msg);
	}
	

}
