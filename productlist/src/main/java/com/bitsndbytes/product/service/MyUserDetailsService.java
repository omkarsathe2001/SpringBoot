//package com.bitsndbytes.product.service;
//
//import com.bitsndbytes.product.dto.UserDTO;
//import com.bitsndbytes.product.entity.User;
//import com.bitsndbytes.product.repository.UserRepository;
//import com.bitsndbytes.product.security.UserPrincipal;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService{
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//
//    public User createUser(UserDTO dto){
//        //Check for duplicate username
//        if (userRepository.existsByUsername(dto.getUsername())){
//            throw new RuntimeException("Username already exists!");
//        }
//
//        // Map DTO to entity
//        User user = new User();
//        user.setUsername(dto.getUsername());
//        user.setPassword(passwordEncoder.encode(dto.getPassword()));
//
//        //Set Role: if role not provided, default to "USER"
//        user.setRole(dto.getRole() != null ? dto.getRole() : "USER");
//
//        //save user
//        return userRepository.save(user);
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//        if(user.isEmpty()) throw new UsernameNotFoundException("User not found!");
//        return new UserPrincipal(user.get());
//    }
//}
