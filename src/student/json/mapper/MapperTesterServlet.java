package student.json.mapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import student.data.Student;
import student.rest.Login;

@WebServlet("/mapperTester")
public class MapperTesterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MapperTesterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			String jsonText = request.getReader().lines().collect(Collectors.joining());
			out.println(jsonText);

			ValidationResult vr=new ValidationResult();
			Student student=new Student();
			 mapFormToBo( request,  jsonText, student,vr);
			
			out.println(student.getId());

		}
		
	}
	
	private static Mappings getMappings() {

	    Mappings mappings = new Mappings();

	    final MappingSource MAP = MappingSource.MAP;

	    mappings.addMapping(MAP, "id", "id", String.class, 0, true);
	    mappings.addMapping(MAP, "name", "name", String.class, 0, true);
	    mappings.addMapping(MAP, "gender", "gender", String.class, 0, true);
	    mappings.addMapping(MAP, "standard", "standard", Integer.class, 0, true);
	    mappings.addMapping(MAP, "division", "division", String.class, 0, true);
	    mappings.addMapping(MAP, "rollNumber", "rollNumber", Integer.class, 0, true);

	    return mappings;

	}

	private Mapper getMapper(String jsonText, Student student, ValidationResult validationResult,
			HttpServletRequest request) {
		String requestData;
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> data = new Gson().fromJson(jsonText, type);
		
	    Mapper mapper = new Mapper(getMappings(),jsonText ,"yyyy-MM-dd", request, data,student, validationResult);
	    return mapper;

	
	}

	private void mapFormToBo(HttpServletRequest request, String jsonText, Student student,
	        ValidationResult vr) {
	    Mapper mapper = getMapper(jsonText, student, vr, request);
	    mapper.mapToBo();

	}

	
}
