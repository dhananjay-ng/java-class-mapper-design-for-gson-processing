package student.json.mapper;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

import test.Author;
import test.Book;

public class Test {

	public static void main(String[] args) {
	//String s="{\"students\":[\"student.data.StudentService\",\"student.data.Student\"] }";
	//Employee employee = gson.fromJson(br, Employee.class);
		/*
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
		}*/
		/*Book bk=new Book();
		System.out.println(Author.class.getName());
		*/		
	  Author au=new Author();
		Book myBean=new Book();
	      try {
	 		PropertyUtils.setSimpleProperty(myBean, "id", 1);
			PropertyUtils.setSimpleProperty(myBean, "name", "Dhananjay");
			PropertyUtils.setSimpleProperty(au, "firstName", "DGN");
			PropertyUtils.setSimpleProperty(au, "lastName", "DGN");
			PropertyUtils.setSimpleProperty(myBean, "author", au);
			
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println(myBean.getId());
	      System.out.println(myBean.getAuthor().getFirstName());
	      
	}

}
