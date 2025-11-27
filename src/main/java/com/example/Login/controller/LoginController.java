package com.example.Login.controller;


import com.example.Login.model.UsersData;
import com.example.Login.repo.UserRepo;
import com.example.Login.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller

public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserRepo userRepo;

    @PostMapping("/login")
    public String addInfo(@RequestParam("email") String email,
                          @RequestParam("password") String password, HttpSession session) {
        if (loginService.putInfo(email, password,session)) {

            return "redirect:/main.html";
        } else {
            return "redirect:/RegisFail.html";
        }
    }
    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam long phone,
                           @RequestParam String username,
                           HttpSession session) {
        UsersData user = loginService.put(email, password, phone, username);

        if (user == null) {

            return "redirect:/Register.html";
        }

        session.setAttribute("username", user.getUsername());
        return "redirect:/main.html";
    }
    @GetMapping("/get-username")
    @ResponseBody
    public String getUsername(HttpSession session) {
        Object username = session.getAttribute("username");
        return username != null ? username.toString() : "";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return  "redirect:/main.html";
    }




}
