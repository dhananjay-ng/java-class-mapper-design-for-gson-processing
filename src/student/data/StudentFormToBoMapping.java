package student.data;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentFormToBoMapping")
public class StudentFormToBoMapping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public StudentFormToBoMapping() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static Map<String, String> mapBoToForm(Student student) {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Map<String, String> form = new LinkedHashMap<>();
        form.put("id", student.getId());
        form.put("name", student.getName());
        form.put("gender", student.getGender());
        form.put("birthDate", student.getBirthDate() != null ? simpleDateFormatter.format(student.getBirthDate()) : "");
        form.put("joinDate", student.getJoinDate() != null ? simpleDateFormatter.format(student.getJoinDate()) : "");
        form.put("standard", student.getStandard() > 0 ? Integer.toString(student.getStandard()) : "");
        form.put("division", student.getDivision());
        form.put("rollNumber", student.getRollNumber() > 0 ? Integer.toString(student.getRollNumber()) : "");
        return form;
    }

    public static void mapFormToBo(Student student,List<String> messages,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	messages.clear();
		student.setId(request.getParameter("id"));
		
		if (request.getParameter("name") == null || request.getParameter("name").trim().length() == 0) {
			messages.add("name is required.");
		}
		else{
		student.setName(request.getParameter("name"));
		}
		
		if (request.getParameter("gender") == null || request.getParameter("gender").trim().length() == 0) {
			messages.add("Enter Valid gender");

		}
		else{
			student.setGender(request.getParameter("gender"));

		}
		
		try {
			student.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthDate")));
		} catch (ParseException e) {
			messages.add("Enter Valid Birth date in yyyy-mm-dd format");
			//Ignore
		}
		try {
			student.setJoinDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("joinDate")));
		} catch (ParseException e) {
			messages.add("Enter Valid Joining date in yyyy-mm-dd format");
			//Ignore
		}
		if (request.getParameter("standard") == null || request.getParameter("standard").length() == 0) {
			//student.setStandard(0);
			messages.add("Enter Valid Standard");

		} else {
			try {
				student.setStandard(Integer.parseInt(request.getParameter("standard")));
			} catch (Exception e) {
				messages.add("Enter Valid Standard");
			}
		}
		if (request.getParameter("division") == null ||request.getParameter("division").trim().length() == 0) {
			messages.add("Student Division can not be empty");

		}else{
		student.setDivision(request.getParameter("division"));
		}

		

		if (request.getParameter("rollNumber") == null || request.getParameter("rollNumber").trim().length() == 0) {
			messages.add("Enter Valid Roll Number");

		} else {
			try {
				student.setRollNumber(Integer.parseInt(request.getParameter("rollNumber")));
			} catch (Exception e) {
				messages.add("Enter Integer value for roll number");
			}
		}
		

    }


}
