package controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/kafka/consumer")
public class ConsumerController {
    ConsumerController(){
        System.out.println("sd;oifjaodsifj");
    }




    @RequestMapping("/config")
    @ResponseBody
    public void config(HttpServletRequest req){
        System.out.println("dfadsfdsaf");
    }

    @RequestMapping("/index")
    public String index(){
       return "index";
    }


}

