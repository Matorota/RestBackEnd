package lt.viko.eif.mstrimaitis.Repository;

import lt.viko.eif.mstrimaitis.Worker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WorkerRepository {
    private final Map<Integer, Worker> workers = new HashMap<>();

    public List<Worker> findAll() {
        return new ArrayList<>(workers.values());
    }

    public Worker findById(int id) {
        return workers.get(id);
    }

    public void save(Worker worker) {
        workers.put(worker.getId(), worker);
    }

    public void delete(int id) {
        workers.remove(id);
    }
}