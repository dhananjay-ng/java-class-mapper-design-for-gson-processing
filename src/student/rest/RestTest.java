package student.rest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class RestTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		try {
			FileReader reader;

			reader = new FileReader("src/config.properties");
			Properties p=new Properties();  
		    p.load(reader);  
		      
		    System.out.println(p.getProperty("users"));  
		    System.out.println(p.getProperty("students"));  

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      
		    

	}

}
