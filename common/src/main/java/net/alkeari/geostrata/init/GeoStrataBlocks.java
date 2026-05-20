package net.alkeari.geostrata.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.alkeari.geostrata.GeoStrata;
import net.alkeari.geostrata.stone.StoneType;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.LinkedHashMap;
import java.util.Map;

public final class GeoStrataBlocks {
    private GeoStrataBlocks() {}

    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(GeoStrata.MOD_ID, Registries.BLOCK);

    public static final Map<String, RegistrySupplier<? extends Block>> ALL_BLOCKS = new LinkedHashMap<>();

    public static final Map<String, RegistrySupplier<Block>> BASE      = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> SLABS     = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> STAIRS    = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> WALLS     = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> BRICKS         = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> BRICKS_SLABS   = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> BRICKS_STAIRS  = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> BRICKS_WALLS   = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> POLISHED        = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> POLISHED_SLABS  = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> POLISHED_STAIRS = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> POLISHED_WALLS  = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> DEEPSLATE       = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> COBBLED        = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> COBBLED_SLABS  = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> COBBLED_STAIRS = new LinkedHashMap<>();
    public static final Map<String, RegistrySupplier<Block>> COBBLED_WALLS  = new LinkedHashMap<>();

    public static void init() {
        for (StoneType type : StoneTypes.ALL) {
            register(type);
        }
        for (StoneType type : StoneTypes.ALL) {
            registerDeepslate(type);
        }
        for (StoneType type : StoneTypes.ALL) {
            registerCobbled(type);
        }
        BLOCKS.register();
    }

    private static void register(StoneType type) {
        String n = type.name();
        BlockBehaviour.Properties props = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .requiresCorrectToolForDrops()
            .strength(type.hardness(), type.resistance())
            .sound(SoundType.STONE);

        RegistrySupplier<Block> base = reg(n, () -> new Block(props));
        BASE.put(n, base);

        RegistrySupplier<Block> slab = reg(n + "_slab", () -> new SlabBlock(props));
        SLABS.put(n, slab);

        RegistrySupplier<Block> stair = reg(n + "_stairs",
            () -> new StairBlock(base.get().defaultBlockState(), props));
        STAIRS.put(n, stair);

        RegistrySupplier<Block> wall = reg(n + "_wall", () -> new WallBlock(props));
        WALLS.put(n, wall);

        RegistrySupplier<Block> bricks = reg(n + "_bricks", () -> new Block(props));
        BRICKS.put(n, bricks);

        RegistrySupplier<Block> bricksSlab = reg(n + "_bricks_slab", () -> new SlabBlock(props));
        BRICKS_SLABS.put(n, bricksSlab);

        RegistrySupplier<Block> bricksStair = reg(n + "_bricks_stairs",
            () -> new StairBlock(bricks.get().defaultBlockState(), props));
        BRICKS_STAIRS.put(n, bricksStair);

        RegistrySupplier<Block> bricksWall = reg(n + "_bricks_wall", () -> new WallBlock(props));
        BRICKS_WALLS.put(n, bricksWall);

        RegistrySupplier<Block> polished = reg("polished_" + n, () -> new Block(props));
        POLISHED.put(n, polished);

        RegistrySupplier<Block> polishedSlab = reg("polished_" + n + "_slab", () -> new SlabBlock(props));
        POLISHED_SLABS.put(n, polishedSlab);

        RegistrySupplier<Block> polishedStair = reg("polished_" + n + "_stairs",
            () -> new StairBlock(polished.get().defaultBlockState(), props));
        POLISHED_STAIRS.put(n, polishedStair);

        RegistrySupplier<Block> polishedWall = reg("polished_" + n + "_wall", () -> new WallBlock(props));
        POLISHED_WALLS.put(n, polishedWall);
    }

    private static void registerCobbled(StoneType type) {
        String n         = type.name();
        float hardness   = type.hardness();
        float resistance = type.resistance();
        BlockBehaviour.Properties props = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .requiresCorrectToolForDrops()
            .strength(hardness, resistance)
            .sound(SoundType.STONE);

        RegistrySupplier<Block> cob = BLOCKS.register("cobbled_" + n, () -> new Block(props));
        COBBLED.put(n, cob);
        ALL_BLOCKS.put("cobbled_" + n, cob);

        RegistrySupplier<Block> cobSlab = BLOCKS.register("cobbled_" + n + "_slab", () -> new SlabBlock(props));
        COBBLED_SLABS.put(n, cobSlab);
        ALL_BLOCKS.put("cobbled_" + n + "_slab", cobSlab);

        RegistrySupplier<Block> cobStair = BLOCKS.register("cobbled_" + n + "_stairs",
            () -> new StairBlock(cob.get().defaultBlockState(), props));
        COBBLED_STAIRS.put(n, cobStair);
        ALL_BLOCKS.put("cobbled_" + n + "_stairs", cobStair);

        RegistrySupplier<Block> cobWall = BLOCKS.register("cobbled_" + n + "_wall", () -> new WallBlock(props));
        COBBLED_WALLS.put(n, cobWall);
        ALL_BLOCKS.put("cobbled_" + n + "_wall", cobWall);
    }

    private static void registerDeepslate(StoneType type) {
        String name = "deepslate_" + type.name();
        float hardness   = type.hardness();
        float resistance = type.resistance();
        RegistrySupplier<Block> sup = BLOCKS.register(name, () ->
            new Block(BlockBehaviour.Properties.of()
                .mapColor(MapColor.DEEPSLATE)
                .requiresCorrectToolForDrops()
                .strength(hardness, resistance)
                .sound(SoundType.DEEPSLATE)));
        DEEPSLATE.put(type.name(), sup);
        ALL_BLOCKS.put(name, sup);
    }

    private static <T extends Block> RegistrySupplier<T> reg(String name,
            java.util.function.Supplier<T> factory) {
        RegistrySupplier<T> sup = BLOCKS.register(name, factory);
        ALL_BLOCKS.put(name, sup);
        return sup;
    }

    public static Block getBaseBlock(StoneType type) {
        return BASE.get(type.name()).get();
    }
}
