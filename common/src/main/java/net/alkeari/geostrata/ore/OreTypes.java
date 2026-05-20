package net.alkeari.geostrata.ore;

import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class OreTypes {
    private OreTypes() {}

    public static final OreType COAL     = new OreType("coal",     "Coal",     "coal_ore_overlay",     17, 20, -60, 192, 0, 2, 1, () -> Items.COAL);
    public static final OreType IRON     = new OreType("iron",     "Iron",     "iron_ore_overlay",      9, 20, -60,  72, 0, 0, 1, () -> Items.RAW_IRON);
    public static final OreType COPPER   = new OreType("copper",   "Copper",   "copper_ore_overlay",    9, 16, -16, 112, 0, 0, 1, () -> Items.RAW_COPPER);
    public static final OreType GOLD     = new OreType("gold",     "Gold",     "gold_ore_overlay",      9,  4, -64,  32, 0, 1, 2, () -> Items.RAW_GOLD);
    public static final OreType REDSTONE = new OreType("redstone", "Redstone", "redstone_ore_overlay",  8,  8, -64,  15, 1, 5, 2, () -> Items.REDSTONE);
    public static final OreType LAPIS    = new OreType("lapis",    "Lapis",    "lapis_ore_overlay",     7,  7, -32,  64, 2, 5, 1, () -> Items.LAPIS_LAZULI);
    public static final OreType DIAMOND  = new OreType("diamond",  "Diamond",  "diamond_ore_overlay",   8,  7, -64,  16, 3, 7, 2, () -> Items.DIAMOND);
    public static final OreType EMERALD  = new OreType("emerald",  "Emerald",  "emerald_ore_overlay",   3,  1, -16, 480, 3, 7, 2, () -> Items.EMERALD);

    private static final List<OreType> REGISTRY = new ArrayList<>(List.of(
        COAL, IRON, COPPER, GOLD, REDSTONE, LAPIS, DIAMOND, EMERALD
    ));

    public static final List<OreType> ALL = Collections.unmodifiableList(REGISTRY);

    /**
     * Register a custom ore type so GeoStrata generates stone-variant ore blocks for it.
     * Must be called before GeoStrataOreBlocks.init() (i.e., before GeoStrata.init()).
     * The overlayTexture must be available under assets/geostrata/textures/block/ on the classpath.
     */
    public static void register(OreType ore) {
        REGISTRY.add(ore);
    }
}
