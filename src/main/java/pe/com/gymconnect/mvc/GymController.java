package pe.com.gymconnect.mvc;

import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.com.gymconnect.dto.CreateGymCommand;
import pe.com.gymconnect.service.GymService;

@Controller
@RequestMapping("gyms")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping("/list")
    public CompletableFuture<String> hello(Model model, Pageable pageable) {

        return gymService.findAllAsync(pageable)
                .thenApplyAsync(gymPage -> {
                    model.addAttribute("gyms", gymPage.getContent());
                    model.addAttribute("gym", new CreateGymCommand(null, null, null));
                    return "index-gym :: test_frag";
                });
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@ModelAttribute("gym") CreateGymCommand theGym) {

        String message = "";
        var dto = gymService.createAsync(theGym);
        if (dto != null)
            message = "Successfully";
        // return "redirect:index-gym :: test_frag";
        return ResponseEntity.ok(message);
    }
}
