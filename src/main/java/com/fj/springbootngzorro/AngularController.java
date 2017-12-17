package com.fj.springbootngzorro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularController {
    @RequestMapping({ "/home", "/login" })
    public String angular() {
        return "forward:/index.html";
    }
}
