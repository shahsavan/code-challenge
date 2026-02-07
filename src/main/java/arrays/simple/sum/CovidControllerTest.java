package arrays.simple.sum;

import com.hackerrank.api.model.Covid;
import com.hackerrank.api.service.CovidService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CovidController.class)
public class CovidControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CovidService covidService;

    @Test
    void getCovidById_ValidId_ReturnsEntry() throws Exception {
        Covid covid = new Covid(1L, "MyCountry", 574, 45, 7080);
        when(covidService.findById(1L)).thenReturn(Optional.of(covid));

        mockMvc.perform(get("/covid/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value("MyCountry"));
    }

    @Test
    void getCovidById_InvalidId_ReturnsNotFound() throws Exception {
        when(covidService.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/covid/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTop5_ValidParam_ReturnsEntries() throws Exception {
        List<Covid> top5 = List.of(
                new Covid(6L, "CountryE", 200, 90, 4000),
                new Covid(5L, "CountryD", 300, 80, 5000)
        );
        when(covidService.getTop5("death")).thenReturn(top5);

        mockMvc.perform(get("/covid/top5")
                        .param("by", "death"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].death").value(90));
    }

    @Test
    void getTop5_InvalidParam_ReturnsBadRequest() throws Exception {
        when(covidService.getTop5("invalid")).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(get("/covid/top5")
                        .param("by", "invalid"))
                .andExpect(status().isBadRequest());
    }
}