package com.example.usercrudapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="email")
    private String email;
    @Column(name="address")
    private String address;
    @Column(name="phoneNumber")
    private String phoneNumber;
}
