package com.petshop.PetShop_management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.petshop.PetShop_management.repository.UserRepository;
import com.petshop.PetShop_management.entity.User;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setSurName("Testov"); 
        user.setEmail("test@example.com");
        user.setPassword(passwordEncoder.encode("testpassword")); 
        user.setPhoneNum("123456789");
        user.setAddress("Test Address");
        userRepository.save(user);
        System.out.println("Тестовый пользователь создан: test@example.com / testpassword (хэшированный пароль в БД)");
    }
}



