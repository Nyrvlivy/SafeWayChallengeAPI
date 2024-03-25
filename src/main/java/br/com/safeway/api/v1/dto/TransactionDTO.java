package br.com.safeway.api.v1.dto;

import br.com.safeway.infrastructure.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {

    @JsonProperty(value = "id")
    private BigInteger id;
    @JsonProperty(value = "accountId")
    private BigInteger accountId;
    @JsonProperty(value = "customerId")
    private BigInteger customerId;
    @JsonProperty(value = "transactionType")
    private TransactionType transactionType;
    @JsonProperty(value = "amount")
    private BigDecimal amount;
    @JsonProperty(value = "feeAmount")
    private BigDecimal feeAmount;
    @JsonProperty(value = "transactionDate")
    private Date transactionDate;

}
