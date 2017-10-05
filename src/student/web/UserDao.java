package student.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import student.data.DbUtil;

public class UserDao {
	
	Connection conn = null;
	PreparedStatement pstmt = null;

	public String add(User user) throws UserDaoException {
		try {
			conn = DbUtil.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			final String queryCheck = "select count(*) from \"user\" where id = ?";
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ps.setString(1, user.getUserId());

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);
				if (count > 0) {
					conn.close();

					throw new UserDaoException("Duplicate id " + user.getUserId());

				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		/*
		 * if (data.containsKey(user.getId())) { throw new
		 * UniqueKeyViolationException("Duplicate id " + user.getId()); }
		 */
		try {
			final String sql = "insert into  \"user\" "//
					+ " values(?, ?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getPassword());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
            return user.getUserId();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public  User get(String userId) throws UserDaoException {
		User user = new User();
		try {
			conn = DbUtil.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			final String queryCheck = "select * from \"user\" where id = ?";
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ps.setString(1, userId);

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				user.setUserId(resultSet.getString(1));
				user.setUserName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));

			}
			return cloneUser(user);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return user;
	}

	public User cloneUser(User user) {
		User cloneUser = new User();
		cloneUser.setUserId(user.getUserId());
		cloneUser.setUserName(user.getUserName());
		cloneUser.setPassword(user.getPassword());

		return cloneUser;
	}
	
	public List<User> list() throws UserDaoException {
		/*if (map.isEmpty()) {
			throw new StudentNotFoundException("Students Does Not Exists");
		}*/
		User user=new User();
		try {
			conn = DbUtil.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			final String queryCheck = "select "//
					+ "count(*) from \"user\"";
			PreparedStatement ps = conn.prepareStatement(queryCheck);
			ResultSet resultSet;
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);
				if (count == 0) {
					conn.close();

					throw new UserNotFoundException("DATABASE IS EMPTY");

				}
				resultSet.close();
				ps.close();
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<User> userList = new ArrayList<>();
		/*
		Iterator<Map.Entry<String, Student>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Student> pair = it.next();
			studentList.add((pair.getValue()));
		}
		return new ArrayList<Student>(map.values());
		*/
		try {
			final String queryCheck = "select "//
					+ "* from \"user\"";
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			while(resultSet.next()) {
				user.setUserId(resultSet.getString(1)); 
				user.setUserName(resultSet.getString(2));
				user.setPassword("*");
				userList.add(cloneUser(user));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return userList;
	}
	
	public void remove(User user) throws UserDaoException {
		try {
			conn = DbUtil.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			final String queryCheck = "select "//
					+ "count(*) from \"user\""//
					+ " where id = ?";
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ps.setString(1, user.getUserId());

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);
				if (count == 0) {
					conn.close();

					throw new UserNotFoundException("Unknown id " + user.getUserId());

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
			final String sql = "delete from  \"user\" "//
					+ " where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(User user) throws UserDaoException {
		try {
			conn = DbUtil.getConnection();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		try {
			final String queryCheck = "select "//
					+ "count(*) from \"user\""//
					+ " where id = ?";
			PreparedStatement ps = conn.prepareStatement(queryCheck);

			ps.setString(1, user.getUserId());

			ResultSet resultSet;

			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);
				if (count == 0) {
					conn.close();

					throw new UserNotFoundException("Unknown id " + user.getUserId());

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
			final String sql = "update  \"user\" "//
					+ "set name=? where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, user.getUserId());
			pstmt.setString(1, user.getUserName());
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}




}
