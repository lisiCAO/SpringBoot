package com.fsd.librarymanagement.controller;

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

@Controller
public class UserController {
    private final PasswordEncoder passwordEncoder; // Injected PasswordEncoder for encoding passwords
    private final UserService userService; // Injected UserService for user-related operations

    /**
     * Constructor
     */
    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    /**
     * Method to display the user creation page
     */
    @GetMapping("/showCreateUserPage")
    public String showLoginPage(Model theModel) {
        theModel.addAttribute("user", new User()); // Add a new User object to the model for form binding
        String role = "";
        theModel.addAttribute("role", role); // Add an empty role string to the model for role selection
        return "leaders/create-user";
    }

    /**
     * Method to process the user creation form submission
     */
    @PostMapping("/createUser")
    public String createUser(
            @Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult, @ModelAttribute("role")String role
    ) {
        String result = null;
        if (theBindingResult.hasErrors()) { // Check for validation errors in the form
            result = "leaders/create-user";// Return to the form in case of validation errors
        } else {
            String encodedPassword = passwordEncoder.encode(theUser.getPassword()); // Encode the user's password
            theUser.setPassword(encodedPassword);
            try {
                userService.save(theUser, role); // Save the user with the specified role using UserService
            } catch (CustomException e) {
                theBindingResult.rejectValue("username", "error.user", "An account already exists for this username."); // Handle custom exception if username already exists
                result = "leaders/create-user";// Return to the form in case of exception
            }
            if (result == null) {
                result = "redirect:/leaders/user-confirmation";// Redirect to a confirmation page after successful user creation
            }
        }
        return result;
    }

}
