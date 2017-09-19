package student.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import student.data.DbUtil;

public class UserDao {
	
	Connection conn = null;
	PreparedStatement pstmt = null;

	public void add(User user) throws DaoException {
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

					throw new DaoException("Duplicate id " + user.getUserId());

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

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public  User get(String userId) throws DaoException {
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

}
