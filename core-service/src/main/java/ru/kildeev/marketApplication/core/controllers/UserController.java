package ru.kildeev.marketApplication.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kildeev.marketApplication.core.entities.User;
import ru.kildeev.marketApplication.core.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
//@CrossOrigin("/*")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/admin/qwerty")
    public String adminPage() {
        return "Hello admin";
    }

    @GetMapping("/user_info")
    public String whoAmI(Principal principal) {
        return principal.getName();
    }
}
