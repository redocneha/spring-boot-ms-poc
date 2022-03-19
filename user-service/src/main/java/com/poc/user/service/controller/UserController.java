package com.poc.user.service.controller;

import com.poc.user.service.entity.User;
import com.poc.user.service.service.UserService;
import com.poc.user.service.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("Inside save user of controller");
        return userService.saveUser(user);
    }


    @GetMapping("/{id}")
    public ResponseTemplateVO getUserById(@PathVariable("id") Long id){
        log.info("Inside get user of controller");
        return userService.findById(id);
    }
}
