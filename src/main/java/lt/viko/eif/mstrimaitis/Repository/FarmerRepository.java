package lt.viko.eif.mstrimaitis.Repository;

import lt.viko.eif.mstrimaitis.Farmer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FarmerRepository {
    private final Map<Integer, Farmer> farmers = new HashMap<>();

    public List<Farmer> findAll() {
        return new ArrayList<>(farmers.values());
    }

    public Farmer findById(int id) {
        return farmers.get(id);
    }

    public void save(Farmer farmer) {
        farmers.put(farmer.getId(), farmer);
    }

    public void delete(int id) {
        farmers.remove(id);
    }
}