package group.bridge.web.controller;


import group.bridge.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DemoController extends BaseController{
    @Autowired
    PersonService personService;
    @RequestMapping(value = {"/",""})
    public String demo() {
        return "common/index";
    }


}
