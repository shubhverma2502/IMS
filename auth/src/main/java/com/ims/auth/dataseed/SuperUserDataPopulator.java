package com.ims.auth.dataseed;

import com.ims.common.entity.User;
import com.ims.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SuperUserDataPopulator implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
    if(!this.userRepository.existsByUsername("admin@secure.com")){
        //create the user
        final User user = new User();
        user.setUserId(2000L);
        user.setUsername("admin@secure.com");
        user.setFirstname("Super");
        user.setLastname("Administrator");
        user.setPassword(this.passwordEncoder.encode("Admin@1234"));
        user.setRole("ADMIN");
        this.userRepository.save(user);
    }
    }
}
