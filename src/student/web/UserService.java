package student.web;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import student.data.Service;
import student.data.ServiceException;

public class UserService implements Service {

	private UserDao userDao = new UserDao();


	@Override
	public void add(Object obj) throws ServiceException {
			try {
				User user=(User) obj;
			userDao.add(user);
		} catch (UserDaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
    @Override
	public User findById(String userId) throws ServiceException {

		try {
			User user= userDao.get(userId);
			user.setPassword("*");
			return user;
		} catch (UserDaoException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);

		}

	}
	
    @Override
	public List<User> list(String filter) throws ServiceException {
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
			throw new ServiceException(e.getMessage(), e);

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
		} catch (UserDaoException e) {
			throw new UserServiceException("Error in authenticate(id, password)", e);
		}
		return false;
	}




	@Override
	public void update(Object obj) throws ServiceException {
		User user=(User) obj;
		try {
			userDao.update(user);
		} catch (UserDaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}		
	}


	@Override
	public void remove(Object obj) throws ServiceException {
		User user=(User) obj;
		try {
			userDao.remove(user);
		} catch (UserDaoException e) {
			throw new ServiceException(e.getMessage(), e);
		}			
	}

}
