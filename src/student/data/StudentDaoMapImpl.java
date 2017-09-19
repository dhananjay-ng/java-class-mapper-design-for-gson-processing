package student.data;



	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Map;
	import java.util.Map.Entry;
	public class StudentDaoMapImpl {

		public static Map<String, Student> map = new HashMap<String, Student>();

		public Map<String, Student> getMap() {
			return map;
		}

		public void setMap(Map<String, Student> map) {
			StudentDao.map = map;
		}

		public void add(Student student) throws StudentDaoException {
			if (map.containsKey(student.getId())) {
				throw new DuplicateStudentException("Student Already Exists");
			}
			map.put(student.getId(), cloneStudent(student));
		}

		public void remove(Student student) throws StudentDaoException {
			if (!map.containsKey(student.getId())) {
				throw new StudentNotFoundException("Student Does Not Exists");
			}
			map.remove(student.getId());
		}

		public void update(Student student) throws StudentDaoException {
			if (!map.containsKey(student.getId())) {
				throw new StudentNotFoundException("Student Does Not Exists");
			}
			map.put(student.getId(), cloneStudent(student));

		}

		public Student get(String id) throws StudentDaoException {
			if (!map.containsKey(id)) {
				throw new StudentNotFoundException("Student Does Not Exists");
			}
			return cloneStudent(map.get(id));

		}

		public List<Student> list() throws StudentDaoException {
			if (map.isEmpty()) {
				throw new StudentNotFoundException("Students Does Not Exists");
			}
			List<Student> studentList = new ArrayList<>();
			Iterator<Map.Entry<String, Student>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Student> pair = it.next();
				studentList.add(cloneStudent(pair.getValue()));
			}
			return new ArrayList<Student>(map.values());
		}

		public Student cloneStudent(Student student) {
			Student cloneStudent = new Student();
			cloneStudent.setId(student.getId());
			cloneStudent.setBirthDate(student.getBirthDate());
			cloneStudent.setDivision(student.getDivision());
			cloneStudent.setGender(student.getGender());
			cloneStudent.setJoinDate(student.getJoinDate());
			cloneStudent.setName(student.getName());
			cloneStudent.setRollNumber(student.getRollNumber());
			cloneStudent.setStandard(student.getRollNumber());

			return cloneStudent;
		}

	}

