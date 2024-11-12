package pe.com.gymconnect.mvc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.gymconnect.dto.CreateGymCommand;
import pe.com.gymconnect.dto.GymDto;
import pe.com.gymconnect.service.GymService;

@Controller
@RequestMapping("gyms")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping("/listGyms")
    public String listGyms(Model model) {

        // model.addAttribute("loading", true);
        model.addAttribute("gym", new CreateGymCommand(null, null, null));
        // // Durante este tiempo necesito mostrar un mensaje
        gymService.findAllAsync()
                .thenAccept(gymPage -> {
                    model.addAttribute("gyms", gymPage);
                    model.addAttribute("gym", new CreateGymCommand(null, null, null));
                    // model.addAttribute("loading", true);
                });

        return "index-gym";
    }

    @GetMapping("/listData")
    public CompletableFuture<ResponseEntity<Map<String, Object>>> listGyms() {
        return gymService.findAllAsync()
                .thenApplyAsync(gyms -> {
                    Map<String, Object> response = new HashMap<>();

                    // Agregamos el objeto gym inicial (puedes ajustar los valores si es necesario)
                    response.put("gym", new GymDto(null, 0, null, null, null, null));

                    // Convertimos la lista de gimnasios a JSON
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode gymsJson = mapper.valueToTree(gyms);
                    response.put("data", gymsJson);

                    return ResponseEntity.ok(response);
                });
    }

    // @PostMapping("/save")
    // public CompletableFuture<ResponseEntity<String>> save(@ModelAttribute("gym")
    // CreateGymCommand theGym, Model model,
    // Pageable pageable) {

    // return gymService.createAsync(theGym)
    // .thenCompose(savedGym -> gymService.findAllAsync())
    // .thenApply(gymPage -> {
    // model.addAttribute("gyms", gymPage);
    // return ResponseEntity.ok().body("index-gym :: test_frag");
    // })
    // .exceptionally(ex ->
    // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al
    // guardar"));
    // }

    @PostMapping("/save")
    public CompletableFuture<ResponseEntity<String>> save(@RequestBody CreateGymCommand data) {

        return gymService.createAsync(data)
                .thenApply(savedGym -> {
                    return ResponseEntity.ok().body("");
                })
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar"));
    }
}
