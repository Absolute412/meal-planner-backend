package com.example.meal_planner.controller;

import com.example.meal_planner.model.Meal;
import com.example.meal_planner.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meals")
@CrossOrigin(origins = {
        "https://simplemealplanner.netlify.app", "https://localhost:5173"
}) // Allow frontend access (e.g., React on different port)
public class MealController {

    @Autowired
    private MealRepository mealRepository;

    // GET all meals
    @GetMapping
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    // GET a single meal by ID (useful for editing)
    @GetMapping("/{id}")
    public Meal getMealById(@PathVariable Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    // POST: Add a new meal
    @PostMapping
    public Meal addMeal(@RequestBody Meal meal) {
        return mealRepository.save(meal);
    }

    // PUT: Update an existing meal
    @PutMapping("/{id}")
    public Meal updateMeal(@PathVariable Long id, @RequestBody Meal updateMeal) {
        Optional<Meal> optionalMeal = mealRepository.findById(id);
        if (optionalMeal.isPresent()) {
            Meal meal = optionalMeal.get();
            meal.setTitle(updateMeal.getTitle());
            meal.setDescription(updateMeal.getDescription());
            meal.setDate(updateMeal.getDate());
            return mealRepository.save(meal);
        }
        return null;
    }

    // DELETE: Remove a meal
    @DeleteMapping("/{id}")
    public void deleteMeal(@PathVariable Long id) {
        mealRepository.deleteById(id);
    }
}
