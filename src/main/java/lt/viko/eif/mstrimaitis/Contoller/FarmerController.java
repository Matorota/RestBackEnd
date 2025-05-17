package lt.viko.eif.mstrimaitis.Contoller;

import lt.viko.eif.mstrimaitis.Farmer;
import lt.viko.eif.mstrimaitis.Repository.FarmerRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// http://localhost:8080/api/farmers
// TURI GRAZINTI RESPONS ENTITY (PADARYTA)
// LINKUS PADARYTI: HATEOS
// OPENAPI Dokumentacija


@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerRepository repo;

    public FarmerController(FarmerRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<EntityModel<Farmer>> getAllFarmers() {
        return repo.findAll().stream()
                .map(farmer -> EntityModel.of(farmer,
                        linkTo(methodOn(FarmerController.class).getFarmer(farmer.getId())).withSelfRel(),
                        linkTo(methodOn(FarmerController.class).getAllFarmers()).withRel("all-farmers")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Farmer>> getFarmer(@PathVariable int id) {
        Farmer farmer = repo.findById(id);
        if (farmer == null) {
            return ResponseEntity.notFound().build();
        }
        EntityModel<Farmer> resource = EntityModel.of(farmer,
                linkTo(methodOn(FarmerController.class).getFarmer(id)).withSelfRel(),
                linkTo(methodOn(FarmerController.class).getAllFarmers()).withRel("all-farmers"));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<String> addFarmer(@RequestBody Farmer farmer) {
        repo.save(farmer);
        return ResponseEntity.ok("Farmer added successfully.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFarmer(@PathVariable int id, @RequestBody Farmer farmer) {
        Farmer existingFarmer = repo.findById(id);
        if (existingFarmer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmer not found.");
        }
        farmer.setId(id);
        repo.save(farmer);
        return ResponseEntity.ok("Farmer updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFarmer(@PathVariable int id) {
        Farmer existingFarmer = repo.findById(id);
        if (existingFarmer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmer not found.");
        }
        repo.delete(id);
        return ResponseEntity.ok("Farmer deleted successfully.");
    }
}