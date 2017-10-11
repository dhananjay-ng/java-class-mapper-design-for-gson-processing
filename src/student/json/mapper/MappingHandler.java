package student.json.mapper;

import java.lang.reflect.Type;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import test.Book;

public class MappingHandler {

	public Mapper getMapper(Mappings mappings,String jsonText, Object bk, ErrorMessages errorsMessages,
			HttpServletRequest request,Class<?> resource) {
			Map<String, String> data=null;
	    Mapper mapper = new Mapper(mappings,"yyyy-MM-dd",jsonText , request, data,bk, errorsMessages,resource);
	    return mapper;

	
	}
	public Mapper getMapper(String className,String jsonText, Object bk, ErrorMessages errorsMessages,
			HttpServletRequest request,Class<?> resource) {
			Map<String, String> data=null;
	    Mapper mapper = new Mapper(BoMappings.getBoMappings(className),"yyyy-MM-dd",jsonText , request, data,bk, errorsMessages,resource);
	    return mapper;

	
	}

	public void mapFormToBo(Mappings mappings,HttpServletRequest request, String jsonText, Object bk,
			ErrorMessages errorsMessages,Class<?> resource) {
	    Mapper mapper = getMapper(mappings,jsonText, bk, errorsMessages, request,resource);
	    mapper.mapToBo();

	}
	public void mapFormToBo(String className,HttpServletRequest request, String jsonText, Object bk,
			ErrorMessages errorsMessages,Class<?> resource) {
	    Mapper mapper = getMapper(className,jsonText, bk, errorsMessages, request,resource);
	    mapper.mapToBo();

	}
	
	
}
