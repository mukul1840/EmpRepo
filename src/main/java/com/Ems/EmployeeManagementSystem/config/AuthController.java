package com.Ems.EmployeeManagementSystem.config;

import com.Ems.EmployeeManagementSystem.jwtModels.JwtRequest;
import com.Ems.EmployeeManagementSystem.jwtModels.JwtResponse;
import com.Ems.EmployeeManagementSystem.security.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static com.Ems.EmployeeManagementSystem.jwtModels.JwtResponse.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;

    //private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
     this.doAuthenticate(request.getEmail(), request.getPassword());
     UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
     System.out.println(userDetails);
        String token = this.helper.generateToken(userDetails,userDetails.getAuthorities());

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userName(userDetails.getUsername())
                .roles(userDetails.getAuthorities().toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {            manager.authenticate(authentication);
          } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
