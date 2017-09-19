package student.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
		List<String> messages = new ArrayList<>();

		String userName = request.getParameter("username");
		String userId = request.getParameter("userid");
		String password = request.getParameter("password");
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		UserService service = new UserService();
		UserValidator userValidator = new UserValidator();
		userValidator.validateForAdd(user, messages);
		if (messages.size() > 0) {
			request.setAttribute("user", user);
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
		}
		try {
			service.add(user);
			response.sendRedirect("/login");

		} catch (UserServiceException e) {
			// TODO Auto-generated catch block
			messages.add(e.getMessage());
		}
		if (messages.size() > 0) {
			request.setAttribute("user", user);

			request.setAttribute("messages", messages);
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
		}

	}

}
