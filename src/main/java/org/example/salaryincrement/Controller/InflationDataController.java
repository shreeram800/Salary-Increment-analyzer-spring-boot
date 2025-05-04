package org.example.salaryincrement.Controller;
import org.example.salaryincrement.DTO.InflationDataDTO;
import org.example.salaryincrement.Service.InflationDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inflation")
public class InflationDataController {

    private final InflationDataService inflationDataService;

    public InflationDataController(InflationDataService inflationDataService) {
        this.inflationDataService = inflationDataService;
    }

    @PostMapping
    public ResponseEntity<InflationDataDTO> create(@RequestBody InflationDataDTO dto) {
        return ResponseEntity.ok(inflationDataService.createInflationData(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InflationDataDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inflationDataService.getInflationDataById(id));
    }

    @GetMapping
    public ResponseEntity<List<InflationDataDTO>> getAll() {
        return ResponseEntity.ok(inflationDataService.getAllInflationData());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InflationDataDTO> update(@PathVariable Long id, @RequestBody InflationDataDTO dto) {
        return ResponseEntity.ok(inflationDataService.updateInflationData(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inflationDataService.deleteInflationData(id);
        return ResponseEntity.noContent().build();
    }
}
