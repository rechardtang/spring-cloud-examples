package com.example.jwtsecurity.service.impl;

import com.example.jwtsecurity.repository.UserRepository;
import com.example.jwtsecurity.dto.UserDto;
import com.example.jwtsecurity.entity.User;
import com.example.jwtsecurity.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User found = userRepository.findUserByUsername(username);
        if (found == null) return null;
        return new org.springframework.security.core.userdetails.User(found.getUsername(), found.getPassword(), new ArrayList<>());
    }

    @Override
    public void addUser(UserDto userDto) {
        userRepository.save(User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build());
    }
}
