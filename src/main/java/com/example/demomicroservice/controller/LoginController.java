package com.example.demomicroservice.controller;

import com.example.demomicroservice.model.AppUser;
import com.example.demomicroservice.service.AppUserService;
import com.example.demomicroservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AppUserService appUserService;


    @PostMapping("/login")
    public String login(@RequestBody AppUser appUser){
        try {
            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtService.createToken(authentication);
        } catch (Exception e) {
            return null;
        }

    }
}
