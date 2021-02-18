package jm.task.core.hiber.controller;

import java.net.http.HttpClient.Redirect;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jm.task.core.hiber.model.Car;
import jm.task.core.hiber.model.User;
import jm.task.core.hiber.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(ModelMap model) {

        List<User> users = new ArrayList<>();

        for (User user : users) {
            System.out.println(user.toString());
        }

        model.addAttribute("users", userService.listUsers());

        return "index";
    }

    @GetMapping("user/{id}")
    public String user(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("user", userService.getUserById(id));

        System.out.println(userService.getUserById(id).toString());

        return "user";
    }

    @GetMapping("/users/new")
    public String create( 
                            ModelMap model
                            
                            ){
         model.addAttribute("user", new User());                          
         model.addAttribute("car", new Car());                          

        return "new";
    }

    @PostMapping("/users/new")
    public String newUser(@ModelAttribute User user,
                            @ModelAttribute Car car,
                            RedirectAttributes redirectAttributes){

        user.setCar(car);                         
        userService.add(user);   

    
        redirectAttributes.addAttribute("id", user.getId()).addFlashAttribute("msg", "User Created!");
        
        return "redirect:/user/{id}";                                                                                                                                                                                                                                                                 
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap model) {

        model.addAttribute("user", userService.getUserById(id));

        System.out.println(userService.getUserById(id).toString());

        return "edit";
    }
}
