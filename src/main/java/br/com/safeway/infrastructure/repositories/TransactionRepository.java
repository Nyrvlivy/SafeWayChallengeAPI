package br.com.safeway.infrastructure.repositories;

import br.com.safeway.infrastructure.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
}
