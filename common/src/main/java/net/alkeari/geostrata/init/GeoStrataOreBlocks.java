package net.alkeari.geostrata.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.alkeari.geostrata.GeoStrata;
import net.alkeari.geostrata.ore.OreType;
import net.alkeari.geostrata.ore.OreTypes;
import net.alkeari.geostrata.stone.StoneType;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.LinkedHashMap;
import java.util.Map;

public final class GeoStrataOreBlocks {
    private GeoStrataOreBlocks() {}

    public static final DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(GeoStrata.MOD_ID, Registries.BLOCK);

    // ore name → (stone name → supplier)
    public static final Map<String, Map<String, RegistrySupplier<Block>>> ORE_BLOCKS = new LinkedHashMap<>();

    // flat map for iteration in datagen / items / loot
    public static final Map<String, RegistrySupplier<Block>> ALL_ORE_BLOCKS = new LinkedHashMap<>();

    public static void init() {
        for (OreType ore : OreTypes.ALL) {
            Map<String, RegistrySupplier<Block>> byStone = new LinkedHashMap<>();
            for (StoneType stone : StoneTypes.ALL) {
                int minXp = ore.minXp();
                int maxXp = ore.maxXp();
                float hardness   = stone.hardness();
                float resistance = stone.resistance();

                String blockName = stone.name() + "_" + ore.name() + "_ore";
                RegistrySupplier<Block> sup = BLOCKS.register(blockName, () ->
                    new DropExperienceBlock(
                        BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .requiresCorrectToolForDrops()
                            .strength(hardness, resistance)
                            .sound(SoundType.STONE),
                        UniformInt.of(minXp, maxXp)
                    )
                );
                byStone.put(stone.name(), sup);
                ALL_ORE_BLOCKS.put(blockName, sup);

                String dsBlockName = "deepslate_" + stone.name() + "_" + ore.name() + "_ore";
                RegistrySupplier<Block> dsSup = BLOCKS.register(dsBlockName, () ->
                    new DropExperienceBlock(
                        BlockBehaviour.Properties.of()
                            .mapColor(MapColor.DEEPSLATE)
                            .requiresCorrectToolForDrops()
                            .strength(hardness, resistance)
                            .sound(SoundType.DEEPSLATE),
                        UniformInt.of(minXp, maxXp)
                    )
                );
                byStone.put("deepslate_" + stone.name(), dsSup);
                ALL_ORE_BLOCKS.put(dsBlockName, dsSup);
            }
            ORE_BLOCKS.put(ore.name(), byStone);
        }
        BLOCKS.register();
    }
}
