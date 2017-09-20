package student.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import student.data.ServiceException;
import student.data.Student;
import student.data.StudentService;
import student.data.StudentServiceException;

@WebServlet("/rest/students")
public class StudentRestService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentRestService() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentService studentService = new StudentService();
		response.setContentType("application/json;charset=UTF-8");
		System.out.println(request.getMethod());
		String id = request.getParameter("id");
		if (id == null || id.isEmpty()) {
			try (PrintWriter out = response.getWriter()) {
				try {

					out.print(new Gson().toJson(studentService.list()));
				} catch (StudentServiceException e) {
					e.printStackTrace();
				}
			}
		} else {
			try (PrintWriter out = response.getWriter()) {
				try {

					out.print(new Gson().toJson(studentService.findById(id)));
				} catch (StudentServiceException e) {
					response.sendError(HttpServletResponse.SC_NO_CONTENT, "NOT DATA FOUND");
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getMethod());

		Student student = new Gson().fromJson(request.getReader(), Student.class);
		StudentService studentService = new StudentService();
		try {
			studentService.add(student);
		} catch (ServiceException e) {

			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "BAD REQUEST");
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Gson().fromJson(request.getReader(), Student.class);
		StudentService studentService = new StudentService();
		try {
			studentService.update(student);
		} catch (ServiceException e) {

			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "BAD REQUEST");
		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		StudentService service = new StudentService();
		try {
			service.remove(service.findById(id));

		} catch (StudentServiceException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "NO STUDENT FOUND");

		}

	}

}
