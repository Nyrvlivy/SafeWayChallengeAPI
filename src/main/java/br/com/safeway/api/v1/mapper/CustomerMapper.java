package br.com.safeway.api.v1.mapper;

import br.com.safeway.api.v1.dto.CustomerDTO;
import br.com.safeway.infrastructure.entities.CustomerEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class CustomerMapper {

    public CustomerEntity toEntity(CustomerDTO dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        entity.setPhone(dto.getPhone());
        return entity;
    }

    public CustomerDTO toDto(CustomerEntity entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(BigInteger.valueOf(entity.getId()));
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setPhone(entity.getPhone());
        return dto;
    }
}
