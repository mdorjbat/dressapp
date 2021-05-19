package com.dress.demo.service;

import com.dress.demo.exception.InformationExistException;
import com.dress.demo.exception.InformationNotFoundException;
import com.dress.demo.model.Clothes;
import com.dress.demo.model.Profile;
import com.dress.demo.model.User;
import com.dress.demo.repository.ProfileRepository;
import com.dress.demo.repository.UserRepository;
import com.dress.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private UserRepository userRepository;
    private ProfileRepository profileRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile getProfile(){
        System.out.println("service calling getProfile");
        MyUserDetails userDetails = gettingUserDetails();
        Profile profile = profileRepository.findByUserId(userDetails.getUser().getId());
//        if (userDetails.getUser().getProfile() == null) {
//            throw new InformationExistException("user profile is already exists");
//        }
            return profile;
    }

    public String createProfile(Profile profileObject) {
        System.out.println("service calling createUserProfile ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (userDetails.getUser().getProfile() != null) {
            throw new InformationExistException("user profile is already exists");
        }
        userDetails.getUser().setProfile(profileObject);
        userRepository.save(userDetails.getUser());
        return "profile created successfully";
    }

    public void updateProfile(Profile profileObject) {
        System.out.println("service calling updateUserProfile ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<User> currentUser = userRepository.findById(userDetails.getUser().getId());
        if (currentUser.isPresent()) {
            Profile profile = currentUser.get().getProfile();
            profile.setFirstName(profileObject.getFirstName());
            profile.setLastName(profileObject.getLastName());
            profile.setAge(profileObject.getAge());
            profile.setGender(profileObject.getGender());
            profile.setSize(profileObject.getSize());
            profile.setPath(profileObject.getPath());
            currentUser.get().setProfile(profile);
            profileRepository.save(profile);
            //return profile;
        } else {
            throw new InformationExistException("user profile does not exist for current user");
        }
    }

    private MyUserDetails gettingUserDetails() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails;
    }
}
