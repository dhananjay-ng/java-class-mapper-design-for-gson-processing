package student.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StudentDao {
	public static Map<String, Student> map = new HashMap<String, Student>();
	Connection conn = null;
	PreparedStatement pstmt = null;

	public Map<String, Student> getMap() {
		return map;
	}

	public void setMap(Map<String, Student> map) {
		StudentDao.map = map;
	}

	public String add(Student student) throws DaoException {
		try {
			conn = DbUtil.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			final String queryCheck = "select count(*) from \"student\" where id = ?";
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ps.setString(1, student.getId());

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);
				if (count > 0) {
					System.out.println(count);
					conn.close();
					throw new UniqueKeyViolationException("Duplicate id " + student.getId());

				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		/*
		 * if (data.containsKey(student.getId())) { throw new
		 * UniqueKeyViolationException("Duplicate id " + student.getId()); }
		 * data.put(student.getId(), cloneStudent(student));
		 */

		try {
			final String sql = "insert into  \"student\" "//
					+ " values(?, ?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getId());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getGender());
			pstmt.setDate(4, new java.sql.Date(student.getBirthDate().getTime()));
			pstmt.setDate(5, new java.sql.Date(student.getJoinDate().getTime()));
			pstmt.setInt(6, student.getStandard());
			pstmt.setString(7, student.getDivision());
			pstmt.setInt(8, student.getRollNumber());
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();
			return student.getId();


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Student student) throws StudentDaoException {
		try {
			conn = DbUtil.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			final String queryCheck = "select "//
					+ "count(*) from \"student\""//
					+ " where id = ?";
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ps.setString(1, student.getId());

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);
				if (count == 0) {
					conn.close();

					throw new StudentNotFoundException("Unknown id " + student.getId());

				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		/*if (!map.containsKey(student.getId())) {
			throw new StudentNotFoundException("Student Does Not Exists");
		}
		map.remove(student.getId());
		*/
		try {
			final String sql = "delete from  \"student\" "//
					+ " where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getId());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Student student) throws DaoException {
		try {
			conn = DbUtil.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			final String queryCheck = "select "//
					+ "count(*) from \"student\""//
					+ " where id = ?";
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ps.setString(1, student.getId());

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);
				if (count == 0) {
					conn.close();

					throw new StudentNotFoundException("Unknown id " + student.getId());

				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		/*
		 * if (data.containsKey(student.getId()) == false) { throw new
		 * NotFoundException("Unknown id " + student.getId()); }
		 * data.put(student.getId(), cloneStudent(student));
		 */
		try {
			final String sql = "update  \"student\" "//
					+ "set name=?,gender=?,birthDate=?,joinDate=?,standard=?,division=?,rollNumber=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(8, student.getId());
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getGender());
			pstmt.setDate(3, new java.sql.Date(student.getBirthDate().getTime()));
			pstmt.setDate(4, new java.sql.Date(student.getJoinDate().getTime()));
			pstmt.setInt(5, student.getStandard());
			pstmt.setString(6, student.getDivision());
			pstmt.setInt(7, student.getRollNumber());

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public Student get(String id) throws StudentDaoException {
		Student student=new Student();
		try {
			conn = DbUtil.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			final String queryCheck = "select "//
					+ "count(*) from \"student\""//
					+ " where id = ?";
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ps.setString(1, id);

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);
				if (count == 0) {
					conn.close();

					throw new StudentNotFoundException("Unknown id " + id);

				}
				resultSet.close();
				ps.close();
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		/*
		if (data.containsKey(id) == false) {
			throw new NotFoundException("Unknown id " + id);
		}
		return cloneStudent(data.get(id));
		*/
		try {
			final String queryCheck = "select "//
					+ "* from \"student\""//
					+ " where id = ?";
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ps.setString(1, id);

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			while(resultSet.next()) {
				student.setId(resultSet.getString(1)); 
				student.setName(resultSet.getString(2));
				student.setGender(resultSet.getString(3));
				student.setBirthDate(resultSet.getDate(4));
				student.setJoinDate(resultSet.getDate(5));
				student.setStandard(resultSet.getInt(6));
				student.setDivision(resultSet.getString(7));
				student.setRollNumber(resultSet.getInt(8));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return student;
	}

	public List<Student> list(String filter) throws StudentDaoException {
		/*if (map.isEmpty()) {
			throw new StudentNotFoundException("Students Does Not Exists");
		}*/
		
		
		Student student=new Student();
		try {
			conn = DbUtil.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			final String queryCheck = "select "//
					+ "count(*) from \"student\"";
			PreparedStatement ps = conn.prepareStatement(queryCheck);
			ResultSet resultSet;
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);
				if (count == 0) {
					conn.close();

					throw new StudentNotFoundException("DATABASE IS EMPTY");

				}
				resultSet.close();
				ps.close();
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Student> studentList = new ArrayList<>();
		/*
		Iterator<Map.Entry<String, Student>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Student> pair = it.next();
			studentList.add((pair.getValue()));
		}
		return new ArrayList<Student>(map.values());
		*/
		try {
			String queryCheck;
			if(filter==null){
			 queryCheck = "select "//
					+ "* from \"student\"";
			}
			else{
				 queryCheck = "select "//
							+ "* from \"student\""+filter;
					
			}
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			while(resultSet.next()) {
				student.setId(resultSet.getString(1)); 
				student.setName(resultSet.getString(2));
				student.setGender(resultSet.getString(3));
				student.setBirthDate(resultSet.getDate(4));
				student.setJoinDate(resultSet.getDate(5));
				student.setStandard(resultSet.getInt(6));
				student.setDivision(resultSet.getString(7));
				student.setRollNumber(resultSet.getInt(8));
				studentList.add(cloneStudent(student));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return studentList;
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

	public int returnLastId() {
		String id=null;
		/*if (map.isEmpty()) {
		throw new StudentNotFoundException("Students Does Not Exists");
	}*/
	try {
		conn = DbUtil.getConnection();
	} catch (SQLException e2) {
		e2.printStackTrace();
	}

	try {
		final String queryCheck = "select "//
				+ "count(*) from \"student\"";
		PreparedStatement ps = conn.prepareStatement(queryCheck);
		ResultSet resultSet;
		resultSet = ps.executeQuery();
		if (resultSet.next()) {
			final int count = resultSet.getInt(1);
			if (count == 0) {
				conn.close();

            return 0;
			}
			else if(count>0){
				System.out.println("Count"+count);
				return count;
				
				
			}
			resultSet.close();
			ps.close();
			
		}
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
		
	try {
		final String queryCheck = "select "//
				+ "id from \"student\" order by id desc";
		PreparedStatement ps = conn.prepareStatement(queryCheck);

		ResultSet resultSet;

		resultSet = ps.executeQuery();
		resultSet.next();
		return 0;
		
	} catch (SQLException e1) {
		e1.printStackTrace();
	}

		
		return 0;
		
	}
}
