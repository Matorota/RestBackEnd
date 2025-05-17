package lt.viko.eif.mstrimaitis.Contoller;

import lt.viko.eif.mstrimaitis.Repository.WorkerRepository;
import lt.viko.eif.mstrimaitis.Worker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller for managing Worker resources
@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkerRepository repo;

    public WorkerController(WorkerRepository repo) {
        this.repo = repo;
    }

    // Get all workers
    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers() {
        List<Worker> workers = repo.findAll();
        System.out.println("Retrieved all workers: " + workers);
        return ResponseEntity.ok(workers);
    }

    // Get a specific worker by ID
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable int id) {
        Worker worker = repo.findById(id);
        if (worker == null) {
            System.out.println("Worker with ID " + id + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        System.out.println("Retrieved worker: " + worker);
        return ResponseEntity.ok(worker);
    }

    // Add a new worker
    @PostMapping
    public ResponseEntity<String> addWorker(@RequestBody Worker worker) {
        repo.save(worker);
        System.out.println("Added new worker: " + worker);
        return ResponseEntity.status(HttpStatus.CREATED).body("Worker added successfully.");
    }

    // Update an existing worker
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorker(@PathVariable int id, @RequestBody Worker worker) {
        Worker existingWorker = repo.findById(id);
        if (existingWorker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worker not found.");
        }
        worker.setId(id);
        repo.save(worker);
        return ResponseEntity.ok("Worker updated successfully.");
    }
    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorker(@PathVariable int id) {
        Worker existingWorker = repo.findById(id);
        if (existingWorker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worker not found.");
        }
        repo.delete(id);
        return ResponseEntity.ok("Worker deleted successfully.");
    }
}