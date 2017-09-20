package student.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentService {

	private StudentDao studentDao = new StudentDao();
	private StudentValidator studentValidator;
	List<String> messages = new ArrayList<>();

	public void add(Student student) throws ServiceException {
		String id = studentDao.returnLastId();
		Integer id1 = Integer.parseInt(id);
		id1 += 1;
		id = id1.toString();
		student.setId(id);
		studentValidator = new StudentValidator(student, this);
		studentValidator.validateStudent(messages, student);
		if (messages.size() > 0) {
			throw new ValidatorException("validateForAdd() errors", messages);

		}
		try {
			studentDao.add(student);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}

	public void update(Student student) throws ServiceException {
		studentValidator = new StudentValidator(student, this);
		studentValidator.validateStudent(messages, student);
		if (messages.size() > 0) {
			throw new ValidatorException("validateForUpdate() errors", messages);

		}
		try {
			studentDao.update(student);
		} catch (DaoException e) {
			throw new StudentServiceException(e.getMessage(), e);
		}
	}

	public void remove(Student student) throws StudentServiceException {
		try {
			studentDao.remove(student);
		} catch (StudentDaoException e) {
			e.printStackTrace();
			throw new StudentServiceException(e.getMessage(), e);

		}
	}

	public List<Student> list() throws StudentServiceException {
		try {
			List<Student> students = studentDao.list();
			Collections.sort(students, new Comparator<Student>() {
				public int compare(Student student1, Student student2) {
					return student1.getName().compareTo(student2.getName());
				}
			});
			return students;

		} catch (StudentDaoException e) {
			e.printStackTrace();
			throw new StudentServiceException(e.getMessage(), e);

		}
	}

	public Student findById(String id) throws StudentServiceException {

		try {
			return studentDao.get(id);
		} catch (StudentDaoException e) {
			e.printStackTrace();
			throw new StudentServiceException(e.getMessage(), e);

		}
	}

}
