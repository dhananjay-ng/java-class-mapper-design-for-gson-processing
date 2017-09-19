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

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService service = new StudentService();
	List<String> messages = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		messages.clear();
		String id = request.getParameter("id");
		try {
			Student student = service.findById(id);
			Map<String, String> form = StudentFormToBoMapping.mapBoToForm(student);
			EditStudentServlet.forwardToView(form, messages, request, response);
			return;

		} catch (StudentServiceException e) {
			e.printStackTrace();
			return;

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Student();
		messages.clear();
		String action = request.getParameter("action");
		if ("cancel".equalsIgnoreCase(action)) {
			response.sendRedirect("/listStudents");

		} else if ("update".equalsIgnoreCase(action)) {
			StudentFormToBoMapping.mapFormToBo(student, messages, request, response);
			if (messages.size() > 0) {
				Map<String, String> form = StudentFormToBoMapping.mapBoToForm(student);
				EditStudentServlet.forwardToView(form, messages, request, response);
			}
			messages.clear();

			if (messages.size() > 0) {
				Map<String, String> form = StudentFormToBoMapping.mapBoToForm(student);
				EditStudentServlet.forwardToView(form, messages, request, response);
			}
			try {

				service.update(student);
				System.out.println("EXECUTED");
				response.sendRedirect("/viewStudent?" + student.getId());

			} catch (ValidatorException e) {
				Map<String, String> form = StudentFormToBoMapping.mapBoToForm(student);
				NewStudentServlet.forwardToView(form, e.getMessages(), request, response);
				return;

			} catch (ServiceException e) {
				e.printStackTrace();

			}
		}
	}

	public static void forwardToView(Map<String, String> form, List<String> messages, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("student", form);
		if (messages.isEmpty()) {
			request.setAttribute("messages", null);

		} else {
			request.setAttribute("messages", messages);

		}
		request.getRequestDispatcher("/WEB-INF/pages/edit_student.jsp").forward(request, response);
	}

}
