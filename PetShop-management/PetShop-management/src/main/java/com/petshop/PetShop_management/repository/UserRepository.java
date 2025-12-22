package com.petshop.PetShop_management.repository;

import com.petshop.PetShop_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>  {
    List<User> findByLastNameContainingIgnoreCase(String lastname);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long user_id);

    List<User> findByPhoneNum(String phoneNumber);
}
