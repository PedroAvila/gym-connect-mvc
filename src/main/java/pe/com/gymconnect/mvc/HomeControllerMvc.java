package pe.com.gymconnect.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllerMvc {

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
