package br.com.safeway.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    @JsonProperty(value = "id")
    private BigInteger id;
    @JsonProperty(value = "balance")
    private BigDecimal balance;
    @JsonProperty(value = "customerId")
    private BigInteger companyId;

}