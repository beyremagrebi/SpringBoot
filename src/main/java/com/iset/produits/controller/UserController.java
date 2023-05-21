package com.iset.produits.controller;

import com.iset.produits.dao.UserRepository;
import com.iset.produits.entites.User;
import com.iset.produits.service.UserForm;
import com.iset.produits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/reg")
    public String show(@ModelAttribute("user")User user){
        return "register";
    }

    @RequestMapping("/register")
    public String addUser(@ModelAttribute("user")UserForm user, ModelMap modelMap){
        String error="";

        if(user.getPassword().equals(user.getConfirmedPassword())){
            userService.saveUser(user.getUsername(),user.getPassword(),user.getConfirmedPassword());
        }
        else{
            error="Check Field Confirm Password";
            modelMap.addAttribute("error",error);
            return "register";
        }

        return "login";
    }


}
