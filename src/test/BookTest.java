package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.google.gson.Gson;

public class BookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Book bk=new Book();
		Author author=new Author();
		author.setFirstName("Dhananjay");
		author.setLastName("Nagargoje");
		bk.setId(1);
		bk.setName("XYZ");
		bk.setAuthor(author);
		List<String> genere=new ArrayList<String>();
		genere.add("Adventure");
		genere.add("Action");
		bk.setGenere(genere);
		Map<String,String> mp=new HashMap<>();
		mp.put("Dhananjay", "XYZ");
		bk.setMapTest(mp);
		List<Author> authorList=new ArrayList<Author>();
		authorList.add(author);
		bk.setAuthorList(authorList);
		System.out.println(new Gson().toJson(bk,Book.class));
	}

}
