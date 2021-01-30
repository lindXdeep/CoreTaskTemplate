package jm.task.core.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jm.task.core.jdbc.config.AppConfig;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;

public class MainApp {

    public static Logger log = Util.getLogger();

    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

            userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
            userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
            userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
            userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();

        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        context.close();
    }
}
