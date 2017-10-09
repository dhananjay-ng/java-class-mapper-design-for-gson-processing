import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.glassfish.jersey.internal.guava.Lists;

import com.google.gson.Gson;

public class GsonTests {

	public static void main(String[] args) {


		Gson gson=new Gson();
		System.out.println(gson.toJson("ABC"));
		
		Collection<Integer> ints = new ArrayList<>();
		ints.add(2);
		ints.add(4);
		System.out.println(gson.fromJson(gson.toJson(ints),ArrayList.class));
		
		
String jsonPropertyName="author";
String cap = jsonPropertyName.substring(0, 1).toUpperCase() + jsonPropertyName.substring(1);
String  methodName = "set" + cap;
System.out.println(methodName);
	}

}
