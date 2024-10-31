package pe.com.gymconnect.mvc;

import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public CompletableFuture<JsonNode> listGyms(Model model) {

        return gymService.findAllAsync()
                .thenApplyAsync(gyms -> {
                    ObjectMapper mapper = new ObjectMapper();
                    return mapper.valueToTree(gyms);
                });
    }

    @GetMapping("/addContent")
    public String addContent() {
        return "index-gym :: test_frag";
    }

    // @GetMapping("/list")
    // public CompletableFuture<ResponseEntity<Map<String, Object>>>
    // listGyms(Pageable pageable) {
    // return gymService.findAllAsync(pageable)
    // .thenApplyAsync(gymPage -> {
    // Map<String, Object> response = new HashMap<>();
    // response.put("data", gymPage.getContent());
    // response.put("recordsTotal", gymPage.getTotalElements());
    // response.put("recordsFiltered", gymPage.getTotalElements());
    // response.put("fragment", "index-gym :: test_frag"); // Fragmento a renderizar
    //
    // // Devuelve un ResponseEntity con el cuerpo
    // return ResponseEntity.ok(response);
    // })
    // .exceptionally(ex -> {
    // Map<String, Object> errorResponse = new HashMap<>();
    // errorResponse.put("message", "Error al obtener la lista de gimnasios");
    // errorResponse.put("error", ex.getMessage());
    // return
    // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    // });
    // }

    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<String>> save(@ModelAttribute("gym") CreateGymCommand theGym, Model model,
            Pageable pageable) {

        return gymService.createAsync(theGym)
                .thenCompose(savedGym -> gymService.findAllAsync())
                .thenApply(gymPage -> {
                    model.addAttribute("gyms", gymPage);
                    return ResponseEntity.ok().body("index-gym :: test_frag");
                })
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar"));
    }

}
