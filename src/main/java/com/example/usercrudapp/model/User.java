package com.example.usercrudapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="email")
    private String email;
    @Column(name="address")
    private String address;
    @Column(name="phonenumber")
    private String phoneNumber;
}
