package com.unicorn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.unicorn.model.User;
import com.unicorn.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
    private UserRepository userRepo;
	
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
	
	@RequestMapping(value={"/**","/login","/logout"})
	public String login(HttpServletRequest request, Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		 
		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		} else if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
			clearAuthenticationAttributes(request);
		} 
		//model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping("/register")
	public String registerUser(Model model){
		model.addAttribute("user",new User());
		return "register";
	}
	
	@RequestMapping(value="/registerNew")
	public String registerUser(Model model, @ModelAttribute User user, @RequestParam String action){
		if (action.equals("Cancel") == false) 
			userRepo.registerUser(user);
		
		return "redirect:/";
	}
	
}
