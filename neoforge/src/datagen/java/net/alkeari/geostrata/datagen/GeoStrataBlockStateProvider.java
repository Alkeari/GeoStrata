package net.alkeari.geostrata.datagen;

import net.alkeari.geostrata.GeoStrata;
import net.alkeari.geostrata.init.GeoStrataBlocks;
import net.alkeari.geostrata.init.GeoStrataOreBlocks;
import net.alkeari.geostrata.ore.OreType;
import net.alkeari.geostrata.ore.OreTypes;
import net.alkeari.geostrata.stone.StoneType;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class GeoStrataBlockStateProvider extends BlockStateProvider {

    public GeoStrataBlockStateProvider(PackOutput output, ExistingFileHelper efh) {
        super(output, GeoStrata.MOD_ID, efh);
    }

    @Override
    protected void registerStatesAndModels() {
        for (StoneType type : StoneTypes.ALL) {
            registerType(type);
            registerCobbledType(type);
        }
        for (StoneType type : StoneTypes.ALL) {
            registerDeepslateStone(type);
        }
        for (OreType ore : OreTypes.ALL) {
            for (StoneType stone : StoneTypes.ALL) {
                registerOre(ore, stone);
                registerDeepslateOre(ore, stone);
            }
        }
    }

    private void registerType(StoneType type) {
        String n = type.name();
        ResourceLocation tex = modLoc("block/" + n);

        Block base = GeoStrataBlocks.BASE.get(n).get();
        simpleBlockWithItem(base, cubeAll(base));

        SlabBlock slab = (SlabBlock) GeoStrataBlocks.SLABS.get(n).get();
        slabBlock(slab, tex, tex);
        simpleBlockItem(slab, models().slab(n + "_slab", tex, tex, tex));

        StairBlock stair = (StairBlock) GeoStrataBlocks.STAIRS.get(n).get();
        stairsBlock(stair, tex);
        simpleBlockItem(stair, models().stairs(n + "_stairs", tex, tex, tex));

        WallBlock wall = (WallBlock) GeoStrataBlocks.WALLS.get(n).get();
        wallBlock(wall, tex);
        itemModels().wallInventory(n + "_wall", tex);

        Block bricks = GeoStrataBlocks.BRICKS.get(n).get();
        simpleBlockWithItem(bricks, cubeAll(bricks));

        ResourceLocation bricksTex = modLoc("block/" + n + "_bricks");

        SlabBlock bricksSlab = (SlabBlock) GeoStrataBlocks.BRICKS_SLABS.get(n).get();
        slabBlock(bricksSlab, bricksTex, bricksTex);
        simpleBlockItem(bricksSlab, models().slab(n + "_bricks_slab", bricksTex, bricksTex, bricksTex));

        StairBlock bricksStairs = (StairBlock) GeoStrataBlocks.BRICKS_STAIRS.get(n).get();
        stairsBlock(bricksStairs, bricksTex);
        simpleBlockItem(bricksStairs, models().stairs(n + "_bricks_stairs", bricksTex, bricksTex, bricksTex));

        WallBlock bricksWall = (WallBlock) GeoStrataBlocks.BRICKS_WALLS.get(n).get();
        wallBlock(bricksWall, bricksTex);
        itemModels().wallInventory(n + "_bricks_wall", bricksTex);

        Block polished = GeoStrataBlocks.POLISHED.get(n).get();
        simpleBlockWithItem(polished, cubeAll(polished));

        ResourceLocation polishedTex = modLoc("block/polished_" + n);

        SlabBlock polishedSlab = (SlabBlock) GeoStrataBlocks.POLISHED_SLABS.get(n).get();
        slabBlock(polishedSlab, polishedTex, polishedTex);
        simpleBlockItem(polishedSlab, models().slab("polished_" + n + "_slab", polishedTex, polishedTex, polishedTex));

        StairBlock polishedStairs = (StairBlock) GeoStrataBlocks.POLISHED_STAIRS.get(n).get();
        stairsBlock(polishedStairs, polishedTex);
        simpleBlockItem(polishedStairs, models().stairs("polished_" + n + "_stairs", polishedTex, polishedTex, polishedTex));

        WallBlock polishedWall = (WallBlock) GeoStrataBlocks.POLISHED_WALLS.get(n).get();
        wallBlock(polishedWall, polishedTex);
        itemModels().wallInventory("polished_" + n + "_wall", polishedTex);
    }

    private void registerCobbledType(StoneType type) {
        String n   = type.name();
        ResourceLocation tex = modLoc("block/cobbled_" + n);

        Block cob = GeoStrataBlocks.COBBLED.get(n).get();
        simpleBlockWithItem(cob, cubeAll(cob));

        SlabBlock cobSlab = (SlabBlock) GeoStrataBlocks.COBBLED_SLABS.get(n).get();
        slabBlock(cobSlab, tex, tex);
        simpleBlockItem(cobSlab, models().slab("cobbled_" + n + "_slab", tex, tex, tex));

        StairBlock cobStair = (StairBlock) GeoStrataBlocks.COBBLED_STAIRS.get(n).get();
        stairsBlock(cobStair, tex);
        simpleBlockItem(cobStair, models().stairs("cobbled_" + n + "_stairs", tex, tex, tex));

        WallBlock cobWall = (WallBlock) GeoStrataBlocks.COBBLED_WALLS.get(n).get();
        wallBlock(cobWall, tex);
        itemModels().wallInventory("cobbled_" + n + "_wall", tex);
    }

    private void registerDeepslateStone(StoneType stone) {
        Block block = GeoStrataBlocks.DEEPSLATE.get(stone.name()).get();
        simpleBlockWithItem(block, cubeAll(block));
    }

    private void registerDeepslateOre(OreType ore, StoneType stone) {
        String name = "deepslate_" + stone.name() + "_" + ore.name() + "_ore";
        ResourceLocation stoneTex = modLoc("block/deepslate_" + stone.name());
        ResourceLocation oreTex   = modLoc("block/" + ore.overlayTexture());

        BlockModelBuilder model = models()
            .withExistingParent(name, modLoc("block/ore_block"))
            .texture("stone", stoneTex)
            .texture("ore", oreTex);

        Block block = GeoStrataOreBlocks.ORE_BLOCKS.get(ore.name()).get("deepslate_" + stone.name()).get();
        simpleBlockWithItem(block, model);
    }

    private void registerOre(OreType ore, StoneType stone) {
        String name = stone.name() + "_" + ore.name() + "_ore";
        ResourceLocation stoneTex = modLoc("block/" + stone.name());
        ResourceLocation oreTex   = modLoc("block/" + ore.overlayTexture());

        BlockModelBuilder model = models()
            .withExistingParent(name, modLoc("block/ore_block"))
            .texture("stone", stoneTex)
            .texture("ore", oreTex);

        Block block = GeoStrataOreBlocks.ORE_BLOCKS.get(ore.name()).get(stone.name()).get();
        simpleBlockWithItem(block, model);
    }
}
