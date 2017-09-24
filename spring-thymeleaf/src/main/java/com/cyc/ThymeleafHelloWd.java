package com.cyc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Thymeleaf 自动化配置 ： ThymeleafProperties
 * Created by cyc_e on 2017/9/24.
 */
@Controller
public class ThymeleafHelloWd {

    @RequestMapping("/thymeleafHelloWd")
    public String helloWd(Model model) {
        model.addAttribute("name", "cyc_eason");
        return "thymeleaf";
    }
}
