package net.alkeari.geostrata.ore;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public record OreType(
    String name,
    String displayName,
    String overlayTexture,
    int veinSize,
    int veinsPerChunk,
    int minY,
    int maxY,
    int minXp,
    int maxXp,
    int toolLevel,
    Supplier<Item> dropItem
) {}
