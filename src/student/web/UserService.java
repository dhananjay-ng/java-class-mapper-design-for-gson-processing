package student.web;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import student.data.Student;
import student.data.StudentDaoException;
import student.data.StudentServiceException;

public class UserService {

	private UserDao userDao = new UserDao();

	public void add(User user) throws UserServiceException {
		try {
			userDao.add(user);
		} catch (DaoException e) {
			throw new UserServiceException(e.getMessage(), e);
		}
	}
	

	public User findById(String userId) throws UserServiceException {

		try {
			User user= userDao.get(userId);
			user.setPassword("*");
			return user;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserServiceException(e.getMessage(), e);

		}

	}
	
	public List<User> list() throws UserServiceException {
		try {
			List<User> users = userDao.list();
			Collections.sort(users, new Comparator<User>() {
				public int compare(User user1, User user2) {
					return user1.getUserName().compareTo(user2.getUserName());
				}
			});
			return users;

		} catch (UserDaoException e) {
			e.printStackTrace();
			throw new UserServiceException(e.getMessage(), e);

		}
	}


	public boolean authenticate(String id, String password) throws UserServiceException {
		try {
			User user = userDao.get(id);

			byte[] bytesOfPassword;
			MessageDigest md;

			try {
				bytesOfPassword = password.getBytes("UTF-8");
				md = MessageDigest.getInstance("MD5");
				byte[] thedigest = md.digest(bytesOfPassword);
				BigInteger bigInt = new BigInteger(1, thedigest);
				String hashtext = bigInt.toString(16);
				while (hashtext.length() < 32) {
					hashtext = "0" + hashtext;
				}
				if (user.getPassword().equals(hashtext)) {
					return true;
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

		} catch (UserNotFoundException e) {
		} catch (DaoException e) {
			throw new UserServiceException("Error in authenticate(id, password)", e);
		}
		return false;
	}

}
