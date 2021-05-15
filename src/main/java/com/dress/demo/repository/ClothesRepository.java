package com.dress.demo.repository;

import com.dress.demo.model.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository <Clothes, Long> {

    Clothes findByName(String clothesName);

    Clothes findByIdAndUserId(Long clothesId, Long userId);

    Clothes findByUserIdAndName(Long userId, String clothesName);

    List<Clothes> findByUserId(Long userId);

}
