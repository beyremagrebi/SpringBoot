package com.iset.produits.controller;

import com.iset.produits.dao.UserRepository;
import com.iset.produits.entites.User;
import com.iset.produits.service.UserForm;
import com.iset.produits.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/addUser")
    public String addUser(@ModelAttribute("user")User user){
        userService.saveUser(user.getUsername(),user.getPassword(),user.getPassword());
        return "login";
    }


}
