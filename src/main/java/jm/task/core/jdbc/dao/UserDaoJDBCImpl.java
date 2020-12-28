package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private String usertable = "";


    public UserDaoJDBCImpl() {

    }

    // Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
    public void createUsersTable() {

        String query =  "CREATE TABLE IF NOT EXISTS Users.Users("+
                            "id       LONG NOT NULL,"+
                            "name     CHAR(64) NOT NULL,"+
                            "lastName CHAR(64) NOT NULL,"+
                            "age      TINYINT);";
        try {
            Connection connection = Util.getConnection();
           
            Statement statement = connection.createStatement();
                      statement.executeUpdate(query);
                      
                      

                      connection.commit();

                      statement.close();

                      connection.close();

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }

    public void dropUsersTable() {

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
