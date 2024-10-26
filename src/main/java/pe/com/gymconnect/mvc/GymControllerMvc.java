package pe.com.gymconnect.mvc;

import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.gymconnect.service.GymService;

@Controller
@RequestMapping("gyms")
public class GymControllerMvc {

    private final GymService gymService;

    public GymControllerMvc(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping("/list")
    public CompletableFuture<String> hello(Model theModel, Pageable pageable) {

        // theModel.addAttribute("pageFragment", "fragments/index-gym");

        return gymService.findAllAsync(pageable)
                .thenApplyAsync(gymPage -> {
                    theModel.addAttribute("gyms", gymPage.getContent());
                    return "index-gym :: test_frag";
                });
    }

    // @RequestMapping(value = "/test_ajax_frag")
    // @GetMapping("/test_ajax_frag")
    // public CompletableFuture<String> sendHtmlFragment(Model map, Pageable
    // pageable) {
    // // map.addAttribute("foo", "bar");
    // return gymService.findAllAsync(pageable)
    // .thenApplyAsync(gymPage -> {
    // map.addAttribute("gyms", gymPage.getContent());
    // return "testajaxfragment :: test_frag";
    // });
    // // return "testajaxfragment :: test_frag";
    // }

}
