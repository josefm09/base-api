package com.example.demo.controllers;

import com.example.demo.dto.User;
import com.example.demo.services.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("user")
    public ResponseEntity<User> login(@RequestParam("user") String username, @RequestParam("password") String pwd){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, pwd));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = getJWTToken(username);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = new User();
        user.set_id(userDetails.getId());
        user.setFullName(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setToken(jwt);

        return ResponseEntity.ok(user);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("${base.app.jwtSecret}")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
