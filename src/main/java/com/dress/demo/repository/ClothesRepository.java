package com.dress.demo.repository;

import com.dress.demo.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository <Clothes, Long> {



}
