package controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/kafka/consumer")
public class ConsumerController {




    @RequestMapping("/config")
    public void config(HttpServletRequest req){

    }

}

