package student.web;

import java.util.HashMap;
import java.util.Map;

import student.web.User;

public class UserDaoMapImpl {
	public static Map<String, User> map = new HashMap<String, User>();

	public Map<String, User> getMap() {
		return map;
	}

	public void setMap(Map<String, User> map) {
		UserDaoMapImpl.map = map;
	}

	public void add(User user) throws UserDaoException {
		if (map.containsKey(user.getUserId())) {
			throw new DuplicateUserException("User Already Exists");
		}
		map.put(user.getUserId(), cloneUser(user));
	}

	public User get(String userId) throws UserDaoException {
		if (!map.containsKey(userId)) {
			throw new UserNotFoundException("No User Found");
		}
		return cloneUser(map.get(userId));

	}

	public User cloneUser(User user) {
		User cloneUser = new User();
		cloneUser.setUserId(user.getUserId());
		cloneUser.setUserName(user.getUserName());
		cloneUser.setPassword(user.getPassword());

		return cloneUser;
	}

}
