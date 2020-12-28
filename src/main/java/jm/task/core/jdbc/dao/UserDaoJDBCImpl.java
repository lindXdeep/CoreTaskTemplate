package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private String sqlCreateUsers = "CREATE TABLE IF NOT EXISTS Users.Users("+
                                    "id       SERIAL,"+
                                    "name     CHAR(64) NOT NULL,"+
                                    "lastName CHAR(64) NOT NULL,"+
                                    "age      TINYINT);";

    private String sqlDropUsers = "DROP TABLE IF EXISTS Users;";
                                   
    public UserDaoJDBCImpl() {
       
    }

    public void createUsersTable() {

        try {
            Connection connection = Util.getConnection();

            Statement statement = connection.createStatement();
                    statement.executeUpdate(sqlCreateUsers);

            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try {
            Connection connection = Util.getConnection();  

            Statement statement = connection.createStatement();
                    statement.executeUpdate(sqlDropUsers);
                      
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
