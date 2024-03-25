package br.com.safeway.api.v1.controller;

import br.com.safeway.api.v1.dto.AccountDTO;
import br.com.safeway.api.v1.mapper.AccountMapper;
import br.com.safeway.business.services.AccountService;
import br.com.safeway.infrastructure.entities.AccountEntity;
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
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Operation(summary = "Get all accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountEntity> accountEntities = accountService.findAllAccounts();
        List<AccountDTO> accountDTOs = accountEntities.stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accountDTOs);
    }

    @Operation(summary = "Create a new account")
    @ApiResponse(responseCode = "200", description = "Successfully created new account")
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountEntity accountEntity = accountMapper.toEntity(accountDTO);
        AccountEntity savedAccount = accountService.createOrUpdateAccount(accountEntity);
        return ResponseEntity.ok(accountMapper.toDto(savedAccount));
    }

    @Operation(summary = "Update an existing account")
    @ApiResponse(responseCode = "200", description = "Successfully updated account")
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        AccountEntity existingAccount = accountService.findById(id);
        existingAccount.setBalance(accountDTO.getBalance());
        AccountEntity updatedAccount = accountService.createOrUpdateAccount(existingAccount);
        return ResponseEntity.ok(accountMapper.toDto(updatedAccount));
    }

    @Operation(summary = "Delete an account")
    @ApiResponse(responseCode = "200", description = "Successfully deleted account")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
