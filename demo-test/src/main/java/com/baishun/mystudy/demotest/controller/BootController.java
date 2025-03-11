package com.baishun.mystudy.demotest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/6/28 16:32
 */
@Controller
//@RequestMapping("/test")
public class BootController {
    @RequestMapping("/index")
    public String index() {
        return "index.html";
    }

    @PostMapping( "/submit-form")
    public String submitForm(@RequestParam("name") String name,
                             @RequestParam("email") String email) {
        // 处理表单提交的逻辑
        System.out.println("Submitted Name: " + name);
        System.out.println("Submitted Email: " + email);

        // 这里可以添加将数据保存到数据库的逻辑

        return "redirect:result.html"; // 返回一个结果页面，可以是另一个 HTML 模板
    }

    /**
     * 方法名可以一样，但是入参不能相同，Mapping匹配的路径不能相同
     *
     * @param name
     * @return
     */
    @PostMapping( "/submit-form-name")
    public String submitForm(@RequestParam("name") String name) {
        // 处理表单提交的逻辑
        System.out.println("Submitted Name: " + name);

        // 这里可以添加将数据保存到数据库的逻辑

        return "redirect:result.html"; // 返回一个结果页面，可以是另一个 HTML 模板
    }

}
