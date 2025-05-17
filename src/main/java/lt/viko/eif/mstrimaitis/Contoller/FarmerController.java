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

// Base URL: http://localhost:8080/api/farmers
// This controller handles CRUD operations for Farmer entities.
// HATEOAS links are used to provide navigational context in responses.
// Future work: add OpenAPI documentation annotations.

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    // Repository instance for performing data access operations
    private final FarmerRepository repo;

    // Constructor injection of FarmerRepository
    public FarmerController(FarmerRepository repo) {
        this.repo = repo;
    }

    /**
     * GET /api/farmers
     * Retrieves all farmers and wraps each in a HATEOAS EntityModel with self and collection links.
     * @return List of EntityModel<Farmer> containing HATEOAS links.
     */
    @GetMapping
    public List<EntityModel<Farmer>> getAllFarmers() {
        return repo.findAll().stream()
                .map(farmer -> EntityModel.of(farmer,
                        // Self link: points to this specific farmer
                        linkTo(methodOn(FarmerController.class).getFarmer(farmer.getId())).withSelfRel(),
                        // Collection link: points to all farmers
                        linkTo(methodOn(FarmerController.class).getAllFarmers()).withRel("all-farmers")))
                .collect(Collectors.toList());
    }

    /**
     * GET /api/farmers/{id}
     * Retrieves a single farmer by ID and returns it wrapped in an EntityModel.
     * Returns 404 if not found.
     * @param id ID of the farmer
     * @return ResponseEntity with EntityModel or 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Farmer>> getFarmer(@PathVariable int id) {
        Farmer farmer = repo.findById(id);
        if (farmer == null) {
            return ResponseEntity.notFound().build(); // Farmer not found
        }
        EntityModel<Farmer> resource = EntityModel.of(farmer,
                linkTo(methodOn(FarmerController.class).getFarmer(id)).withSelfRel(),
                linkTo(methodOn(FarmerController.class).getAllFarmers()).withRel("all-farmers"));
        return ResponseEntity.ok(resource); // Return farmer with HATEOAS links
    }

    /**
     * POST /api/farmers
     * Adds a new farmer to the system.
     * @param farmer Farmer object from request body
     * @return Success message
     */
    @PostMapping
    public ResponseEntity<String> addFarmer(@RequestBody Farmer farmer) {
        repo.save(farmer); // Save farmer to repository
        return ResponseEntity.ok("Farmer added successfully.");
    }

    /**
     * PUT /api/farmers/{id}
     * Updates an existing farmer with the given ID.
     * @param id ID of the farmer to update
     * @param farmer Updated farmer data from request body
     * @return Success or error message
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFarmer(@PathVariable int id, @RequestBody Farmer farmer) {
        Farmer existingFarmer = repo.findById(id);
        if (existingFarmer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmer not found."); // ID doesn't exist
        }
        farmer.setId(id); // Ensure the updated farmer has the correct ID
        repo.save(farmer); // Save updated farmer
        return ResponseEntity.ok("Farmer updated successfully.");
    }

    /**
     * DELETE /api/farmers/{id}
     * Deletes the farmer with the given ID.
     * @param id ID of the farmer to delete
     * @return Success or error message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFarmer(@PathVariable int id) {
        Farmer existingFarmer = repo.findById(id);
        if (existingFarmer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmer not found."); // Cannot delete non-existing farmer
        }
        repo.delete(id); // Delete the farmer
        return ResponseEntity.ok("Farmer deleted successfully.");
    }
}
