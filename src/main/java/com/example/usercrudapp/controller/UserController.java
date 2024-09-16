package com.example.usercrudapp.controller;


import com.example.usercrudapp.model.User;
import com.example.usercrudapp.service.UserService;
import com.example.usercrudapp.util.CSVProcessor;
import com.example.usercrudapp.util.http.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> userList = new ArrayList<>();
            userService.findAll().forEach(userList::add);

            if (userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/user")
    public ResponseEntity<List<User>> getUsersByNameOrLastName
            (@RequestParam(required = false) String name,@RequestParam(required = false) String surname ) {
        try {
            List<User> userList = new ArrayList<>();
            userService.findByNameOrSurname(Optional.ofNullable(name),Optional.ofNullable(surname)).forEach(userList::add);

            if (userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            User createdUser = userService.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) {
        try {
            Optional<User> userData = userService.findById(id);
            if (userData.isPresent()) {
                User updatedUser = userData.get();
                updatedUser.setName(user.getName());
                updatedUser.setSurname(user.getSurname());
                updatedUser.setAddress(user.getAddress());
                updatedUser.setEmail(user.getEmail());
                updatedUser.setPhoneNumber(user.getPhoneNumber());
                User persistedUser = userService.save(updatedUser);
                return new ResponseEntity<>(persistedUser, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/users/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("users") MultipartFile file) {
        String message = "";

        if (CSVProcessor.hasCSVFormat(file)) {
            try {
                userService.uploadFromCSV(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/deleteAll")
    public ResponseEntity<HttpStatus> deleteUserById() {
        try {
            userService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
