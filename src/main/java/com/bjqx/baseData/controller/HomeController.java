package com.bjqx.baseData.controller;

import com.bjqx.base.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 阿坤
 * @Create 2017--03 10:27
 **/
@Controller
@RequestMapping(value = "home")
public class HomeController extends BaseController {

    @RequestMapping(value = "/home.html")
    public String home(Model model) {
        model.addAttribute("key", "李金坤");
        model.addAttribute("key2", "李金坤2");
        return "waimai/index3";
    }
}
