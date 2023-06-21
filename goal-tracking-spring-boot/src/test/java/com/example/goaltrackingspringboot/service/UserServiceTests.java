package com.example.goaltrackingspringboot.service;

import com.example.goaltrackingspringboot.model.Response;
import com.example.goaltrackingspringboot.model.User;
import com.example.goaltrackingspringboot.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void getAllUsers_ShouldReturn2Users() {
        List<User> usersList = new ArrayList<>();
        User user1 = User.builder().id(1L).email("aaa@gmail.com").hashedPassword("123jkjsd3").lastName("aa").firstName("bb").build();
        User user2 = User.builder().id(2L).email("bbb@gmail.com").hashedPassword("ssww22123jkjsd3").lastName("bb").firstName("aa").build();
        usersList.add(user1);
        usersList.add(user2);
        when(userRepository.findAll()).thenReturn(usersList);

        Response receivedResponse = userService.getAllUsers();
        List<User> receivedUsers = (List<User>) receivedResponse.getData();

        assertThat(receivedUsers).hasSize(2);
        verify(userRepository, times(1)).findAll();
    }
}
