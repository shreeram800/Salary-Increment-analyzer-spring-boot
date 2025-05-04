package org.example.salaryincrement.Service;
import org.example.salaryincrement.DTO.InflationDataDTO;
import org.example.salaryincrement.DTO.InflationDataMapper;
import org.example.salaryincrement.Exceptions.InflationDataNotFoundException;
import org.example.salaryincrement.Model.InflationData;
import org.example.salaryincrement.Repository.InflationDataRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InflationDataService {

    private final InflationDataRepository repository;
    private final InflationDataMapper mapper;

    public InflationDataService(InflationDataRepository repository, InflationDataMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public InflationDataDTO createInflationData(InflationDataDTO dto) {
        InflationData entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }


    public InflationDataDTO getInflationDataById(Long id) {
        InflationData entity = repository.findById(id)
                .orElseThrow(() -> new InflationDataNotFoundException("Inflation data not found with ID: " + id));
        return mapper.toDTO(entity);
    }


    public List<InflationDataDTO> getAllInflationData() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public InflationDataDTO updateInflationData(Long id, InflationDataDTO dto) {
        InflationData entity = repository.findById(id)
                .orElseThrow(() -> new InflationDataNotFoundException("Inflation data not found with ID: " + id));

        entity.setApplicableDate(dto.getApplicableDate());
        entity.setInflationRate(dto.getInflationRate());
        entity.setSource(dto.getSource());

        return mapper.toDTO(repository.save(entity));
    }

    public void deleteInflationData(Long id) {
        if (!repository.existsById(id)) {
            throw new InflationDataNotFoundException("Inflation data not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
