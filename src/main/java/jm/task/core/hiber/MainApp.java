package jm.task.core.hiber;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jm.task.core.hiber.config.AppConfig;
import jm.task.core.hiber.model.User;
import jm.task.core.hiber.service.UserService;
import jm.task.core.hiber.util.Util;
/**
 * MainApp
 */
public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
                userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
                userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
                userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
                userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        userService.listUsers().stream().forEach(

                    user -> Util.getLogger().info(user.toString())
                );

        ((ConfigurableApplicationContext)context).close();
    }
}
