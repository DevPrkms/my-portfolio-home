package com.school.myportfolio.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("main")
    public String test(){
        return "/webapp/WEB-INF/view/home";
    }
}
