package erni.betterask.eats.be.model;

import erni.betterask.eats.be.model.at11.MenuItem;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Meal {
    public String name;
    public BigDecimal price;
    public MealType type;

    public static Meal fromMenuItem(MenuItem menuItem) {
        return Meal.builder()
                .name(menuItem.getText())
                .price(menuItem.getPrice())
                .type(menuItem.isSoup ? MealType.SOUP : MealType.MAIN_DISH)
                .build();
    }
}
