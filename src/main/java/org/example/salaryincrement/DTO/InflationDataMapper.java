package org.example.salaryincrement.DTO;

import org.example.salaryincrement.Model.InflationData;
import org.springframework.stereotype.Component;

@Component
public class InflationDataMapper {

    public InflationDataDTO toDTO(InflationData entity) {
        InflationDataDTO dto = new InflationDataDTO();
        dto.setInflationDataId(entity.getInflationDataId());
        dto.setApplicableDate(entity.getApplicableDate());
        dto.setInflationRate(entity.getInflationRate());
        dto.setSource(entity.getSource());
        return dto;
    }

    public InflationData toEntity(InflationDataDTO dto) {
        return new InflationData(
                dto.getApplicableDate(),
                dto.getInflationRate(),
                dto.getSource()
        );
    }
}
