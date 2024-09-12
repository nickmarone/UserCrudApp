package com.example.usercrudapp.service;

import com.example.usercrudapp.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void uploadFromCSV(MultipartFile file);

    void deleteById(Long id);

    void deleteAll();

    List<User> findByNameOrSurname(String name, String surname);

    List<User> findByName(String name);

    List<User> findBySurname(String surname);

    List<User> findByEmail(String email);

    List<User> findByAddress(String email);

    List<User> findByPhoneNumber(String phoneNumber);
}
