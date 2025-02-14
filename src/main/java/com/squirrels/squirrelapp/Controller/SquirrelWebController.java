package com.squirrels.squirrelapp.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;

@Controller
public class SquirrelWebController {

    @GetMapping("/")
    public String index(){
        return "redirect:/index.html";
    }
}
