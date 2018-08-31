package cn.java.receive.controller;

import cn.java.receive.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/c")
public class controller {

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/g")
    @ResponseBody
    public String aa() {

        return tokenService.getToken();
    }

    @RequestMapping("/w")
    @ResponseBody
    public Boolean updateToken(String t) {



        return tokenService.wriToken(t);
    }
}
