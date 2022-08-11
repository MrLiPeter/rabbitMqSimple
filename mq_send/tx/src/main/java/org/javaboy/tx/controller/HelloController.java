package org.javaboy.tx.controller;

import org.javaboy.tx.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    MsgService msgService;

    @GetMapping("/send")
    public void hello(){
        msgService.send();
    }

}
