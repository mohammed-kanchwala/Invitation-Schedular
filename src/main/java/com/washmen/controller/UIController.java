package com.washmen.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UIController {
    @RequestMapping("/")
    public String loadUI() {
        log.info("loading UI");
        return "forward:/index.html";
    }
}
