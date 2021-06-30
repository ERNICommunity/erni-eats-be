package erni.betterask.eats.be.model.at11;

import lombok.Data;

import java.util.List;

@Data
public class MenuResult {
    public List<MenuItem> menu;
    public String timeago;
}
