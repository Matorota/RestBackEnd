package lt.viko.eif.mstrimaitis.Repository;

import lt.viko.eif.mstrimaitis.Worker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple in-memory repository for managing Worker entities.
 *
 * This class uses a HashMap to simulate a database, providing basic
 * create, read, update, and delete (CRUD) operations on Worker objects.
 */
@Repository
public class WorkerRepository {

    private final Map<Integer, Worker> workers = new HashMap<>();

    public List<Worker> findAll() {
        return new ArrayList<>(workers.values()); // Return a copy of all workers
    }

    public Worker findById(int id) {
        return workers.get(id); // Retrieve worker by ID
    }


    public void save(Worker worker) {
        workers.put(worker.getId(), worker); // Add or update worker
    }


    public void delete(int id) {
        workers.remove(id); // Remove worker by ID
    }
}
