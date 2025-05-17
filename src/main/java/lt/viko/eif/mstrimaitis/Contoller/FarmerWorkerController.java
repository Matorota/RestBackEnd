package lt.viko.eif.mstrimaitis.Contoller;

import lt.viko.eif.mstrimaitis.Farmer;
import lt.viko.eif.mstrimaitis.Repository.FarmerRepository;
import lt.viko.eif.mstrimaitis.Repository.WorkerRepository;
import lt.viko.eif.mstrimaitis.Worker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller handles a combined endpoint that returns both farmers and workers.
 * URL: http://localhost:8080/api/farmerworker
 * It uses FarmerRepository and WorkerRepository to fetch data from the database.
 */
@RestController
@RequestMapping("/api/farmerworker")
public class FarmerWorkerController {

    // Repository for accessing Farmer data
    private final FarmerRepository farmerRepo;

    // Repository for accessing Worker data
    private final WorkerRepository workerRepo;

    /**
     * Constructor injection for repositories.
     * @param farmerRepo repository for Farmer entity
     * @param workerRepo repository for Worker entity
     */
    public FarmerWorkerController(FarmerRepository farmerRepo, WorkerRepository workerRepo) {
        this.farmerRepo = farmerRepo;
        this.workerRepo = workerRepo;
    }

    /**
     * GET /api/farmerworker
     * Fetches all farmers and all workers from their respective repositories,
     * and returns a string combining both lists.
     *
     * @return a string listing all farmers and workers
     */
    @GetMapping
    public String getFarmerWorkerInfo() {
        // Fetch all farmers
        List<Farmer> farmers = farmerRepo.findAll();

        // Fetch all workers
        List<Worker> workers = workerRepo.findAll();

        // Return combined string of both lists
        return "Farmers: " + farmers + "\nWorkers: " + workers;
    }
}
