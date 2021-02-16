package jm.task.core.hiber;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jm.task.core.hiber.config.AppConfig;
import jm.task.core.hiber.model.Car;
import jm.task.core.hiber.model.User;
import jm.task.core.hiber.service.CarBuilder;
import jm.task.core.hiber.service.CarService;
import jm.task.core.hiber.service.UserBuilder;
import jm.task.core.hiber.service.UserService;
import jm.task.core.hiber.util.Util;

/**
 * MainApp
 */
public class MainApp {

    // static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    // static UserService userService = context.getBean(UserService.class);
    // static CarService carService = context.getBean(CarService.class);

    // static UserBuilder user = context.getBean(UserBuilder.class);
    // static CarBuilder car = context.getBean(CarBuilder.class);

    public static void main(String[] args) {

    //     Map<String, Car> cars = new LinkedHashMap<>();
    //         cars.put("User1", car.model("modelA").series(101).build());
    //         cars.put("User2", car.model("modelB").series(202).build());
    //         cars.put("User3", car.model("modelC").series(303).build());
    //         cars.put("User4", car.model("modelD").series(404).build());

    //     List<User> users = Arrays.asList(
    //         user.firstName("User1").lastName("Lastname1").email("user1@mail.ru").car(cars.get("User1")).build(),
    //         user.firstName("User2").lastName("Lastname2").email("user2@mail.ru").car(cars.get("User2")).build(),
    //         user.firstName("User3").lastName("Lastname3").email("user3@mail.ru").car(cars.get("User3")).build(),
    //         user.firstName("User4").lastName("Lastname4").email("user4@mail.ru").car(cars.get("User4")).build());

    //     users.stream().forEach(x -> userService.add(x));

    //     userService.listUsers().stream().forEach(
    //         user -> Util.getLogger().info(user.toString()));

    //     carService.listCars().stream().forEach(
    //         car -> Util.getLogger().info(car.toString()));

    //     context.close();
    }
}
