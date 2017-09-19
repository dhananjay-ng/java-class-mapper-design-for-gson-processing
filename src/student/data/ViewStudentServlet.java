package student.data;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewStudent")
public class ViewStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		StudentService service = new StudentService();
		try {
			Student s = service.findById(id);
			System.out.println(s.getGender());
			request.setAttribute("student", service.findById(id));
			Map<String, String> form=StudentFormToBoMapping.mapBoToForm(s);
            forwardToView(form, request, response);

		} catch (StudentServiceException e) {
			e.printStackTrace();
			response.sendRedirect("/listStudents");

			return;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			doGet(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	 private static void forwardToView(Map<String, String> form, //
	            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.setAttribute("student", form);
	        request.getRequestDispatcher("/WEB-INF/pages/view_students.jsp").forward(request, response);
	    }


}
