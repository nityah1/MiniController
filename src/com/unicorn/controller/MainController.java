package com.unicorn.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.unicorn.model.Device;
import com.unicorn.model.User;
import com.unicorn.repository.UserRepository;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/")
public class MainController {

	
	@Autowired
    private UserRepository userRepo;
	
	@RequestMapping(value="/main")
	public String listRegisteredDevices(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        System.out.println(principal.getName());
        model.addAttribute("user", userRepo.getUser(principal.getName()));
		model.addAttribute("userList", userRepo.getAllUsers());
		return "main";
	}
	
	@RequestMapping("/admin")
	public String getUserDetails(Model model,@ModelAttribute(value="user") User user){
		//model.addAttribute("user", userRepo.getUser("ab001"));
		return "admin";
	}

	@RequestMapping(value="/admin/update", method=RequestMethod.POST)
	public String updateUerDetails(@ModelAttribute(value="user") User user,@RequestParam String action){
		if (action.equals("Submit"))
			userRepo.updateUser(user);
		return "redirect:/main";
	}
	
	@RequestMapping("/manageDevices")
	public String manageDevices(Model model,@ModelAttribute User user){
		return "manageDevices";
	}
	
	@RequestMapping(value="/manageDevices/save/{id}/{s}")
	public String saveDevice(HttpServletRequest request, Model model, @ModelAttribute(value="user") User user, @PathVariable(value="id")int listId, @PathVariable(value="s")String s){
		Device d = user.getDevices().get(listId);
		user = userRepo.updateDeviceStatus(user.getUserId(), d.getDeviceId(), s);
		model.addAttribute("user",user);
		return "manageDevices";
	}

	/*
	 * REST API CALLS
	 * NOTE: Need to set header Content-Type to application/json in the rest client
	 */
		
	@RequestMapping(value="/devices/{userId}",method=RequestMethod.GET)
	@ResponseBody
	public List<Device> getRegisteredDevices(@PathVariable String userId){
		System.out.println(userId);
		return userRepo.findDevicesByUserId(userId);
	}
	
	
	@RequestMapping(value="/checkStatus/{userId}/{deviceId}",method=RequestMethod.GET)
	@ResponseBody
	public String checkDeviceStatus(@PathVariable String userId, @PathVariable String deviceId){
		return userRepo.checkDeviceStatus(userId,deviceId);
	}
	
	@RequestMapping(value="/updateStatus/{userId}/{deviceId}/{status}",method=RequestMethod.GET)
	@ResponseBody
	public String updateStatus(@PathVariable String userId, @PathVariable String deviceId, @PathVariable String status){
		try {
			userRepo.updateDeviceStatus(userId, deviceId, status);
		} catch (Exception e){
			return "{Result: e.getMessage()}";
		}
		return "{Result:Success}";
	}
	

	@RequestMapping(value="/registerDevice/{userId}/{deviceId}/{macAddr}",method=RequestMethod.GET)
	@ResponseBody
	public String registerDevice(@PathVariable String userId, @PathVariable String deviceId, @PathVariable String macAddr){
		try {
			userRepo.registerDeviceForUser(userId,deviceId, macAddr);
		} catch (Exception e){
			return "{Result: e.getMessage()}";
		}
		return "{Result:Success}";
	}


}
