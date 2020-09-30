package com.pearadmin.generator.controller;

import com.pearadmin.common.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 代码生成视图控制器
 *
 * @author Bamboo
 * @since 2020-03-19
 */
@Controller
public class ViewController extends BaseController {

    private static final String PREFIX = "generator/";

    @GetMapping("/generator/main")
    public ModelAndView index() {
        return JumpPage(PREFIX + "gen");
    }

    @GetMapping("/generator/add")
    public ModelAndView add() {
        return JumpPage(PREFIX + "gen_add");
    }

}
