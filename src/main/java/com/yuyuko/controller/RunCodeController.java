package com.yuyuko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.yuyuko.service.JavaExecuterService;

import java.util.Map;

@RestController
@RequestMapping("/compile")
public class RunCodeController {
    private static final String defaultSource ="public class Main{\n" +
            "    public static void main(String[] args) {\n" +
            "        //在这里输入代码\n" +
            "    }\n" +
            "}";

    @Autowired
    JavaExecuterService executerService;


    @GetMapping
    public String entry(){
        return executerService.execute(defaultSource).replaceAll(System.lineSeparator(), "<br/>");
    }


    @PostMapping
    public String runCode(@RequestBody String code){
        System.out.println(code);
        return executerService.execute(code).replaceAll(System.lineSeparator(), "<br/>");
    }
}
