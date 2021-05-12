package groseal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersController {

    @GetMapping
    public String user(Principal principal) {
        return "User" + principal.getName();
    }
}
