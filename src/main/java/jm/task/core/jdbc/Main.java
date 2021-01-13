package jm.task.core.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
public class Main {

    public static Logger log = Util.getLogger();

    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();

        ArrayList<User> users = new ArrayList<>();
            users.add(new User("John", "Mckey",   (byte)30));
            users.add(new User("Bill", "Johnson", (byte)45));
            users.add(new User("Tom",  "Murrey",  (byte)55));
            users.add(new User("Lue",  "Depp",    (byte)55));

            userService.createUsersTable();

            users.stream().forEach(x -> userService.saveUser(x.getName(), x.getLastName(), x.getAge()));

            System.out.println(userService.getAllUsers()); 

            userService.cleanUsersTable();

            userService.dropUsersTable();
    }
}
