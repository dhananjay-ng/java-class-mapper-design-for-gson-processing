package student.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listStudents")
public class ListStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Service studentService = new StudentService();
	List<String> messages = new ArrayList<>();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("students", studentService.list(null));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/list_students.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
