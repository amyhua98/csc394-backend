package com.csc394.capStoneProject.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc394.capStoneProject.config.JwtTokenProvider;
import com.csc394.capStoneProject.dto.AuthRequestDTO;
import com.csc394.capStoneProject.dto.JwtAuthenticationResponseDTO;

@RequestMapping("/login")
@RestController
public class AuthentificationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;
    // be attention muste have a whithe space at the end of variable in case of new one
   


    @PostMapping
    public ResponseEntity<JwtAuthenticationResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUserName(),
                authRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponseDTO(jwt));
    }


}
