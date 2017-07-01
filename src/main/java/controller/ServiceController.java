package controller;

import logging.Log4j2Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@Controller
public class ServiceController {

    private static Logger LOG = LogManager.getLogger(ServiceController.class);

    @Autowired
    Log4j2Config log4j2Config;

    @RequestMapping("/test")
    public String test(@RequestParam(value="text", defaultValue="default") String text) {
        LOG.trace("text " + text);
        return "Hello! Your text: " + text;
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        LOG.trace("name " + name);
        return "greeting";
    }

    @RequestMapping("/reloadLog")
    public String greeting(Model model) throws IOException {
        log4j2Config.reload();
        model.addAttribute("status", "Log4j2.xml was reload succesful!");
        return "reloadlogconfig";
    }

}
