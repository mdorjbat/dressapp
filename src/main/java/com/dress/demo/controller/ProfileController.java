package com.dress.demo.controller;

import com.dress.demo.model.Clothes;
import com.dress.demo.model.Profile;
import com.dress.demo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfileController {

    private ProfileService profileService;

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    //Getting list of clothes of current user
    @GetMapping("/profile")
    public Profile getProfile() { return profileService.getProfile();}

    @PostMapping("/profile")
    public ResponseEntity<HashMap> createProfile(@RequestBody Profile profileObject) {
        System.out.println("calling createUserProfile ==>");
        String status = profileService.createProfile(profileObject);
        HashMap message = new HashMap();
        message.put("message", status);
        return new ResponseEntity<HashMap>(message, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<HashMap> updateProfile(@RequestBody Profile userProfileObject) {
        System.out.println("calling updateUserProfile ==>");
        profileService.updateProfile(userProfileObject);
        HashMap responseMessage = new HashMap();

        responseMessage.put("Status", "Profile was successfully updated!");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }
}
