package com.dress.demo.controller;

import com.dress.demo.model.Clothes;
import com.dress.demo.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClothesController {
    private ClothesService clothesService;

    public void setClothesService(ClothesService clothesService){this.clothesService = clothesService;}

    //Getting list of clothes of current user
    @GetMapping("/clothes")
    public List<Clothes> getClothes() { return clothesService.getClothes();}

    //Getting single clothes with given Id of current user
    @GetMapping("/clothes/{clothesId}")
    public Clothes getCloth(@PathVariable Long clothId){
        System.out.println("testing getClothes method");
        return clothesService.getCloth(clothId);
    }

    //Create a new clothes
    @PostMapping("/")
}
