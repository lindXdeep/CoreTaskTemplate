package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection;
    
    public UserDaoJDBCImpl() {

        try{
            connection = Util.getConnection();
        }catch(SQLException e){
            Util.getLogger().warning(e.getMessage());
        }
    }

    public void createUsersTable() {

        String sqlCreateUsers = "CREATE TABLE IF NOT EXISTS Users.Users("+
                                "id       SERIAL,"+
                                "name     CHAR(64) NOT NULL,"+
                                "lastName CHAR(64) NOT NULL,"+
                                "age      TINYINT);";
        try {
            Statement statement = connection.createStatement();
                    statement.executeUpdate(sqlCreateUsers);

            connection.commit();

            statement.close();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
        }
    }

    public void dropUsersTable() {

        try {
            Statement statement = connection.createStatement();
                    statement.executeUpdate("DROP TABLE IF EXISTS Users;");
                      
            connection.commit();

            statement.close();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        
        String query = "INSERT INTO Users(name, lastname, age)" + 
                        "VALUES('"+ name +"','"+ lastName + "'," + age +");"; 
        
        try {
            Statement statement = connection.createStatement();
                    statement.executeUpdate(query);

            connection.commit();

            System.out.println("User с именем – " + name + " добавлен в базу данных");

            statement.close();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
                    statement.execute("DELETE FROM Users WHERE id=" + id);

            connection.commit();

            statement.close();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            
            ResultSet res = statement.executeQuery("SELECT * From Users;");

            connection.commit();

            while(res.next()){

                User user = new User();
                    user.setId(res.getLong("id"));
                    user.setName(res.getString("name"));
                    user.setLastName(res.getString("lastname"));
                    user.setAge(res.getByte("age"));
    
                users.add(user); 
            }

            res.close();
            statement.close();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
                    statement.executeUpdate("TRUNCATE TABLE Users;");

            connection.commit();

            statement.close();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
        }
    }
}
