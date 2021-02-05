package com.example.jwtsecurity.api;

import com.example.jwtsecurity.dto.UserDto;
import com.example.jwtsecurity.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private UserService userService;

    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public void login(Object object) {
        System.out.println("login =========" + object.getClass().getSimpleName());
    }

    @GetMapping("/logout")
    public void logout() {
        System.out.println("logout ...");
    }

    @PostMapping("/register")
    public void register(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }
}
