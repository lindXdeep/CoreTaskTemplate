package io.lindx.task.controller;

import java.util.Collections;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.lindx.task.model.Role;
import io.lindx.task.model.User;
import io.lindx.task.service.RoleService;
import io.lindx.task.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
public class AdminController {

  private final UserService userService;
  private final RoleService roleService;

  @GetMapping("/admin")
  public String admin(final ModelMap model) {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    model.addAttribute("users", userService.listUsers());
    model.addAttribute("allroles", roleService.getAllRoles());

    model.addAttribute("user", new User());

    model.addAttribute("login", principal instanceof UserDetails ? true : false);
    String username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
    model.addAttribute("principal", !username.equals("anonymousUser") ? userService.getUserByEmail(username) : null);

    return "pages/admin";
  }

  @GetMapping("/admin/users/create")
  public String newUser(final ModelMap model) {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    model.addAttribute("user", new User());

    model.addAttribute("login", principal instanceof UserDetails ? true : false);
    String username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
    model.addAttribute("principal", !username.equals("anonymousUser") ? userService.getUserByEmail(username) : null);

    return "/pages/add";
  }

  @PostMapping("/admin/users/create")
  public String newUser(final @ModelAttribute User user, 
                        final RedirectAttributes redirectAttributes,
                        final @RequestParam(value = "select_role", required = false) String selectedRole) {

    Role role = new Role();
    role.setTitle(selectedRole);

    user.setRoles(Collections.singleton(role));

    userService.add(user);

    redirectAttributes.addAttribute("id", user.getId()).addFlashAttribute("msg", "User: " + user.getId() + " Created!");

    return "redirect:/user/{id}";
  }

  @GetMapping("/admin/user/{id}/edit")
  public String edit(final @PathVariable("id") Long id, final ModelMap model) {

    User user = userService.getUserById(id);

    model.addAttribute("user", user);

    return "pages/edit";
  }

  @PatchMapping("/admin/user/{id}")
  public String update(final @ModelAttribute User user, final @PathVariable("id") Long id,
      final RedirectAttributes redirectAttributes) {

    userService.update(user);

    redirectAttributes.addAttribute("id", user.getId()).addFlashAttribute("msg", "User: " + user.getId() + " Updated!");

    return "redirect:/user/{id}";
  }

  @DeleteMapping("/admin/user/{id}")
  public String delete(final @PathVariable("id") Long id, final RedirectAttributes redirectAttributes) {

    User user = userService.getUserById(id);
    userService.delete(userService.getUserById(id));

    redirectAttributes.addAttribute("id", user.getId()).addFlashAttribute("msg", "User: " + user.getId() + " Delete!");

    return "redirect:/admin/users";
  }

  @GetMapping("/admin/users")
  public String users(final ModelMap model) {

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();

    model.addAttribute("users", userService.listUsers());

    model.addAttribute("login", principal instanceof UserDetails ? true : false);

    model.addAttribute("principal", !username.equals("anonymousUser") ? userService.getUserByEmail(username) : null);

    return "pages/admin";
  }

}
