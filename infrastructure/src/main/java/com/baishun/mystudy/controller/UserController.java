package com.baishun.mystudy.controller;

import com.baishun.mystudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Author shengy
 * @Date 2024/10/15 16:58
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public String getUser(Long userId) throws InterruptedException {
        String user = userService.getUser(userId);
        return "user: " + user;
    }

    // 更新用户
    @PostMapping("/updateUser")
    public String updateUser(Long userId) throws InterruptedException {
        userService.updateUser(userId);
        return "update success";
    }

}
