package org.ifi.com.muzikKloud.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ifi.com.muzikKloud.dao.UserDao;
import org.ifi.com.muzikKloud.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void addUser(User u) throws DataAccessException{
		// TODO Auto-generated method stub
		this.entityManager.persist(u);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) 
	public void updateUser(String login, String newPass) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "update table user set password = ? where login = ? ";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, newPass);
		query.setParameter(2, login);
		query.executeUpdate();
	}

	@Override
	public User getUser(String login) throws DataAccessException{
		// TODO Auto-generated method stub
		String req = "select u from user where u.id = ?";
		Query query = this.entityManager.createQuery(req);
		query.setParameter(1, login);
		return (User) query.getSingleResult();
	}

}
