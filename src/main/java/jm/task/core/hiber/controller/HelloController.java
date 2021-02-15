package jm.task.core.hiber.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HelloController {
    
    @GetMapping(value = "/hello")
    public String printWelcome(ModelMap model){
        
        List<String> messages = new ArrayList<>();

            messages.add("Hello!");
            messages.add("I'm Spring MVC application");
            messages.add("5.2.0 version by sep'19 ");

            for (String s : messages) {
                System.out.println(s);
            }

            model.addAttribute("messages", messages);

        return "index";
    }
}
