package com.fsd.librarymanagement.controller;

import com.fsd.librarymanagement.entity.Role;
import com.fsd.librarymanagement.entity.User;
import com.fsd.librarymanagement.exception.CustomException;
import com.fsd.librarymanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/showCreateUserPage")
    public String showLoginPage(Model theModel) {
        theModel.addAttribute("user", new User());
        String role ="";
        theModel.addAttribute("role", role);
        return "leaders/create-user";
    }

//    @PostMapping("/createUser")
//    public String createUser(@RequestParam String username,
//                             @RequestParam String password,
//                             @RequestParam String role) {
//        String encodedPassword = passwordEncoder.encode(password);
//        User newUser = new User();
//        newUser.setUsername(username);
//        newUser.setPassword(encodedPassword);
//        userService.save(newUser, role);
//
//        return "redirect:/leaders"; // Redirect after user creation
//    }

    @PostMapping("/createUser")
    public String createUser(
            @Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult, @ModelAttribute("role")String role
    ){
        if(theBindingResult.hasErrors()){
            return "leaders/create-user";
        }

            String encodedPassword = passwordEncoder.encode(theUser.getPassword());
            theUser.setPassword(encodedPassword);
        try {
            userService.save(theUser, role);
        } catch (CustomException e) {
            theBindingResult.rejectValue("username", "error.user", "An account already exists for this username.");
            return "leaders/create-user";
        }
            return "leaders/user-confirmation";
    }


}
