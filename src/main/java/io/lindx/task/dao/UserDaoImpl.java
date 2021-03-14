package io.lindx.task.dao;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.lindx.task.model.User;

/**
 * Implementation for {@link UserDao}.
 *
 * @author Linder Igor
 * @version 1.0
 * @since 2021-03-13
 */
// @Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void add(User user) {
		getSession().save(user);

	}

	@Override
	public User getUserById(Long id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public void update(User user) {
		Query query = getSession()
				.createQuery("UPDATE User SET name =: name, last_name =: lastname, email =: email WHERE id =: id ");

		query.setParameter("name", user.getFirstName());
		query.setParameter("lastname", user.getLastName());
		query.setParameter("email", user.getEmail());
		query.setParameter("id", user.getId());

		query.executeUpdate();
	}

	@Override
	public void delete(User user) {
		getSession().delete(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		TypedQuery<User> query = getSession().createQuery("from User");
		return query.getResultList();
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
