package com.example.cardapio.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;

    @CrossOrigin(origins =  "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> getAll() {
        List<FoodResponseDTO> foods = foodRepository
            .findAll()
            .stream()
            .map(food -> new FoodResponseDTO(food))
            .collect(Collectors.toList());

        return ResponseEntity.ok().body(foods);
    }

    @CrossOrigin(origins =  "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<FoodResponseDTO> create(@RequestBody FoodRequestDTO request) {
        Food food = new Food(request);

        foodRepository.save(food);

        FoodResponseDTO response = new FoodResponseDTO(food);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
