package service;

import Model.User;
import dao.UserDAO;


public class UserService {
	public static Integer saveUser(User user) { 
		try {
			// If the user does not exist, it saves the user data using the UserDAO.saveUser() method.
			// If the user already exists, it returns 0 to indicate that saving was skipped.
			if (UserDAO.isExists(user.getEmail())) { //It checks if a user with the given email already exists in the database using the UserDAO.isExists() method.
				return 0;
			}else {
				return UserDAO.saveUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
