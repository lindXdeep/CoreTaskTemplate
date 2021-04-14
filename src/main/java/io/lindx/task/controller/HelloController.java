package io.lindx.task.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.lindx.task.model.User;
import io.lindx.task.service.UserService;
import io.lindx.task.util.Util;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/")
public class HelloController {

  private User admin;

	private final UserService userService;

	@GetMapping(value = "/")
	public String printwelcome(ModelMap model) {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String username = principal instanceof UserDetails ? 
                ((UserDetails)principal).getUsername() : principal.toString();

		List<String> messages = new ArrayList<>();

		messages.add("hello!");
		messages.add("i'm spring mvc application");
		messages.add("5.2.0 version by sep'19 ");

		messages.stream().forEach(s -> Util.getLogger().info(s));

		model.addAttribute("messages", messages);

    admin = userService.getUserByEmail("admin@admin");
    model.addAttribute("setadmin", admin == null ? false : true);
    model.addAttribute("admin", admin);
    
    model.addAttribute("login", principal instanceof UserDetails? true : false);
    
    model.addAttribute("principal", !username.equals("anonymousUser") ? 
                                 userService.getUserByEmail(username) : null);
		return "index";
	}
}
