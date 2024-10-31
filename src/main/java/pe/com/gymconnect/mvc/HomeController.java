package pe.com.gymconnect.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

    @GetMapping({ "/", "indexDatatable.html" })
    public String home(Model index) {

        // return "index";
        return "/fragments/indexDatatable";
    }

    // @GetMapping("/goal")
    // public String goals(Model index) {
    // return "/fragments/indexDatatable :: test_frag";
    // }
}
