package student.data;

import java.util.List;

public class StudentDaoTest {
	public static void assertTrue(String message, boolean value) {

		System.out.println(message);
		if (value == true) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}

	}

	public static void success(String message) {
		assertTrue(message, true);
	}

	public static void failure(String message) {
		assertTrue(message, false);
	}

	public static void message(String message) {

		System.out.println(message);
	}

	public static void main(String[] args) {

		Student student1 = StudentTest.createStudent1();
		Student student3 = StudentTest.createStudent3();
		StudentDao studentDao = new StudentDao();
		/*try {
			studentDao.add(student1);
			assertTrue("Adding student1", studentDao.get(student1.getId()).getId().equals(student1.getId()));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			studentDao.add(student3);
			assertTrue("Adding student3", studentDao.get(student3.getId()).getId().equals(student3.getId()));

		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		/*
		try {
		student1.setName("dkndn");
			studentDao.update(student1);
			assertTrue("Updating student1", studentDao.get(student1.getId()).getId().equals(student1.getId()));

		} catch (DaoException e) {
			e.printStackTrace();
		}

		try {
			List<Student> studentList = studentDao.list();
			System.out.println(studentList.size());
			for (Student s : studentList) {
				System.out.println(s.getName());
			}
			studentDao.remove(student1);
			studentDao.remove(student3);


		} catch (DaoException e) {
			e.printStackTrace();
		}*/

		
	}

}
