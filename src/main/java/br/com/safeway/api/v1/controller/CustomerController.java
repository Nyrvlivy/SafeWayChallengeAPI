package br.com.safeway.api.v1.controller;

import br.com.safeway.api.v1.dto.CustomerDTO;
import br.com.safeway.api.v1.mapper.CustomerMapper;
import br.com.safeway.business.services.CustomerService;
import br.com.safeway.infrastructure.entities.CustomerEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAllCustomers() {
        List<CustomerEntity> customers = customerService.findAllCustomers();
        List<CustomerDTO> dtos = customers.stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Create a new customer")
    @ApiResponse(responseCode = "200", description = "Successfully created new customer")
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerEntity customer = customerMapper.toEntity(customerDTO);
        CustomerEntity createdCustomer = customerService.createOrUpdateCustomer(customer);
        return ResponseEntity.ok(customerMapper.toDto(createdCustomer));
    }

    @Operation(summary = "Update an existing customer")
    @ApiResponse(responseCode = "200", description = "Successfully updated customer")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        CustomerEntity customer = customerService.findById(id);
        CustomerEntity updatedCustomer = customerService.createOrUpdateCustomer(customer);
        return ResponseEntity.ok(customerMapper.toDto(updatedCustomer));
    }

    @Operation(summary = "Delete a customer")
    @ApiResponse(responseCode = "200", description = "Successfully deleted customer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
