package br.com.safeway.api.v1.mapper;

import br.com.safeway.api.v1.dto.AccountDTO;
import br.com.safeway.infrastructure.entities.AccountEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class AccountMapper {

    public AccountEntity toEntity(AccountDTO dto) {
        AccountEntity entity = new AccountEntity();
        entity.setBalance(dto.getBalance());
        return entity;
    }

    public AccountDTO toDto(AccountEntity entity) {
        AccountDTO dto = new AccountDTO();
        dto.setId(BigInteger.valueOf(entity.getId()));
        dto.setBalance(entity.getBalance());
        if (entity.getCompany() != null) {
            dto.setCompanyId(BigInteger.valueOf(entity.getCompany().getId()));
        }
        return dto;
    }
}

