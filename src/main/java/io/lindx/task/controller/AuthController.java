package io.lindx.task.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.lindx.task.model.Role;
import io.lindx.task.model.User;
import io.lindx.task.service.UserService;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String sign_in(@RequestParam(name = "error", required = false) final Boolean error,
                        final Model model) {

    if(Boolean.TRUE.equals(error)){
      model.addAttribute("error", true);
    }   

		return "/auth/sign_in.html";
	}

  @GetMapping("/logout")
  public String logout(HttpServletRequest request){

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
    if(auth != null){
      request.getSession().invalidate();
    }
    
    return "redirect:/login";
  }

  /**
   * service method
   * @return
   */
  @GetMapping("/setadmin")
  public String setadmin(
        @RequestParam(name = "n", defaultValue = "admin",       required = false) final String name,
        @RequestParam(name = "l", defaultValue = "admin",       required = false) final String last,
        @RequestParam(name = "m", defaultValue = "admin@admin", required = false) final String mail,
        @RequestParam(name = "p", defaultValue = "admin",       required = false) final String pass,
        final ModelMap model
  ){
      
    if(userService.getUserByEmail(mail) != null) {

      return "redirect:/";
    }

    Role role = new Role();
     role.setTitle("ROLE_ADMIN");

     User admin = new User();
      admin.setFirstName(name);
      admin.setLastName(last);
      admin.setEmail(mail);
      admin.setPassword(pass);
      admin.setRoles(Collections.singleton(role));

      userService.add(admin);
      
    return "redirect:/login";
  }
}
