package com.pyxis.nikoniko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {

    public CalendarController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String users(Model model) {
        //model.addAttribute("users", keyword);
    	return "<html><body>hello</body></html>";
    }
}
