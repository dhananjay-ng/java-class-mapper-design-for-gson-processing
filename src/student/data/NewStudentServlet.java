package student.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newStudent")
public class NewStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service = new StudentService();
	List<String> messages = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/new_student.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		messages.clear();
		String action = request.getParameter("action");
		if ("cancel".equalsIgnoreCase(action)) {
			response.sendRedirect("/listStudents");

		} else if ("add".equalsIgnoreCase(action)) {
			studentAdd(request, response);
		}
	}

	private void studentAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		messages.clear();

		Student student = new Student();
		StudentFormToBoMapping.mapFormToBo(student, messages, request, response);
		
		if (messages.size() > 0) {
			Map<String, String> form = StudentFormToBoMapping.mapBoToForm(student);
			NewStudentServlet.forwardToView(form, messages, request, response);
            return;

		}
		
		messages.clear();

		if (messages.size() > 0) {
			Map<String, String> form = StudentFormToBoMapping.mapBoToForm(student);
			NewStudentServlet.forwardToView(form, messages, request, response);
            return;

		}
		messages.clear();

		try {
			service.add(student);
		} catch (ValidatorException e) {
			Map<String, String> form = StudentFormToBoMapping.mapBoToForm(student);
			NewStudentServlet.forwardToView(form, e.getMessages(), request, response);
            return;


		} catch (ServiceException e) {

		}
		request.getRequestDispatcher("/listStudents").forward(request, response);

	}

	public static void forwardToView(Map<String, String> form, List<String> messages, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("student", form);
		request.setAttribute("messages", messages);
		System.out.println("\n"+messages.toString()+"\n");
		request.getRequestDispatcher("/WEB-INF/pages/new_student.jsp").forward(request, response);
	}

}
