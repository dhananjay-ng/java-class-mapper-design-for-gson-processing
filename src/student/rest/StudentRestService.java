package student.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import student.data.Student;
import student.data.StudentService;
import student.data.StudentServiceException;

@WebServlet("/students/*")
public class StudentRestService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentRestService() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentService studentService = new StudentService();
		response.setContentType("application/json;charset=UTF-8");
		System.out.println(request.getMethod());
		String id = request.getParameter("id");
		if (id==null||id.isEmpty()) {
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
					response.sendError(response.SC_NOT_FOUND,
				            "NOT DATA FOUND");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getMethod());
		
		Student obj = new Gson().fromJson(request.getReader(), Student.class);

		try (PrintWriter out = response.getWriter()) {
			out.print(request.getMethod());
			System.out.println("possttttt");

		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			out.print(request.getMethod());
			System.out.println("putttttt");

		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			out.print(request.getMethod());
			System.out.println("dlkdjlka");
		}
	}

}
