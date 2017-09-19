package student.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class StudentTest {

	public static void assertTrue(String message, boolean value) {

		System.out.println(message);
		if (value == true) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student student1 = createStudent1();

		Student student2 = createStudent1();

		assertTrue("Equals Test For student1 & student 2 :", student1.equals(student2) == true);
		System.out.println();
		Student student3 = createStudent3();
		assertTrue("Inequality Test For student1 & student 3:", student1.equals(student3) == false);

		System.out.println("\nUsing toSting() On Student1 :\n" + student1.toString());
		System.out.println("\nstudent1 HashCode :" + student1.hashCode());
		System.out.println("student2 HashCode :" + student2.hashCode());
		System.out.println("student3 HashCode :" + student3.hashCode());

		assertTrue("\nTesting student2 hashCode equals student3 hashCode",
				(student2.hashCode() == student3.hashCode()) == true);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student1);
		studentSet.add(student2);
		studentSet.add(student2);
		System.out.println(studentSet);
		assertTrue("\nTesting Wheather HashSet is having distinct values", studentSet.size() == 2);
		assertTrue("\nTesting wheather HashSet contains student1", studentSet.contains(student1));
		assertTrue("Testing wheather HashSet contains student2", studentSet.contains(student2));
		assertTrue("Testing wheather HashSet contains student3", studentSet.contains(student2));

	}

	public static Student createStudent3() {
		Student student3 = new Student();
		student3.setId("3");
		student3.setName("Suruchi");
		student3.setGender("M");

		try {
			student3.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1996"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			student3.setJoinDate(new SimpleDateFormat("dd/MM/yyyy").parse("24/07/2017"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		student3.setStandard(10);
		student3.setDivision("A");
		student3.setRollNumber(12);
		return student3;
	}

	public static Student createStudent1() {
		Student student1 = new Student();
		student1.setId("2");
		student1.setName("Dhananjay");
		student1.setGender("M");

		try {
			student1.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1996"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			student1.setJoinDate(new SimpleDateFormat("dd/MM/yyyy").parse("24/07/2017"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		student1.setStandard(10);
		student1.setDivision("A");
		student1.setRollNumber(12);
		return student1;
	}

	public static Student createStudent4() {
		Student student4 = new Student();
		student4.setId("zzz");
		student4.setName("Nandan");
		student4.setGender("M");

		try {
			student4.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/08/1996"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			student4.setJoinDate(new SimpleDateFormat("dd/MM/yyyy").parse("24/07/2017"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		student4.setStandard(10);
		student4.setDivision("A");
		student4.setRollNumber(12);
		return student4;
	}
}
