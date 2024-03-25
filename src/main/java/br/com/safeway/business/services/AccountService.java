package br.com.safeway.business.services;

import br.com.safeway.infrastructure.entities.AccountEntity;
import br.com.safeway.infrastructure.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountEntity findById(Long id) {
        return accountRepository.findById(BigInteger.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    public List<AccountEntity> findAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountEntity createOrUpdateAccount(AccountEntity account) {
        if (account.getId() == null && account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New account balance must be zero");
        }
        return accountRepository.save(account);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(BigInteger.valueOf(id));
    }
}
