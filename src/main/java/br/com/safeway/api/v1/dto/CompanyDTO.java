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
public class CompanyDTO {

    @JsonProperty(value = "id")
    private BigInteger id;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "cnpj")
    private String cnpj;
    @JsonProperty(value = "phone")
    private String phone;
    @JsonProperty(value = "email")
    private BigDecimal administrationFee;

}
