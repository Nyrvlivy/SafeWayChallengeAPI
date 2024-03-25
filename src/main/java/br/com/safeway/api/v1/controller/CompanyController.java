package br.com.safeway.api.v1.controller;

import br.com.safeway.api.v1.dto.CompanyDTO;
import br.com.safeway.api.v1.mapper.CompanyMapper;
import br.com.safeway.business.services.CompanyService;
import br.com.safeway.infrastructure.entities.CompanyEntity;
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
@RequestMapping("/companies")
@RequiredArgsConstructor
@Tag(name = "Companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @Operation(summary = "Get all companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAllCompanies() {
        List<CompanyEntity> companies = companyService.findAllCompanies();
        List<CompanyDTO> dtos = companies.stream()
                .map(companyMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Create a new company")
    @ApiResponse(responseCode = "200", description = "Successfully created new company")
    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyEntity company = companyMapper.toEntity(companyDTO);
        CompanyEntity createdCompany = companyService.createOrUpdateCompany(company);
        return ResponseEntity.ok(companyMapper.toDto(createdCompany));
    }

    @Operation(summary = "Update an existing company")
    @ApiResponse(responseCode = "200", description = "Successfully updated company")
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        CompanyEntity company = companyService.findById(id);
        CompanyEntity updatedCompany = companyService.createOrUpdateCompany(company);
        return ResponseEntity.ok(companyMapper.toDto(updatedCompany));
    }

    @Operation(summary = "Delete a company")
    @ApiResponse(responseCode = "200", description = "Successfully deleted company")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
