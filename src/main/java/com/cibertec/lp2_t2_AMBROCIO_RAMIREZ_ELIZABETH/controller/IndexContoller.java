package com.cibertec.lp2_t2_AMBROCIO_RAMIREZ_ELIZABETH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexContoller {


@GetMapping("/")
public String Index( ) {
    return "index";

}


    
}
