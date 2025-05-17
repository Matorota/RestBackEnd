package lt.viko.eif.mstrimaitis.Contoller;

import lt.viko.eif.mstrimaitis.Repository.WorkerRepository;
import lt.viko.eif.mstrimaitis.Worker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Worker resources.
 * Base URL: http://localhost:8080/api/workers
 *
 * Supports CRUD operations:
 * - Create a worker
 * - Retrieve all workers or one by ID
 * - Update a worker
 * - Delete a worker
 */
@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    // Repository to access Worker data
    private final WorkerRepository repo;

    /**
     * Constructor-based dependency injection of the WorkerRepository.
     * @param repo the worker repository
     */
    public WorkerController(WorkerRepository repo) {
        this.repo = repo;
    }

    /**
     * GET /api/workers
     * Retrieves all workers from the repository.
     * @return HTTP 200 OK with the list of workers
     */
    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers() {
        List<Worker> workers = repo.findAll();
        System.out.println("Retrieved all workers: " + workers); // For debug/logging
        return ResponseEntity.ok(workers);
    }

    /**
     * GET /api/workers/{id}
     * Retrieves a specific worker by ID.
     * @param id the ID of the worker
     * @return HTTP 200 OK with worker if found, otherwise HTTP 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable int id) {
        Worker worker = repo.findById(id);
        if (worker == null) {
            System.out.println("Worker with ID " + id + " not found."); // For debug/logging
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        System.out.println("Retrieved worker: " + worker); // For debug/logging
        return ResponseEntity.ok(worker);
    }

    /**
     * POST /api/workers
     * Creates a new worker using the data from the request body.
     * @param worker the Worker object to add
     * @return HTTP 201 Created with success message
     */
    @PostMapping
    public ResponseEntity<String> addWorker(@RequestBody Worker worker) {
        repo.save(worker); // Save the worker to the repository
        System.out.println("Added new worker: " + worker); // For debug/logging
        return ResponseEntity.status(HttpStatus.CREATED).body("Worker added successfully.");
    }

    /**
     * PUT /api/workers/{id}
     * Updates an existing worker identified by ID.
     * @param id the ID of the worker to update
     * @param worker the new worker data
     * @return HTTP 200 OK with success message, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorker(@PathVariable int id, @RequestBody Worker worker) {
        Worker existingWorker = repo.findById(id);
        if (existingWorker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worker not found.");
        }
        worker.setId(id); // Ensure correct ID is used
        repo.save(worker); // Save updated worker
        return ResponseEntity.ok("Worker updated successfully.");
    }

    /**
     * DELETE /api/workers/{id}
     * Deletes the worker with the given ID.
     * @param id the ID of the worker to delete
     * @return HTTP 200 OK with success message, or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorker(@PathVariable int id) {
        Worker existingWorker = repo.findById(id);
        if (existingWorker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worker not found.");
        }
        repo.delete(id); // Delete the worker
        return ResponseEntity.ok("Worker deleted successfully.");
    }
}
