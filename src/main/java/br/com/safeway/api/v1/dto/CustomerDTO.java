package br.com.safeway.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    @JsonProperty(value = "id")
    private BigInteger id;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "cpf")
    private String cpf;
    @JsonProperty(value = "phone")
    private String phone;

}
