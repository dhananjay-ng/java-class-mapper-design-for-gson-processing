package test;

public class BookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Book bk=new Book();
		Author author=new Author();
		author.setFirstName("Dhananjay");
		author.setLastName("Nagargoje");
		bk.setId(1);
		bk.setName("XYZ");
		
		System.out.println(bk);
	}

}
