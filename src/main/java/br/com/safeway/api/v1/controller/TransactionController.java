package br.com.safeway.api.v1.controller;

import br.com.safeway.api.v1.dto.TransactionDTO;
import br.com.safeway.api.v1.mapper.TransactionMapper;
import br.com.safeway.business.services.TransactionService;
import br.com.safeway.infrastructure.entities.TransactionEntity;
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
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @Operation(summary = "Get all transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> findAllTransactions() {
        List<TransactionEntity> transactions = transactionService.findAllTransactions();
        List<TransactionDTO> dtos = transactions.stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Create a new transaction")
    @ApiResponse(responseCode = "200", description = "Successfully created new transaction")
    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionEntity transaction = transactionMapper.toEntity(transactionDTO);
        TransactionEntity createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(transactionMapper.toDto(createdTransaction));
    }

    @Operation(summary = "Update an existing transaction")
    @ApiResponse(responseCode = "200", description = "Successfully updated transaction")
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable Integer id) {
        TransactionEntity transaction = transactionService.findById(id);
        TransactionEntity updatedTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(transactionMapper.toDto(updatedTransaction));
    }

    @Operation(summary = "Delete a transaction")
    @ApiResponse(responseCode = "200", description = "Successfully deleted transaction")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
