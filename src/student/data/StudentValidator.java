package student.data;

import java.util.List;

public class StudentValidator {

	Student student;
	StudentService service;
	public  StudentValidator(Student student,StudentService service){
		this.student=student;
		this.service=service;
	}
	
	public void validateStudent(List<String> messages, Student student) {
		
		if (student.getName() == null || student.getName().length() == 0) {
			messages.add("name is required.");
		}
		if (student.getRollNumber() == 0) {
			messages.add("rollNumber is required.");
		}

		
		if (student.getGender() == null || student.getId().length() == 0) {
			messages.add("Student Gender can not be empty");

		}  
		if (student.getBirthDate() == null) {
			messages.add("Student BirthDate Cannot be null");
		}
		if (student.getBirthDate() == null) {
			messages.add("Student JoinDate Cannot be null");
		}
		         if (isDuplicateRollNumber()) {
            messages.add("duplicate rollNumber.");
        }
	}

	public void validateForAdd(List<String> messages, Student student) {
		validateStudent(messages, student);
	}

	public void validateForUpdate(List<String> messages, Student student) {
		validateStudent(messages, student);
	}

	public void validateForDelete(List<String> messages, Student student) {
		validateStudent(messages, student);

	}
	 private boolean isDuplicatePerson() {
	        try {
	            return service.list().stream()
	                    .anyMatch(s -> s.getId().equals(student.getId()) == false //
	                            && s.getName().equals(student.getName())//
	                            && s.getGender().equals(student.getGender())//
	                            && s.getBirthDate().equals(student.getBirthDate()));
	        } catch (ServiceException e) {
	            return false;
	        }
	    }

	    private boolean isDuplicateRollNumber() {
	        try {
	            return service.list().stream()//
	                    .anyMatch(s -> s.getId().equals(student.getId()) == false //
	                            && s.getStandard() == student.getStandard() //
	                            && s.getDivision().equals(student.getDivision())//
	                            && s.getRollNumber() == student.getRollNumber());
	        } catch (ServiceException e) {
	            return false;
	        }
	    }  

}
