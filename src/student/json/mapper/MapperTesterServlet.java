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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import student.data.Student;
import test.Author;
import test.Book;

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
			String jsonText= request.getReader().lines().collect(Collectors.joining());
			out.println(jsonText);

			/*ErrorMessages errorsMessages =new ErrorMessages();
			Student student=new Student(); 
			 mapFormToBo( request,  jsonText, student,errorsMessages);
			if(errorsMessages.getErrors()!=null)
			{
				if(!errorsMessages.getErrors().isEmpty()){
				out.println(errorsMessages.getErrors());
				}
			} 
			
			 
			
			out.println(student.toString());*/
			
			/*JsonReader jsonReader = new JsonReader(new StringReader(c));
			try {
			    while(jsonReader.hasNext()){
			        JsonToken nextToken = jsonReader.peek();
			        System.out.println(nextToken);

			        if(JsonToken.BEGIN_OBJECT.equals(nextToken)){

			            jsonReader.beginObject();

			        } else if(JsonToken.NAME.equals(nextToken)){

			            String name  =  jsonReader.nextName();
			            System.out.println(name);

			        } else if(JsonToken.STRING.equals(nextToken)){

			            String value =  jsonReader.nextString();
			            System.out.println(value);

			        } else if(JsonToken.NUMBER.equals(nextToken)){

			            long value =  jsonReader.nextLong();
			            System.out.println(value);

			        }
			    }
			} catch (IOException e) {
			    e.printStackTrace();
			}
*/
			/*
*/
			Book bk=new Book();
			Author author=new Author();
			author.setFirstName("Dhananjay");
			author.setLastName("Nagargoje");
			bk.setId(1);
			bk.setName("XYZ");
			bk.setAuthor(author);
			//out.println(new Gson().toJson(bk));
			
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(jsonText);
			if(jsonTree.isJsonObject()){
			    JsonObject jsonObject = jsonTree.getAsJsonObject();

			    JsonElement f1 = jsonObject.get("id");

			    JsonElement f2 = jsonObject.get("name");
		        JsonElement f3 = jsonObject.get("author");


			    if(f3.isJsonObject()){
			        JsonObject f3Obj = f3.getAsJsonObject();
			        JsonElement f4 = f3Obj.get("firstName"); 
				    JsonElement f5 = f3Obj.get("lastName");
			      
				   // out.println(f1+"__"+f2+"_"+f3+"__"+f4+"_"+f5);


			    }
			    Book bk1 =new Book();
			    ErrorMessages errorsMessages=new ErrorMessages();
			    MappingHandler mp=new  MappingHandler();
			    mp.mapFormToBo("test.Book",request,jsonText,  bk1,
						 errorsMessages,Book.class);

out.println(new Gson().toJson(bk1, Book.class));			}
			

		}
		
	}
	
	/*public static Mappings getMappings() {

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

	}*/

	

	
}
