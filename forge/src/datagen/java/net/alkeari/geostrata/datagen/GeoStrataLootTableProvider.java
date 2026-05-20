package net.alkeari.geostrata.datagen;

import net.alkeari.geostrata.init.GeoStrataBlocks;
import net.alkeari.geostrata.init.GeoStrataOreBlocks;
import net.alkeari.geostrata.ore.OreType;
import net.alkeari.geostrata.ore.OreTypes;
import net.alkeari.geostrata.stone.StoneType;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GeoStrataLootTableProvider extends LootTableProvider {

    public GeoStrataLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(
            new SubProviderEntry(GeoStrataBlockLoot::new, LootContextParamSets.BLOCK)
        ));
    }

    private static class GeoStrataBlockLoot extends BlockLootSubProvider {

        protected GeoStrataBlockLoot() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            // Base stone: silk touch → stone, otherwise → cobbled variant
            for (StoneType stone : StoneTypes.ALL) {
                Block base    = GeoStrataBlocks.BASE.get(stone.name()).get();
                Block cobbled = GeoStrataBlocks.COBBLED.get(stone.name()).get();
                add(base, createSingleItemTableWithSilkTouch(base, cobbled));
            }
            // All other stone blocks (slabs, stairs, walls, bricks, polished,
            // deepslate, cobbled variants): drop self
            Set<Block> handled = new java.util.HashSet<>();
            StoneTypes.ALL.forEach(t -> handled.add(GeoStrataBlocks.BASE.get(t.name()).get()));
            for (var sup : GeoStrataBlocks.ALL_BLOCKS.values()) {
                Block block = sup.get();
                if (!handled.contains(block)) dropSelf(block);
            }
            // Ore blocks
            for (OreType ore : OreTypes.ALL) {
                for (var stoneEntry : GeoStrataOreBlocks.ORE_BLOCKS.get(ore.name()).entrySet()) {
                    Block block = stoneEntry.getValue().get();
                    add(block, createOreDrop(block, ore.dropItem().get()));
                }
            }
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            List<Block> all = new ArrayList<>();
            GeoStrataBlocks.ALL_BLOCKS.values().forEach(s -> all.add(s.get()));
            GeoStrataOreBlocks.ALL_ORE_BLOCKS.values().forEach(s -> all.add(s.get()));
            return all;
        }
    }
}
