package jm.task.core.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import java.util.logging.Logger;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class Main {

    public static Logger log = Util.getLogger();

    public static void main(String[] args) throws SQLException {

        log.info("Load Entry point to Program");

        List<User> users = new ArrayList<User>();
            users.add(new User("John", "Mckey",   (byte)30));
            users.add(new User("Bill", "Johnson", (byte)45));
            users.add(new User("Tom",  "Murrey",  (byte)55));
            users.add(new User("Lue",  "Depp",    (byte)55));
     
        users.stream().forEach(x -> Util.getLogger().info("Create User: " + x.getName() + " " + x.getLastName() + " " + x.getAge() ));

        UserDao u = new UserDaoJDBCImpl();
        u.createUsersTable();
       


        


       
        
    }
}
