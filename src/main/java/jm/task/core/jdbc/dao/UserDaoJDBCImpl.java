package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet result = null;
    private Savepoint savepoint = null;
    
    public UserDaoJDBCImpl() {

        try{
            connection = Util.getConnection();
        }catch(SQLException e){
            Util.getLogger().warning(e.getMessage());
        }
    }

    public void createUsersTable() throws SQLException{

        String sqlCreateUsers = "CREATE TABLE IF NOT EXISTS Users.Users("+
                                "id       SERIAL,"+
                                "name     CHAR(64) NOT NULL,"+
                                "lastName CHAR(64) NOT NULL,"+
                                "age      TINYINT);";
        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("savepoint CREATE");
            statement = connection.createStatement();
            statement.executeUpdate(sqlCreateUsers);
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
            connection.rollback(savepoint);
        }finally{
            connection.setAutoCommit(true);
            statement.close();
        }
    }

    public void dropUsersTable() {

        try {
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS Users;");
            statement.close();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        
        String query = "INSERT INTO Users(name, lastname, age)" + 
                        "VALUES('"+ name +"','"+ lastName + "'," + age +");"; 
        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("savePoint INSERT");
            statement = connection.createStatement();
            statement.executeUpdate(query);

            System.out.println("User с именем – " + name + " добавлен в базу данных");

            connection.commit();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage() + " " + savepoint.getSavepointName() + " -> rollback");
            connection.rollback(savepoint);
        }finally{
            connection.setAutoCommit(true);
            statement.close();
        }
    }

    public void removeUserById(long id) throws SQLException {

        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("savepiont DELETE");
            statement = connection.createStatement();
                    statement.execute("DELETE FROM Users WHERE id=" + id);
            connection.commit();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage() + " " + savepoint.getSavepointName() + " -> rollback");
            connection.rollback(savepoint);
        }finally{
            connection.setAutoCommit(true);
            statement.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> users = new ArrayList<>();

        try {
            statement = connection.createStatement();
            
            result = statement.executeQuery("SELECT * From Users;");

            while(result.next()){

                User user = new User();
                    user.setId(result.getLong("id"));
                    user.setName(result.getString("name"));
                    user.setLastName(result.getString("lastname"));
                    user.setAge(result.getByte("age"));
    
                users.add(user); 
            }
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage());
        }finally{
            result.close();
            statement.close();
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {

        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("TRUNCATE");
            statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE Users;");
            connection.commit();
        } catch (SQLException e) {
            Util.getLogger().warning(e.getMessage() + " " + savepoint.getSavepointName() + " -> rollback");
            connection.rollback();
        }finally{
            statement.close();
        }
    }
}
