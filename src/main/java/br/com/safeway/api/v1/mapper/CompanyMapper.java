package br.com.safeway.api.v1.mapper;

import br.com.safeway.api.v1.dto.CompanyDTO;
import br.com.safeway.infrastructure.entities.CompanyEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class CompanyMapper {

    public CompanyEntity toEntity(CompanyDTO dto) {
        CompanyEntity entity = new CompanyEntity();
        entity.setName(dto.getName());
        entity.setCnpj(dto.getCnpj());
        entity.setPhone(dto.getPhone());
        entity.setAdministrationFee(dto.getAdministrationFee());
        return entity;
    }

    public CompanyDTO toDto(CompanyEntity entity) {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(BigInteger.valueOf(entity.getId()));
        dto.setName(entity.getName());
        dto.setCnpj(entity.getCnpj());
        dto.setPhone(entity.getPhone());
        dto.setAdministrationFee(entity.getAdministrationFee());
        return dto;
    }
}
