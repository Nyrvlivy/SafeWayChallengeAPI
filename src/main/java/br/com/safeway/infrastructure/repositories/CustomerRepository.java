package br.com.safeway.infrastructure.repositories;

import br.com.safeway.infrastructure.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CustomerRepository extends JpaRepository<CustomerEntity, BigInteger> {
}
