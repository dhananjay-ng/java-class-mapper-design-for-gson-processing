package student.rest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
			FileReader reader;

			reader = new FileReader("src/config.properties");
			Properties p=new Properties();  
		    p.load(reader);  
		      
		    return p.getProperty(type);  
			/*if (type.equals("students")) {
				type = "student.data.StudentService";
			}
			if (type.equals("users")) {
				type = "student.web.UserService";
			}*/
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	     catch (IndexOutOfBoundsException e) {
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ERROR";

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		String id = getPathParameterId(request);
		String type = getPathParameterClass(request);
		if (type.isEmpty()) {
			try (PrintWriter out = response.getWriter()) {
				out.println("Enter Proper Path");
			}

		} else {
			if (id == null || id.isEmpty()) {
				try (PrintWriter out = response.getWriter()) {
					try {
						operation(id, type, "list", request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				try (PrintWriter out = response.getWriter()) {
					try {
						operation(id, type, "findById", request, response);
					} catch (Exception e) {
						response.sendError(HttpServletResponse.SC_NO_CONTENT, "NOT DATA FOUND");
					}
				}
			}

		}
	}

	public void operation(String id, String type, String methode, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			try (PrintWriter out = response.getWriter()) {

				Class<?> clazz = Class.forName(type);
				if ("findById".equals(methode)) {
					List<Object> lt = new ArrayList<>();
					lt.add(clazz.getMethod("findById", String.class).invoke(clazz.getConstructor().newInstance(), id));
					out.print(new Gson().toJson(lt));
				
				} else if ("list".equals(methode)) {
					out.print(new Gson().toJson(clazz.getMethod("list").invoke(clazz.getConstructor().newInstance())));
				
				} else if ("add".equals(methode)) {
					
					if (Class.forName(type).equals(StudentService.class)) {
						clazz.getMethod("add", Student.class).invoke(clazz.getConstructor().newInstance(),
								new Gson().fromJson(request.getReader(), Student.class));
						out.println("Student with id ="+id+" added.");
					
					} else if (Class.forName(type).equals(UserService.class)) {
						clazz.getMethod("add", User.class).invoke(clazz.getConstructor().newInstance(),
								new Gson().fromJson(request.getReader(), User.class));
						out.println("User with id ="+id+" added.");

					}
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = getPathParameterId(request);
		String type = getPathParameterClass(request);
		if (type.isEmpty()) {
			try (PrintWriter out = response.getWriter()) {
				out.println("Enter Proper Path");
			}
		} else {
			operation(id, type, "add", request, response);

		}
		/*
		Student student = new Gson().fromJson(request.getReader(), Student.class);
		StudentService studentService = new StudentService();
		try {
			studentService.add(student);
		} catch (ServiceException e) {

			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "BAD REQUEST");
		}*/
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
