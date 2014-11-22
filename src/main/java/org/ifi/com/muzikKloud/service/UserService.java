package org.ifi.com.muzikKloud.service;

import org.ifi.com.muzikKloud.entity.User;

public interface UserService {
	public void addUser(String login, String password, String type);
	public User getUser(String login);
	public boolean isAdmin(User u);
}
