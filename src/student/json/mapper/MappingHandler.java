package student.json.mapper;

import java.lang.reflect.Type;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import test.Book;

public class MappingHandler {

	public Mapper getMapper(String className,String jsonText, Object bk, ErrorMessages errorsMessages,
			HttpServletRequest request,Class<?> resource) {
	//	Type type = new TypeToken<Map<String, String>>() {}.getType();
	//	Map<String, String> data = new Gson().fromJson(jsonText, type);
     //   System.out.println(data);
		Map<String, String> data=null;
	    Mapper mapper = new Mapper(BoMappings.getBoMappings(className),"yyyy-MM-dd",jsonText , request, data,bk, errorsMessages,resource);
	    return mapper;

	
	}

	public void mapFormToBo(String className,HttpServletRequest request, String jsonText, Object bk,
			ErrorMessages errorsMessages,Class<?> resource) {
	    Mapper mapper = getMapper(className,jsonText, bk, errorsMessages, request,resource);
	    mapper.mapToBo();

	}
}
