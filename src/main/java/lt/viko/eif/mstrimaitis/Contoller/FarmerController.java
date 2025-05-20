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
// LINKUS PADARYTI: HATEOS (padaryta)
// OPENAPI Dokumentacija (padaryta reikia uzklausti ar gerai)

// taip pat pasidaryti Json Ignore and OneToMany
/**
 * REST Controller for managing Farmer entities.
 * Supports standard CRUD operations and includes HATEOAS links.
 *
 * Base URL: http://localhost:8080/api/farmers
 */
@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    // Injected repository instance for Farmer entity operations (in-memory).
    private final FarmerRepository repo;

    // Constructor injection of FarmerRepository
    public FarmerController(FarmerRepository repo) {
        this.repo = repo;
    }

    /**
     * Get all farmers with HATEOAS links included.
     * Each farmer includes a self link and a link to the list of all farmers.
     *
     * @return List of EntityModel<Farmer> with links
     */
    @GetMapping
    public List<EntityModel<Farmer>> getAllFarmers() {
        return repo.findAll().stream()
                .map(farmer -> EntityModel.of(farmer,
                        linkTo(methodOn(FarmerController.class).getFarmer(farmer.getId())).withSelfRel(),
                        linkTo(methodOn(FarmerController.class).getAllFarmers()).withRel("all-farmers")))
                .collect(Collectors.toList());
    }

    /**
     * Get a specific farmer by ID with HATEOAS links.
     *
     * @param id The farmer's unique identifier
     * @return ResponseEntity containing EntityModel<Farmer> or 404 if not found
     */
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

    /**
     * Add a new farmer to the repository.
     *
     * @param farmer Farmer object from the request body
     * @return HTTP 200 OK with success message
     */
    @PostMapping
    public ResponseEntity<String> addFarmer(@RequestBody Farmer farmer) {
        repo.save(farmer);
        return ResponseEntity.ok("Farmer added successfully.");
    }

    /**
     * Update an existing farmer by ID.
     *
     * @param id     Farmer ID to update
     * @param farmer Farmer object with updated values
     * @return HTTP 200 OK if successful, or 404 NOT FOUND if the farmer does not exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFarmer(@PathVariable int id, @RequestBody Farmer farmer) {
        Farmer existingFarmer = repo.findById(id);
        if (existingFarmer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmer not found.");
        }

        farmer.setId(id); // Ensure the updated farmer has the correct ID
        repo.save(farmer);
        return ResponseEntity.ok("Farmer updated successfully.");
    }

    /**
     * Delete a farmer by ID.
     *
     * @param id Farmer ID to delete
     * @return HTTP 200 OK if deleted, or 404 NOT FOUND if the farmer does not exist
     */
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