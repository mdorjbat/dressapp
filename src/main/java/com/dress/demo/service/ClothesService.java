package com.dress.demo.service;

import com.dress.demo.controller.ClothesController;
import com.dress.demo.exception.InformationExistException;
import com.dress.demo.exception.InformationNotFoundException;
import com.dress.demo.model.Clothes;
import com.dress.demo.repository.ClothesRepository;
import com.dress.demo.repository.UserRepository;
import com.dress.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class ClothesService {

    private UserRepository userRepository;
    private ClothesRepository clothesRepository;

    @Autowired
    public void setClothesRepository(ClothesRepository clothesRepository){this.clothesRepository = clothesRepository;}

    public List<Clothes> getClothes(){
        System.out.println("service calling getClothes");
        MyUserDetails userDetails = gettingUserDetails();
        List<Clothes> clothes = clothesRepository.findByUserId(userDetails.getUser().getId());
        if(clothes.isEmpty()){
            throw new InformationNotFoundException("No cothes found for that user id " + userDetails.getUser().getId());
        }else{
            return clothes;
        }
    }

    public Clothes getCloth(Long clothId){
        System.out.println("service calling getCloth");
        MyUserDetails userDetails = gettingUserDetails();
        Clothes clothes = clothesRepository.findByIdAndUserId(clothId, userDetails.getUser().getId());
        if(clothes == null){
            throw new InformationNotFoundException("cloth with id " + clothId + " not found");
        }else{
            return clothes;
        }
    }

    public Clothes createClothes(Clothes clothesObject){
        System.out.println("service calling createClothes method");
        MyUserDetails userDetails = gettingUserDetails();
        Clothes clothes = clothesRepository.findByUserIdAndName(userDetails.getUser().getId(), clothesObject.getName());
        if(clothes != null){
            throw new InformationExistException("clothes with name " + clothes.getName() + " already exist");
        }else{
            clothesObject.setUser(userDetails.getUser());
            return clothesRepository.save(clothesObject);
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
