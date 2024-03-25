package br.com.safeway.infrastructure.repositories;

import br.com.safeway.infrastructure.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AccountRepository extends JpaRepository<AccountEntity, BigInteger> {
}
