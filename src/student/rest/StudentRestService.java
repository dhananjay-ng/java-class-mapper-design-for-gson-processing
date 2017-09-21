package student.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import student.web.UserServiceException;

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

	public String getPathParameterClassType(HttpServletRequest request) {
		String type;
		String pathInfo = request.getPathInfo();
		String[] pathParts = pathInfo.split("/");
		try {
			type = pathParts[1];
			if(type.equals("students")){
				type="student.data.StudentService";
			}
			if(type.equals("users")){
				type="student.web.UserService";
			}
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
		return type;

	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StudentService studentService = new StudentService();
		UserService userService=new UserService();
		response.setContentType("application/json;charset=UTF-8");
		String id = getPathParameterId(request);
		String type = getPathParameterClassType(request);
		
	
		
		// REFLECTION		 
		Method[] methods = StudentService.class.getMethods();
		for (Method method : methods) {
			System.out.println("method = " + method.getName());
		}
		try {
			System.out.println(Class.forName("student.data.Student"));
			System.out.println(Class.forName("student.data.Student").getSimpleName());
			System.out.println(Class.forName("student.data.Student").getFields());
			Method method;
			try {
				method = StudentService.class.getMethod("list");
				
				try {
					Class<?> c = Class.forName(type);
					Constructor<?> cons = c.getConstructor(String.class);
					Class<?> clazz = Class.forName(className);
					Constructor<?> ctor = clazz.getConstructor(String.class);
					Object object = ctor.newInstance(new Object[] { ctorArgument });
					//System.out.println(method.invoke(studentService));
					System.out.println(StudentService.class.getMethod("findById",String.class).invoke(studentService, id));
					System.out.println(c.getMethod("findById",String.class).invoke(cons.newInstance(""), id));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// REFLECTION END
		
			if (type.isEmpty()) {
			try (PrintWriter out = response.getWriter()) {
				out.println("Enter Proper Path");
			}

		} else {
			if("students".equals(type)){
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
						List<Student> st=new ArrayList<>();
						st.add(studentService.findById(id));
						out.print(new Gson().toJson(st));
					} catch (StudentServiceException e) {
						response.sendError(HttpServletResponse.SC_NO_CONTENT, "NOT DATA FOUND");
					}
				}
			}
			}
			else if("users".equals(type)){
				try (PrintWriter out = response.getWriter()) {
					try {
						List<User> st=new ArrayList<>();
						st.add(userService.findById(id));
					out.print(new Gson().toJson(st));
					} catch (UserServiceException e) {
						response.sendError(HttpServletResponse.SC_NO_CONTENT, "NOT DATA FOUND");
					}
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
