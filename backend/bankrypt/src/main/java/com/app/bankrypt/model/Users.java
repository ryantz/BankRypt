package com.app.bankrypt.model;

import com.app.bankrypt.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_table")
public class Users {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_role", nullable = false)
    private UserRoles user_role;

    @Column(name="user_first_name", nullable = false)
    private String first_name;

    @Column(name="user_last_name", nullable = false)
    private String last_name;

    @Column(name="user_name", nullable = false)
    private String user_name;

    // needs some sort of hashing
    @Column(name="user_password", nullable = false)
    private String password;

    @Column(name="user_cash", nullable = false)
    private Double user_cash_value;
}
