package student.web;

public class UserTest {

	public static void main(String[] args) {

		User user1 = new User();
		user1.setUserId("dhanu10896");
		user1.setUserName("Dhananjay");
		user1.setPassword("Aa@1aaaa");

		System.out.println("UserId :" + user1.getUserId() + "\nUserName :" + user1.getUserName() + "\nPassword :"
				+ user1.getPassword());
		

		UserDao test=new UserDao();
	
		try {
			System.out.println(test.get(user1.getUserId()).getUserName());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
