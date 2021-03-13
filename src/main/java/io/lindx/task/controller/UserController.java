package io.lindx.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.lindx.task.model.User;
import io.lindx.task.service.UserService;

@Controller
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping("/users")
	public String users(ModelMap model) {

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
	public String create(ModelMap model) {

		model.addAttribute("user", new User());

		return "new";
	}

	@PostMapping("/users/new")
	public String newUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {

		userService.add(user);

		redirectAttributes.addAttribute("id", user.getId()).addFlashAttribute("msg",
				"User: " + user.getId() + " Created!");

		return "redirect:/user/{id}";
	}

	@GetMapping("/user/{id}/edit")
	public String edit(@PathVariable("id") Long id, ModelMap model) {

		User user = userService.getUserById(id);

		model.addAttribute("user", user);

		return "edit";
	}

	@PatchMapping("/user/{id}")
	public String update(@ModelAttribute User user, @PathVariable("id") Long id,

			RedirectAttributes redirectAttributes) {

		userService.update(user);

		redirectAttributes.addAttribute("id", user.getId()).addFlashAttribute("msg",
				"User: " + user.getId() + " Updated!");

		return "redirect:/user/{id}";
	}

	@DeleteMapping("/user/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

		User user = userService.getUserById(id);
		userService.delete(userService.getUserById(id));

		redirectAttributes.addAttribute("id", user.getId()).addFlashAttribute("msg",
				"User: " + user.getId() + " Delete!");

		return "redirect:/users";
	}

}