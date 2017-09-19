package student.data;

import java.util.Iterator;
import java.util.List;

public class StudentServiceTest {
	public static void message(String message) {

		System.out.println(message);
	}

	public static void main(String[] args) {
		Student student1 = StudentTest.createStudent1();
		Student student2 = StudentTest.createStudent1();
		Student student3 = StudentTest.createStudent3();
		Student student4 = StudentTest.createStudent4();
		StudentService studentService = new StudentService();

		try {
			try {
				studentService.add(student1);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertTrue("Adding student1 ", studentService.findById(student1.getId()).getId() == student1.getId());

		} catch (StudentServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message("\nAdding alredy existing student Exception expected");
		
			try {
				studentService.add(student2);
			} catch (ServiceException e) {
				message("Test Passed");			}
		
		try {
			studentService.add(student3);
			assertTrue("\nAdding student3 ", studentService.findById(student3.getId()).getId() == student3.getId());

		} catch (ServiceException e) {
			e.printStackTrace();
		}

		try {
			studentService.add(student4);
			assertTrue("\nAdding student4 ", studentService.findById(student4.getId()).getId() == student4.getId());

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Student> studentList;
		try {
			studentList = studentService.list();
			message("call to list() should return soreted student list");
			for (Student s : studentList) {
				System.out.println(s.getName());
			}
			assertTrue("Testing wheather list is sorted", isSorted(studentList));

		} catch (StudentServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean isSorted(List<Student> studentList) {
		Iterator<Student> iter = studentList.iterator();
		if (!iter.hasNext()) {
			return true;
		}
		String t = iter.next().getName();
		while (iter.hasNext()) {
			String t2 = iter.next().getName();
			if (t.compareTo(t2) > 0) {
				return false;
			}
			t = t2;
		}
		return true;
	}

	public static void assertTrue(String message, boolean value) {

		System.out.println(message);
		if (value == true) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}

	}
}
