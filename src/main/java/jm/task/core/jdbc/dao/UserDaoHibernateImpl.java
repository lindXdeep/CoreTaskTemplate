package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.SessionUtil;

import java.util.List;

//import javax.persistence.Query;
import org.hibernate.query.Query;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS Users("+
                    "id       SERIAL,"+
                    "name     CHAR(64) NOT NULL,"+
                    "lastName CHAR(64) NOT NULL,"+
                    "age      TINYINT);";

        SessionUtil.openTransactionSession();

        Query query = SessionUtil.getSession().createNativeQuery(sql);
            query.executeUpdate();
        
        SessionUtil.closeTransactionSession();
    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS Users;";

        SessionUtil.openTransactionSession();

        Query query = SessionUtil.getSession().createNativeQuery(sql);
            query.executeUpdate();
        
        SessionUtil.closeTransactionSession();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        SessionUtil.openTransactionSession();

        SessionUtil.getSession().save(new User(name, lastName, age));

        SessionUtil.closeTransactionSession();
    }

    @Override
    public void removeUserById(long id) {

        String sql = "DELETE FROM Users WHERE id = :id";

        SessionUtil.openTransactionSession();

        Query query = SessionUtil.getSession().createNativeQuery(sql);
            query.setParameter("id", id);
            query.executeUpdate();
        
        SessionUtil.closeTransactionSession();
    }

    @Override
    public List<User> getAllUsers() {

        String sql = "SELECT * FROM Users";

        SessionUtil.openTransactionSession();

        Query query = SessionUtil.getSession().createNativeQuery(sql)
                .addEntity(User.class);
            
        List<User> users = query.list();
        
        SessionUtil.closeTransactionSession();

        return users;
    }

    @Override
    public void cleanUsersTable() {

    }
}
