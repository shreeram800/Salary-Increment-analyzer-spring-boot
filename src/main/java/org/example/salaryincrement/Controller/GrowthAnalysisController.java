package org.example.salaryincrement.Controller;

import org.example.salaryincrement.DTO.GrowthAnalysisRequestDTO;
import org.example.salaryincrement.DTO.GrowthAnalysisResponseDTO;
import org.example.salaryincrement.Service.GrowthAnalysisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/growth-analysis")
public class GrowthAnalysisController {

    private final GrowthAnalysisServiceImpl growthAnalysisService;

    @Autowired
    public GrowthAnalysisController(GrowthAnalysisServiceImpl growthAnalysisService) {
        this.growthAnalysisService = growthAnalysisService;
    }

    @PostMapping
    public ResponseEntity<GrowthAnalysisResponseDTO> analyzeGrowth(@RequestBody GrowthAnalysisRequestDTO requestDTO) {
        GrowthAnalysisResponseDTO response = growthAnalysisService.analyzeGrowth(requestDTO);
        return ResponseEntity.ok(response);
    }

}
