package org.example.salaryincrement.Controller;

import org.example.salaryincrement.DTO.CreateCareerRequest;
import org.example.salaryincrement.Model.Career;
import org.example.salaryincrement.Service.CareerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/careers")
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @PostMapping
    public ResponseEntity<Career> createCareer(@RequestBody CreateCareerRequest career) {
        System.out.println(career.getUserId());
        Career created = careerService.createCareer(career);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Career>> getAllCareers() {
        return ResponseEntity.ok(careerService.getAllCareers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Career> getCareerById(@PathVariable Long id) {
        return careerService.getCareerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Career> updateCareer(@PathVariable Long id, @RequestBody Career updatedCareer) {

        Career updated = careerService.updateCareer(id, updatedCareer);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCareer(@PathVariable Long id) {
        careerService.deleteCareer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Career>> getCareersByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(careerService.getCareersByUserId(userId));
    }

    @GetMapping("/start-date/{date}")
    public ResponseEntity<List<Career>> getCareersByStartDate(@PathVariable LocalDate date) {
        return ResponseEntity.ok(careerService.getCareersByStartDate(date));
    }
}
