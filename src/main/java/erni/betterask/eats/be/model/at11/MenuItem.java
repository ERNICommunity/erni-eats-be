package erni.betterask.eats.be.model.at11;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItem {
    public boolean isSoup;
    public BigDecimal price;
    public String text;
}
