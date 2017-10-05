package student.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentService implements Service {

	private StudentDao studentDao = new StudentDao();
	private StudentValidator studentValidator;
	List<String> messages = new ArrayList<>();

	@Override
	public String add(Object obj) throws ServiceException {
		int id = studentDao.returnLastId();
		Integer id1 = id;
		System.out.println(id1);
		id1 += 1;
		String id3 = id1.toString();

		Student student=(Student) obj;
		student.setId(id3);
		studentValidator = new StudentValidator(student, this);
		studentValidator.validateStudent(messages, student);
		System.out.println(messages);
		if (messages.size() > 0) {
			throw new ValidatorException("validateForAdd() errors", messages);

		}
		try {
			return studentDao.add(student);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}

	}

	@Override
	public void update(Object obj) throws ServiceException {
		Student student=(Student) obj;

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

	@Override
	public void remove(Object obj) throws ServiceException {
		Student student=(Student) obj;
		try {
			studentDao.remove(student);
		} catch (StudentDaoException e) {
			e.printStackTrace();
			throw new StudentServiceException(e.getMessage(), e);

		}
	}

	@Override
	public List<Student> list(String filter) throws ServiceException {
		try {
			List<Student> students = studentDao.list(filter);
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

	@Override
	public Student findById(String id) throws ServiceException {

		try {
			return studentDao.get(id);
		} catch (StudentDaoException e) {
			e.printStackTrace();
			throw new StudentServiceException(e.getMessage(), e);

		}
	}

}
