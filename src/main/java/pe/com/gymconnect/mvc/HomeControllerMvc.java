package pe.com.gymconnect.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeControllerMvc {

    @GetMapping({ "/", "index.html" })
    public String home(Model index) {
        return "index";
    }

}
