package com.hidalgocarlos.ZonaFitSpringBoot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
// Mét-odos getter y setter
@Data
// Constructor si argumentos
@NoArgsConstructor
// Constructor con todos los argumentos
@AllArgsConstructor
// Mét-odo toString
@ToString
// Equal y hashcode
@EqualsAndHashCode
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Integer idCustomer;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String dni;
    private Integer membership;
    private String cellphone;
    private String address;
}
