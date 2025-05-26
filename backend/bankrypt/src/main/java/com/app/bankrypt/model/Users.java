package com.app.bankrypt.model;

import com.app.bankrypt.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_table")
public class Users extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_email", nullable=false)
    private String email;

    @Column(name="user_first_name", nullable=false)
    private String firstName;

    @Column(name="user_last_name", nullable=false)
    private String lastName;

    @Column(name="user_contact", nullable=true)
    private Long contactNumber;

    @Column(name="user_name", nullable=false)
    private String username;

    // needs some sort of hashing
    @Column(name="user_password", nullable=false)
    private String password;

    @Column(name="user_role", nullable=false)
    private UserRoles userRole;
}
