package jm.task.core.hiber.dao;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jm.task.core.hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/** UserDaoImpl */
// @Repository
public class UserDaoImpl implements UserDao {

  // @Autowired
  private SessionFactory sessionFactory;

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public void add(User user) {
    getSession().save(user);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<User> listUsers() {

    TypedQuery<User> query = getSession().createQuery("from User");
    return query.getResultList();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<User> getCarOwner(String model, int series) {

    TypedQuery<User> query =
        getSession().createQuery("from User where car.model =: model and car.series =: series");
    query.setParameter("model", model);
    query.setParameter("series", series);

    return query.getResultList();
  }

  @Override
  public User getUserById(Long id) {

    return sessionFactory.getCurrentSession().get(User.class, id);
  }

  @Override
  public void update(User user) {

    Query query =
        getSession()
            .createQuery(
                "UPDATE User SET name =: name, last_name =: lastname, email =: email WHERE id =: id ");

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
}
