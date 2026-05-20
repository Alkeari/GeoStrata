package net.alkeari.geostrata.stone;

public record StoneType(
    String name,
    RockCategory category,
    float hardness,
    float resistance,
    int toolLevel
) {}
