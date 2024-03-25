package br.com.safeway.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity(name = "CostumerEntity")
@Table(name = "costumers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "phone")
    private String phone;

}
