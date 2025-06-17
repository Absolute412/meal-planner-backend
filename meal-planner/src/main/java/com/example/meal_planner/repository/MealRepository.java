package com.example.meal_planner.repository;

import com.example.meal_planner.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository <Meal, Long> {
}
