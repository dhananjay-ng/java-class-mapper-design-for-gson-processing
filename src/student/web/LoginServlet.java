package student.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	List<String> messages = new ArrayList<>();

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		messages.clear();
		request.getSession(true);

		forwardToView(messages, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userid");
		String password = request.getParameter("password");

		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);

		UserService service = new UserService();

		if (userId == null || userId.trim().length() == 0) {
			messages.add("userid is required.");
		} else if (password == null || password.trim().length() == 0) {
			messages.add("password is required.");
		}
		if (messages.size() > 0) {

			forwardToView(messages, request, response);
			return;
		}
		messages.clear();
		try {
			if (service.findById(userId).getUserId().equals(userId)
					&& service.findById(userId).getPassword().equals(password)) {

				HttpSession session = request.getSession(true);
				session.setAttribute("userid", userId);
				session.setAttribute("user", service.findById(userId));

				response.sendRedirect("/listStudents");

			}

		} catch (UserServiceException e) {

			messages.add("invalid userid/password.");
			if (messages.size() > 0) {

				forwardToView(messages, request, response);
				return;}

		}
	}

	private static void forwardToView(List<String> messages, //
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(messages.size());
		if (messages.isEmpty()) {
			request.setAttribute("messages", null);

		} else {System.out.println(messages.size());
			request.setAttribute("messages", messages);

		}
		request.setAttribute("messages", messages);
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}
}
