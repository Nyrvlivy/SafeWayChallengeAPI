package br.com.safeway.business.services;

import br.com.safeway.business.utils.ValidateCNPJ;
import br.com.safeway.infrastructure.entities.CompanyEntity;
import br.com.safeway.infrastructure.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyEntity findById(Long id) {
        return companyRepository.findById(BigInteger.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
    }

    public List<CompanyEntity> findAllCompanies() {
        return companyRepository.findAll();
    }

    public CompanyEntity createOrUpdateCompany(CompanyEntity company) {
        if (!ValidateCNPJ.isValidCNPJ(company.getCnpj())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid CNPJ");
        }
        return companyRepository.save(company);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(BigInteger.valueOf(id));
    }
}
