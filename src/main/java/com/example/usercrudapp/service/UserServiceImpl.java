package com.example.usercrudapp.service;

import com.example.usercrudapp.model.User;
import com.example.usercrudapp.repo.UserRepo;
import com.example.usercrudapp.util.CSVProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.example.usercrudapp.util.TextUtil.isAbsent;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo repo;

    @Autowired
    public UserServiceImpl(final UserRepo repo){
        this.repo = repo;
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public void uploadFromCSV(MultipartFile file){
        try {
            List<User> users = CSVProcessor.csvToTutorials(file.getInputStream());
            repo.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

    @Override
    public List<User> findByNameOrSurname(Optional<String> name, Optional<String> surname) {
        if(isAbsent(name) && isAbsent(surname)) {
            return findAll();
        }
        if(isAbsent(name)){
            return findBySurname(surname.orElse(""));
        }
        if(isAbsent(surname)) {
            return findByName(name.orElse(""));
        }
       return repo.findByNameOrSurname(name.get(),surname.get());
    }

    @Override
    public List<User> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<User> findBySurname(String surname) {
        return findBySurname(surname);
    }

    @Override
    public List<User> findByEmail(String email) {
        return findByEmail(email);
    }

    @Override
    public List<User> findByAddress(String email) {
        return findByAddress(email);
    }

    @Override
    public List<User> findByPhoneNumber(String phoneNumber) {
        return findByPhoneNumber(phoneNumber);
    }
}
