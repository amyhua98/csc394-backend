package com.csc394.capStoneProject.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csc394.capStoneProject.entities.User;
import com.csc394.capStoneProject.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    

@Autowired
UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null ) {
            throw new UsernameNotFoundException("roles innexistants");
        } 
       
        return UserPrincipal.create(com.csc394.capStoneProject.dto.UserDTO.entityToDTO(user));
    }
}
