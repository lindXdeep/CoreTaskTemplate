package io.lindx.task.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import io.lindx.task.model.User;
import io.lindx.task.service.UserService;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

  private UserService userService;  

  @Autowired
  public SuccessUserHandler(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void onAuthenticationSuccess( HttpServletRequest request, 
                                       HttpServletResponse response,
                                       Authentication authentication
                                      ) throws IOException, ServletException {
    
    Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());


    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
    User user = null;

    if (principal instanceof UserDetails) {
    
      user = userService.getUserByEmail(((UserDetails)principal).getUsername());
    }     


    if(roles.contains("ROLE_ADMIN")){
      response.sendRedirect("/admin");
    } else{
      response.sendRedirect("/user/" + user.getId());
    }
  }
}