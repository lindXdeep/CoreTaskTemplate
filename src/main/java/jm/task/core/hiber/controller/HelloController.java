package jm.task.core.hiber.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jm.task.core.hiber.model.User;
import jm.task.core.hiber.service.UserBuilder;
import jm.task.core.hiber.service.UserService;
import jm.task.core.hiber.util.Util;

@Controller
@RequestMapping("/")
public class HelloController {

    private final UserService userService;
    private final UserBuilder user;

    @Autowired
    public HelloController(UserService userService, UserBuilder user) {

        this.userService = userService;
        this.user = user;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {

        List<String> messages = new ArrayList<>();

        messages.add("Hello!");
        messages.add("I'm Spring MVC application");
        messages.add("5.2.0 version by sep'19 ");

        messages.stream().forEach(s -> Util.getLogger().info(s));

        model.addAttribute("messages", messages);

        return "index";
    }

    @GetMapping(value = "/create")
    public String create(ModelMap model) {

        List<User> users = Arrays.asList(
                user.firstName("User1").lastName("Lastname1").email("user1@mail.ru").build(),
                user.firstName("User2").lastName("Lastname2").email("user2@mail.ru").build(),
                user.firstName("User3").lastName("Lastname3").email("user3@mail.ru").build(),
                user.firstName("User4").lastName("Lastname4").email("user4@mail.ru").build(),
                user.firstName("User5").lastName("Lastname5").email("user5@mail.ru").build());

        users.stream().forEach(x -> userService.add(x));

        return "redirect:/users";
    }

    @GetMapping("/index")
    public String cars(ModelMap model) {

        model.addAttribute("users", userService.listUsers());

        return "index";
    }
}
