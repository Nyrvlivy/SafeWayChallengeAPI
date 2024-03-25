package br.com.safeway.business.services;

import br.com.safeway.infrastructure.entities.CompanyEntity;
import br.com.safeway.infrastructure.entities.TransactionEntity;
import br.com.safeway.infrastructure.enums.TransactionType;
import br.com.safeway.infrastructure.repositories.AccountRepository;
import br.com.safeway.infrastructure.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionEntity findById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }

    public List<TransactionEntity> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public TransactionEntity createTransaction(TransactionEntity transaction) {

        CompanyEntity company = transaction.getAccount().getCompany();
        BigDecimal fee = company.getAdministrationFee();

        if (transaction.getTransactionType() == TransactionType.DEPOSIT) {
            transaction.setAmount(transaction.getAmount().subtract(fee));
            transaction.getAccount().setBalance(transaction.getAccount().getBalance().add(transaction.getAmount()));
        } else if (transaction.getTransactionType() == TransactionType.WITHDRAWAL) {
            transaction.setAmount(transaction.getAmount().add(fee));
            transaction.getAccount().setBalance(transaction.getAccount().getBalance().subtract(transaction.getAmount()));
        }
        transaction.setFeeAmount(fee);

        accountRepository.save(transaction.getAccount());

        return transactionRepository.save(transaction);
    }


    public void deleteById(Long id) {
        transactionRepository.deleteById(Math.toIntExact(id));
    }

}
