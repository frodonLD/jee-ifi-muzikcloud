package org.ifi.com.muzikKloud.serviceImpl;

import org.ifi.com.muzikKloud.dao.UserDao;
import org.ifi.com.muzikKloud.entity.User;
import org.ifi.com.muzikKloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	private final int adminFlag = 1; 
	
	@Override
	public void addUser(String login, String password, int type) {
		// TODO Auto-generated method stub
		User u = new User();
		u.setLogin(login);
		u.setPassword(password);
		u.setType(type);
		this.userDao.addUser(u);
	}

	@Override
	public User getUser(String login) {
		// TODO Auto-generated method stub
		return this.userDao.getUser(login);
	}

	@Override
	public boolean isAdmin(User u) {
		// TODO Auto-generated method stub
		return this.adminFlag == u.getType();
	}

}
