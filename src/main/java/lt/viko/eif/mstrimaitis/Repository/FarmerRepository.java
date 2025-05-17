package lt.viko.eif.mstrimaitis.Repository;

import lt.viko.eif.mstrimaitis.Farmer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple in-memory repository for managing Farmer entities.
 *
 * This class mimics a database using a HashMap where the key is the Farmer's ID.
 * It provides basic CRUD operations: create, read, update, delete.
 */
@Repository
public class FarmerRepository {

    private final Map<Integer, Farmer> farmers = new HashMap<>();


    public List<Farmer> findAll() {
        return new ArrayList<>(farmers.values()); // Return a copy of the list
    }


    public Farmer findById(int id) {
        return farmers.get(id); // Return farmer with the given ID
    }


    public void save(Farmer farmer) {
        farmers.put(farmer.getId(), farmer); // Add or update farmer
    }

    public void delete(int id) {
        farmers.remove(id); // Remove farmer with the given ID
    }
}
