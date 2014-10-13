package org.ifi.com.muzikKloud.dao;

import org.ifi.com.muzikKloud.entity.User;

public interface UserDao {
	public void addUser(User u);
	public void updateUser(String login, String newPass);
	public User getUser(String login);
}
