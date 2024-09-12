package com.example.usercrudapp.repo;

import com.example.usercrudapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name)")
    List<User> findByName(@Param("name") String name);
    @Query(value = "SELECT u FROM User u WHERE LOWER(u.surname) = LOWER(:surname)")
    List<User> findBySurname(@Param("surname") String surname);
    @Query(value = "SELECT u FROM User u WHERE u.address LIKE %:address%")
    List<User> findByAddress(@Param("address") String address);
    @Query(value = "SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    List<User> findByEmail(@Param("email") String email);
    @Query(value = "SELECT u FROM User u WHERE u.phoneNumber =:phoneNumber")
    List<User> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    @Query(value = "SELECT u FROM User u WHERE (:name is null OR LOWER(u.name) = LOWER(:name)) " +
            "AND (:surname is null OR LOWER(u.surname) = LOWER(:surname)")
    List<User> findByNameOrSurname(@Param("name") String name, @Param("surname")String surname);

}
