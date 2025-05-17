package lt.viko.eif.mstrimaitis.Contoller;

import lt.viko.eif.mstrimaitis.Farmer;
import lt.viko.eif.mstrimaitis.Repository.FarmerRepository;
import lt.viko.eif.mstrimaitis.Repository.WorkerRepository;
import lt.viko.eif.mstrimaitis.Worker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/farmerworker")
public class FarmerWorkerController {

    private final FarmerRepository farmerRepo;
    private final WorkerRepository workerRepo;

    public FarmerWorkerController(FarmerRepository farmerRepo, WorkerRepository workerRepo) {
        this.farmerRepo = farmerRepo;
        this.workerRepo = workerRepo;
    }

    @GetMapping
    public String getFarmerWorkerInfo() {
        List<Farmer> farmers = farmerRepo.findAll();
        List<Worker> workers = workerRepo.findAll();
        return "Farmers: " + farmers + "\nWorkers: " + workers;
    }
}