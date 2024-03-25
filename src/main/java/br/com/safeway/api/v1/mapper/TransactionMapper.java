package br.com.safeway.api.v1.mapper;

import br.com.safeway.api.v1.dto.TransactionDTO;
import br.com.safeway.infrastructure.entities.TransactionEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class TransactionMapper {

    public TransactionEntity toEntity(TransactionDTO dto) {
        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionType(dto.getTransactionType());
        entity.setAmount(dto.getAmount());
        entity.setFeeAmount(dto.getFeeAmount());
        entity.setTransactionDate(dto.getTransactionDate());
        return entity;
    }

    public TransactionDTO toDto(TransactionEntity entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(BigInteger.valueOf(entity.getId()));
        dto.setTransactionType(entity.getTransactionType());
        dto.setAmount(entity.getAmount());
        dto.setFeeAmount(entity.getFeeAmount());
        dto.setTransactionDate(entity.getTransactionDate());
        if (entity.getAccount() != null) {
            dto.setAccountId(BigInteger.valueOf(entity.getAccount().getId()));
        }
        if (entity.getCustomer() != null) {
            dto.setCustomerId(BigInteger.valueOf(entity.getCustomer().getId()));
        }
        return dto;
    }
}
