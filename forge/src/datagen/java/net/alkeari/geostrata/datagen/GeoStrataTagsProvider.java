package net.alkeari.geostrata.datagen;

import net.alkeari.geostrata.GeoStrata;
import net.alkeari.geostrata.init.GeoStrataBlocks;
import net.alkeari.geostrata.init.GeoStrataOreBlocks;
import net.alkeari.geostrata.ore.OreType;
import net.alkeari.geostrata.ore.OreTypes;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import dev.architectury.registry.registries.RegistrySupplier;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class GeoStrataTagsProvider extends BlockTagsProvider {

    private static final TagKey<Block> FORGE_COBBLESTONE =
        BlockTags.create(new ResourceLocation("forge", "cobblestone"));
    private static final TagKey<Block> FORGE_STONE =
        BlockTags.create(new ResourceLocation("forge", "stone"));

    public GeoStrataTagsProvider(PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper efh) {
        super(output, lookup, GeoStrata.MOD_ID, efh);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        Block[] baseBlocks = StoneTypes.ALL.stream()
            .map(t -> GeoStrataBlocks.BASE.get(t.name()).get())
            .toArray(Block[]::new);
        tag(BlockTags.STONE_ORE_REPLACEABLES).add(baseBlocks);
        tag(BlockTags.BASE_STONE_OVERWORLD).add(baseBlocks);
        tag(FORGE_STONE).add(baseBlocks);

        Block[] cobbledBlocks = StoneTypes.ALL.stream()
            .map(t -> GeoStrataBlocks.COBBLED.get(t.name()).get())
            .toArray(Block[]::new);
        tag(FORGE_COBBLESTONE).add(cobbledBlocks);

        Block[] deepslateBlocks = StoneTypes.ALL.stream()
            .map(t -> GeoStrataBlocks.DEEPSLATE.get(t.name()).get())
            .toArray(Block[]::new);
        tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES).add(deepslateBlocks);
        tag(BlockTags.BASE_STONE_OVERWORLD).add(deepslateBlocks);

        Block[] allStoneBlocks = GeoStrataBlocks.ALL_BLOCKS.values().stream()
            .map(s -> s.get())
            .toArray(Block[]::new);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(allStoneBlocks);

        tag(BlockTags.SLABS).add(blocksFrom(GeoStrataBlocks.SLABS));
        tag(BlockTags.SLABS).add(blocksFrom(GeoStrataBlocks.COBBLED_SLABS));
        tag(BlockTags.SLABS).add(blocksFrom(GeoStrataBlocks.BRICKS_SLABS));
        tag(BlockTags.SLABS).add(blocksFrom(GeoStrataBlocks.POLISHED_SLABS));

        tag(BlockTags.STAIRS).add(blocksFrom(GeoStrataBlocks.STAIRS));
        tag(BlockTags.STAIRS).add(blocksFrom(GeoStrataBlocks.COBBLED_STAIRS));
        tag(BlockTags.STAIRS).add(blocksFrom(GeoStrataBlocks.BRICKS_STAIRS));
        tag(BlockTags.STAIRS).add(blocksFrom(GeoStrataBlocks.POLISHED_STAIRS));

        tag(BlockTags.WALLS).add(blocksFrom(GeoStrataBlocks.WALLS));
        tag(BlockTags.WALLS).add(blocksFrom(GeoStrataBlocks.COBBLED_WALLS));
        tag(BlockTags.WALLS).add(blocksFrom(GeoStrataBlocks.BRICKS_WALLS));
        tag(BlockTags.WALLS).add(blocksFrom(GeoStrataBlocks.POLISHED_WALLS));

        Block[] needsStone = StoneTypes.ALL.stream()
            .filter(t -> t.toolLevel() >= 1)
            .map(t -> GeoStrataBlocks.BASE.get(t.name()).get())
            .toArray(Block[]::new);
        if (needsStone.length > 0) {
            tag(BlockTags.NEEDS_STONE_TOOL).add(needsStone);
        }

        // Ore blocks: mineable with pickaxe
        Block[] allOreBlocks = GeoStrataOreBlocks.ALL_ORE_BLOCKS.values().stream()
            .map(s -> s.get())
            .toArray(Block[]::new);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(allOreBlocks);

        // Stone-tool ores (coal, iron, copper, lapis)
        Block[] stoneToolOres = OreTypes.ALL.stream()
            .filter(o -> o.toolLevel() == 1)
            .flatMap(o -> StoneTypes.ALL.stream()
                .map(s -> GeoStrataOreBlocks.ORE_BLOCKS.get(o.name()).get(s.name()).get()))
            .toArray(Block[]::new);
        if (stoneToolOres.length > 0) {
            tag(BlockTags.NEEDS_STONE_TOOL).add(stoneToolOres);
        }

        // Iron-tool ores (gold, redstone, diamond, emerald)
        Block[] ironToolOres = OreTypes.ALL.stream()
            .filter(o -> o.toolLevel() == 2)
            .flatMap(o -> StoneTypes.ALL.stream()
                .map(s -> GeoStrataOreBlocks.ORE_BLOCKS.get(o.name()).get(s.name()).get()))
            .toArray(Block[]::new);
        if (ironToolOres.length > 0) {
            tag(BlockTags.NEEDS_IRON_TOOL).add(ironToolOres);
        }

        // Deepslate ore tool-level tags
        Block[] dsStoneToolOres = OreTypes.ALL.stream()
            .filter(o -> o.toolLevel() == 1)
            .flatMap(o -> StoneTypes.ALL.stream()
                .map(s -> GeoStrataOreBlocks.ORE_BLOCKS.get(o.name()).get("deepslate_" + s.name()).get()))
            .toArray(Block[]::new);
        if (dsStoneToolOres.length > 0) {
            tag(BlockTags.NEEDS_STONE_TOOL).add(dsStoneToolOres);
        }

        Block[] dsIronToolOres = OreTypes.ALL.stream()
            .filter(o -> o.toolLevel() == 2)
            .flatMap(o -> StoneTypes.ALL.stream()
                .map(s -> GeoStrataOreBlocks.ORE_BLOCKS.get(o.name()).get("deepslate_" + s.name()).get()))
            .toArray(Block[]::new);
        if (dsIronToolOres.length > 0) {
            tag(BlockTags.NEEDS_IRON_TOOL).add(dsIronToolOres);
        }
    }

    private static Block[] blocksFrom(Map<String, RegistrySupplier<Block>> map) {
        return StoneTypes.ALL.stream()
            .map(t -> map.get(t.name()).get())
            .toArray(Block[]::new);
    }
}
