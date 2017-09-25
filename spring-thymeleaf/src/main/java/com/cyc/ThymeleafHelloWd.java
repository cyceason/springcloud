package com.cyc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    /**
     * 自定义异常页面
     *
     * @param e
     * @param model
     * @return
     */
    @RequestMapping("/500")
    @ExceptionHandler
    public String internalServerError(Throwable e, Model model) {
        model.addAttribute("errorMsg", e.getMessage());
        return "500";
    }
}
