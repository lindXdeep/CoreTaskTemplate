package jm.task.core.hiber.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jm.task.core.hiber.model.Car;
import jm.task.core.hiber.model.User;
import jm.task.core.hiber.service.CarService;
import jm.task.core.hiber.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    private final CarService carService;

    @Autowired
    public UserController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping("/users")
    public String users(ModelMap model) {

        List<User> users = new ArrayList<>();

        for (User user : users) {
            System.out.println(user.toString());
        }

        model.addAttribute("users", userService.listUsers());

        return "users";
    }

    @GetMapping("user/{id}")
    public String user(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("user", userService.getUserById(id));

        System.out.println(userService.getUserById(id).toString());

        return "user";
    }

    @GetMapping("user/add")
    public String create(ModelMap modelMap) {

        modelMap.addAttribute("user", new User());

        return "create_user";
    }

    @PostMapping("user/add")
    public String method(@ModelAttribute("user") User user, @RequestParam("car_id") Long id) {

        Car car = carService.getCarbyId(id);

        user.setCar(car);
        car.setOwner(user);

        userService.add(user);
        carService.add(car);

        return "users";
    }

}
