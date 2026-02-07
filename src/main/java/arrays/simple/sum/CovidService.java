package arrays.simple.sum;

import com.hackerrank.api.model.Covid;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CovidService {
    private final List<Covid> covidData = new ArrayList<>();

    public CovidService() {
        initializeData();
    }

    private void initializeData() {
        covidData.add(new Covid(1L, "MyCountry", 574, 45, 7080));
        covidData.add(new Covid(2L, "CountryA", 600, 60, 7000));
        covidData.add(new Covid(3L, "CountryB", 500, 50, 8000));
        covidData.add(new Covid(4L, "CountryC", 400, 70, 6000));
        covidData.add(new Covid(5L, "CountryD", 300, 80, 5000));
        covidData.add(new Covid(6L, "CountryE", 200, 90, 4000));
    }

    public Optional<Covid> findById(Long id) {
        return covidData.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public List<Covid> getTop5(String by) {
        Comparator<Covid> comparator;
        switch (by) {
            case "active":
                comparator = Comparator.comparingInt(Covid::getActive).reversed();
                break;
            case "death":
                comparator = Comparator.comparingInt(Covid::getDeath).reversed();
                break;
            case "recovered":
                comparator = Comparator.comparingInt(Covid::getRecovered).reversed();
                break;
            default:
                throw new IllegalArgumentException("Invalid attribute");
        }
        return covidData.stream()
                .sorted(comparator)
                .limit(5)
                .collect(Collectors.toList());
    }
}