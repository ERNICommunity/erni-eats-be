package erni.betterask.eats.be.model;

public enum PriceLevel {
    FREE(0),
    INEXPENSIVE(1),
    MODERATE(2),
    EXPENSIVE(3),
    VERY_EXPENSIVE(4);

    private final int value;

    PriceLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
