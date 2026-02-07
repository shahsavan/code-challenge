package arrays.simple.sum;

import com.hackerrank.api.model.Covid;
import com.hackerrank.api.service.CovidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/covid")
public class CovidController {
    private final CovidService covidService;

    public CovidController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Covid> getCovidById(@PathVariable Long id) {
        Optional<Covid> covid = covidService.findById(id);
        return covid.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/top5")
    public ResponseEntity<?> getTop5(@RequestParam String by) {
        try {
            List<Covid> top5 = covidService.getTop5(by);
            return ResponseEntity.ok(top5);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}