package io.lindx.task.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.lindx.task.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
public class UserController {

	private final UserService userService;

	@GetMapping("/user/{id}")
	public String user(@PathVariable("id") Long id, ModelMap model) {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String username = principal instanceof UserDetails ? 
                ((UserDetails)principal).getUsername() : principal.toString();


		model.addAttribute("user", userService.getUserById(id));

    model.addAttribute("login", principal instanceof UserDetails? true : false);
    
    model.addAttribute("principal", !username.equals("anonymousUser") ? 
                                 userService.getUserByEmail(username) : null);
		return "pages/user";
	}
}