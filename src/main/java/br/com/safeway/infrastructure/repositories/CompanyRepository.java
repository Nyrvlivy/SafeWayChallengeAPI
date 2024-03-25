package br.com.safeway.infrastructure.repositories;

import br.com.safeway.infrastructure.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CompanyRepository extends JpaRepository<CompanyEntity, BigInteger> {
}
