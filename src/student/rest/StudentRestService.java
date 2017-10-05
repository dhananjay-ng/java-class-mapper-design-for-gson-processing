package student.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import student.data.Service;
import student.data.ServiceException;
import student.data.Student;
import student.data.StudentService;
import student.web.User;
import student.web.UserService;

@WebServlet("/rest/*")
public class StudentRestService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentRestService() {
		super();
	}

	public String getPathParameterId(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		String id;
		try {
			id = pathParts[2];
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
		return id;
	}

	public String getPathParameterClass(HttpServletRequest request) {
		String type;
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		try {
			type = pathParts[1];
			InputStream is = StudentRestService.class.getResourceAsStream("/config.properties");
			if (is != null) {
				Properties p = new Properties();
				p.load(is);
				return p.getProperty(type);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";

		} catch (IndexOutOfBoundsException e) {
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		/*
		 * String id = getPathParameterId(request); String type =
		 * getPathParameterClass(request);
		 */
		EndPoint endPoint = new URLHandler().getEndPoint(request);
		if (endPoint.service == null) {
			try (PrintWriter out = response.getWriter()) {
				out.println("Enter Proper Path");
			}

		} else {
			if (endPoint.id == null || endPoint.id.isEmpty()) {
				try (PrintWriter out = response.getWriter()) {
					try {
						// operation(id, type, MethodeNameConstants.LIST,
						// request, response);
						out.println(new Gson().toJson(endPoint.service.list(endPoint.query)));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				try (PrintWriter out = response.getWriter()) {
					try {
						out.println(new Gson().toJson(endPoint.service.findById(endPoint.id)));

					} catch (Exception e) {
						response.sendError(HttpServletResponse.SC_NO_CONTENT, "NOT DATA FOUND");
					}
				}
			}

		}
	}

	public void operation(String id, String type, String methode, HttpServletRequest request,
			HttpServletResponse response) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			try (PrintWriter out = response.getWriter()) {

				Constructor c = Class.forName(type).getConstructor();
				Service service = (Service) c.newInstance();

				try {
					if (MethodeNameConstants.ADD.equals(methode)) {

						if (Class.forName(type).equals(StudentService.class)) {
							service.add(gson.fromJson(request.getReader(), Student.class));
							out.println("Student with id =" + id + " added.");

						} else if (Class.forName(type).equals(UserService.class)) {
							service.add(gson.fromJson(request.getReader(), User.class));

							out.println("User with id =" + id + " added.");

						}
					}
				} catch (ServiceException e) {

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EndPoint endPoint = new URLHandler().getEndPoint(request);

		if (endPoint.service == null) {
			try (PrintWriter out = response.getWriter()) {
				out.println("Enter Proper Path");
			}
		} else {
			try {
				
			
				endPoint.service.add(new Gson().fromJson(request.getReader(),endPoint.getResource()));
				
				
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (JsonIOException e) {
    			e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			//operation(id, type, "add", request, response);

		}
		/*
		 * Student student = new Gson().fromJson(request.getReader(),
		 * Student.class); StudentService studentService = new StudentService();
		 * try { studentService.add(student); } catch (ServiceException e) {
		 * 
		 * response.sendError(HttpServletResponse.SC_BAD_REQUEST,
		 * "BAD REQUEST"); }
		 */
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Student student = new Gson().fromJson(request.getReader(), Student.class);
		Service studentService = new StudentService();
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
		Service service = new StudentService();
		try {
			service.remove(service.findById(id));

		} catch (ServiceException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "NO STUDENT FOUND");

		}

	}

}
