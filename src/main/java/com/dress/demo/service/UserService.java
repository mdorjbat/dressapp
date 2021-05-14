package com.dress.demo.service;

import com.dress.demo.exception.InformationExistException;
import com.dress.demo.exception.InformationNotFoundException;
import com.dress.demo.model.User;
import com.dress.demo.model.request.LoginRequest;
import com.dress.demo.model.response.LoginResponse;
import com.dress.demo.repository.ProfileRepository;
import com.dress.demo.repository.UserRepository;
import com.dress.demo.security.JWTUtils;
import com.dress.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    private UserRepository userRepository;
    private ProfileRepository userProfileRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserProfileRepository(ProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public void createUser(User userObject) {
        System.out.println("service calling createUser ==>");
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            userRepository.save(userObject);
        } else {
            throw new InformationExistException("User with email address " + userObject.getEmailAddress() +
                    " already exists!");
        }
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        System.out.println("service calling loginUser ==>");
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String JWT = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(JWT));
    }

    public User findUserByEmailAddress(String email) {
        return userRepository.findUserByEmailAddress(email);
    }



    //Changing current user password
    public void updatePassword(HashMap<String, String> passwordObj){
        String password = passwordObj.get("password");

        System.out.println("service calling updatePassword");
        if(password.isEmpty()){
            throw new InformationNotFoundException("Your password doesn't meet requirement. Password cannot be empty!");
        }

        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDetails.getUser();
        currentUser.setPassword(passwordEncoder.encode(password));

        userRepository.save(currentUser);
    }
}





