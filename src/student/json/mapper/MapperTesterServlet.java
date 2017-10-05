package student.json.mapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Date;
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

			ErrorMessages errorsMessages =new ErrorMessages();
			Student student=new Student();
			 mapFormToBo( request,  jsonText, student,errorsMessages);
			if(errorsMessages.getErrors()!=null)
			{
				if(!errorsMessages.getErrors().isEmpty()){
				out.println(errorsMessages.getErrors());
				}
			}
			 
			
			out.println(student.toString());

		}
		
	}
	
	private static Mappings getMappings() {

	    Mappings mappings = new Mappings();

	    final MappingSource MAP = MappingSource.MAP;

	    mappings.addMapping(MAP, "id", "id", String.class, 0, true);
	    mappings.addMapping(MAP, "name", "name", String.class, 0, true);
	    mappings.addMapping(MAP, "birthDate", "birthDate", Date.class, 0, true);
	    mappings.addMapping(MAP, "joinDate", "joinDate", Date.class, 0, true);
	    mappings.addMapping(MAP, "gender", "gender", String.class, 0, true);
	    mappings.addMapping(MAP, "standard", "standard", Integer.class, 0, true);
	    mappings.addMapping(MAP, "division", "division", String.class, 0, true);
	    mappings.addMapping(MAP, "rollNumber", "rollNumber", Integer.class, 0, true);

	    return mappings;

	}

	private Mapper getMapper(String jsonText, Student student, ErrorMessages errorsMessages,
			HttpServletRequest request) {
		Type type = new TypeToken<Map<String, String>>() {}.getType();
		Map<String, String> data = new Gson().fromJson(jsonText, type);
        System.out.println(data);
	    Mapper mapper = new Mapper(getMappings(),"yyyy-MM-dd",jsonText , request, data,student, errorsMessages);
	    return mapper;

	
	}

	private void mapFormToBo(HttpServletRequest request, String jsonText, Student student,
			ErrorMessages errorsMessages) {
	    Mapper mapper = getMapper(jsonText, student, errorsMessages, request);
	    mapper.mapToBo();

	}

	
}
