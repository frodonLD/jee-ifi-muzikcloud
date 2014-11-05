package org.ifi.com.muzikKloud.dao;

import org.ifi.com.muzikKloud.entity.User;
import org.springframework.dao.DataAccessException;

public interface UserDao {
	public void addUser(User u) throws DataAccessException;
	public void updateUser(String login, String newPass) throws DataAccessException;
	public User getUser(String login) throws DataAccessException;
}
