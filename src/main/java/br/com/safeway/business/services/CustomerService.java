package br.com.safeway.business.services;

import br.com.safeway.infrastructure.entities.CustomerEntity;
import br.com.safeway.infrastructure.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import br.com.safeway.business.utils.ValidateCPF;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerEntity findById(Long id) {
        return customerRepository.findById(BigInteger.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    public List<CustomerEntity> findAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerEntity createOrUpdateCustomer(CustomerEntity customer) {
        if (!ValidateCPF.isValidCPF(customer.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid CPF");
        }
        return customerRepository.save(customer);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(BigInteger.valueOf(id));
    }
}
