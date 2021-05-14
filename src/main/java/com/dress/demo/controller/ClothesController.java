package com.dress.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClothesController {
    private ClothesService clothesService;
    @Autowired
    public void setClothesService(ClothesService clothesService){this.clothesService = clothesService;}
}
