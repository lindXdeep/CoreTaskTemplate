package io.lindx.task.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.lindx.task.model.User;

@Repository
public class UserDaoJpaImpl implements UserDao {

	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;

	@Override
	public void add(User user) {
		entityManager.persist(user);
	}

	@Override
	public User getUserById(Long id) {
		TypedQuery<User> query = entityManager.createQuery(

				"select u from User u where u.id =: id ", User.class);

		return query.setParameter("id", id).getResultList().stream().findAny().orElse(null);
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public void delete(User user) {
		entityManager.remove(

				getUserById(user.getId()));
	}

	@Override
	public List<User> listUsers() {
		return entityManager.createQuery(

				"select u from User u", User.class

		).getResultList();
	}

}
