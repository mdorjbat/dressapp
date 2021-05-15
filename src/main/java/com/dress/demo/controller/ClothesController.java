package com.dress.demo.controller;

import com.dress.demo.model.Clothes;
import com.dress.demo.service.ClothesService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dress.demo.repository.ClothesRepository;
import com.dress.demo.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClothesController {
    private ClothesService clothesService;

    @Autowired
    public void setClothesService(ClothesService clothesService){this.clothesService = clothesService;}


    //Getting list of clothes of current user
    @GetMapping("/clothes")
    public List<Clothes> getClothes() { return clothesService.getClothes();}

    //Getting single clothes with given Id of current user
    @GetMapping("/clothes/{clothId}")
    public Clothes getCloth(@PathVariable Long clothId){
        System.out.println("testing getClothes method");
        return clothesService.getCloth(clothId);
    }

    //Create a new clothes
    @PostMapping("/clothes")
    public Clothes createClothes(@RequestBody Clothes clothesObject){
        System.out.println("creating new clothes");
        return clothesService.createClothes(clothesObject);
    }

    //update existing clothes
    @PutMapping("/clothes/{clothId}")
    public Clothes updateCloth(@PathVariable Long clothId, @RequestBody Clothes clothesObject){
        return clothesService.updateCloth(clothId, clothesObject);
    }

    //delete a cloth belonging to current user
    @DeleteMapping("/clothes/{clothId}")
    public String deleteCloth(@PathVariable Long clothId){
        return clothesService.deleteCloth(clothId);
    }


}
