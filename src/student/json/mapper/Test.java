package student.json.mapper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {

	public static void main(String[] args) {
	//String s="{\"students\":[\"student.data.StudentService\",\"student.data.Student\"] }";
	//Employee employee = gson.fromJson(br, Employee.class);
		
		String jsonString = "{\"aNumber\":2}";

		try {
			String url = "editTest.jsp?details=" + URLEncoder.encode(jsonString, "UTF-8");
			System.out.println(url);
			String decode = URLDecoder.decode(url);
			URLDecoder.decode(url, "UTF-8");
			System.out.println(decode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	
	}

}
