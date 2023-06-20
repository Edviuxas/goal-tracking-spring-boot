package com.example.goaltrackingspringboot.repository;

import com.example.goaltrackingspringboot.model.OkrGoal;
import com.example.goaltrackingspringboot.model.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoTests {
    @Autowired
    private UserRepository userRepository;
//
//    @Autowired
//    private TestEntityManager testEntityManager;

    @Test
    public void createUser_ShouldReturnUser() {
        User user = User.builder().id(1L).email("sss@gmail.com").firstName("ee").lastName("bb").hashedPassword("as22asdx33").build();
//        testEntityManager.persistAndFlush(user);
//        Optional<User> savedUser = userRepository.findById(1L);
        User savedUser = userRepository.saveAndFlush(user);
        Assertions.assertNotNull(userRepository.findById(savedUser.getId()));
//        Assertions.assertNotNull((User) savedUser.getId());
    }
}
