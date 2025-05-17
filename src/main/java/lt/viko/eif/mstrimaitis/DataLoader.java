package lt.viko.eif.mstrimaitis;

import lt.viko.eif.mstrimaitis.Repository.FarmerRepository;
import lt.viko.eif.mstrimaitis.Repository.WorkerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * DataLoader is a Spring component that runs automatically at application startup.
 * It preloads sample data into the in-memory Farmer and Worker repositories.
 */
@Component // Marks this class as a Spring-managed bean that runs at startup
public class DataLoader implements CommandLineRunner {

    private final FarmerRepository farmerRepository;
    private final WorkerRepository workerRepository;

    /**
     * Constructor-based dependency injection for repositories.
     * @param farmerRepository the repository for Farmer entities
     * @param workerRepository the repository for Worker entities
     */
    public DataLoader(FarmerRepository farmerRepository, WorkerRepository workerRepository) {
        this.farmerRepository = farmerRepository;
        this.workerRepository = workerRepository;
    }

    /**
     * This method runs after the Spring Boot application has started.
     * It preloads sample Farmer and Worker objects into the repositories.
     */
    @Override
    public void run(String... args) throws Exception {
        // Create and populate Farmer objects
        Farmer f1 = new Farmer();
        f1.setId(1);
        f1.setName("Alice");
        f1.setAddress("123 Main Street");
        f1.setPhone("123-456-7890");
        f1.setEmail("alice@example.com");

        Farmer f2 = new Farmer();
        f2.setId(2);
        f2.setName("Bob");
        f2.setAddress("456 Elm Street");
        f2.setPhone("234-567-8901");
        f2.setEmail("bob@example.com");

        Farmer f3 = new Farmer();
        f3.setId(3);
        f3.setName("Charlie");
        f3.setAddress("789 Oak Avenue");
        f3.setPhone("345-678-9012");
        f3.setEmail("charlie@example.com");

        // Save Farmer objects to the repository
        farmerRepository.save(f1);
        farmerRepository.save(f2);
        farmerRepository.save(f3);

        // Create and populate Worker objects
        Worker w1 = new Worker();
        w1.setId(1);
        w1.setName("Alice's Worker");
        w1.setSalary(1500.0);

        Worker w2 = new Worker();
        w2.setId(2);
        w2.setName("Bob's Worker");
        w2.setSalary(2400.5);

        Worker w3 = new Worker();
        w3.setId(3);
        w3.setName("Charlie's Worker");
        w3.setSalary(300.0);

        // Save Worker objects to the repository
        workerRepository.save(w1);
        workerRepository.save(w2);
        workerRepository.save(w3);
    }
}
