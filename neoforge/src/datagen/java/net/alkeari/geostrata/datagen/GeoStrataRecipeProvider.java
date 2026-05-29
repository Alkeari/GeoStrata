package net.alkeari.geostrata.datagen;

import net.alkeari.geostrata.GeoStrata;
import net.alkeari.geostrata.init.GeoStrataBlocks;
import net.alkeari.geostrata.stone.StoneType;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class GeoStrataRecipeProvider extends RecipeProvider {

    public GeoStrataRecipeProvider(PackOutput output, CompletableFuture<net.minecraft.core.HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput out) {
        for (StoneType type : StoneTypes.ALL) {
            buildType(out, type);
        }
    }

    private void buildType(RecipeOutput out, StoneType type) {
        String n          = type.name();
        Item   base       = GeoStrataBlocks.BASE.get(n).get().asItem();
        Item   slab       = GeoStrataBlocks.SLABS.get(n).get().asItem();
        Item   stair      = GeoStrataBlocks.STAIRS.get(n).get().asItem();
        Item   wall       = GeoStrataBlocks.WALLS.get(n).get().asItem();
        Item   brick      = GeoStrataBlocks.BRICKS.get(n).get().asItem();
        Item   brickSlab  = GeoStrataBlocks.BRICKS_SLABS.get(n).get().asItem();
        Item   brickStair = GeoStrataBlocks.BRICKS_STAIRS.get(n).get().asItem();
        Item   brickWall  = GeoStrataBlocks.BRICKS_WALLS.get(n).get().asItem();
        Item   pol        = GeoStrataBlocks.POLISHED.get(n).get().asItem();
        Item   polSlab    = GeoStrataBlocks.POLISHED_SLABS.get(n).get().asItem();
        Item   polStair   = GeoStrataBlocks.POLISHED_STAIRS.get(n).get().asItem();
        Item   polWall    = GeoStrataBlocks.POLISHED_WALLS.get(n).get().asItem();
        Item   cobbled    = GeoStrataBlocks.COBBLED.get(n).get().asItem();
        Item   cobSlab    = GeoStrataBlocks.COBBLED_SLABS.get(n).get().asItem();
        Item   cobStair   = GeoStrataBlocks.COBBLED_STAIRS.get(n).get().asItem();
        Item   cobWall    = GeoStrataBlocks.COBBLED_WALLS.get(n).get().asItem();

        // ── Shaped: base stone ────────────────────────────────────────────────
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, 6)
            .pattern("XXX").define('X', base)
            .unlockedBy("has_" + n, has(base))
            .save(out, rl(n + "_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stair, 4)
            .pattern("X  ").pattern("XX ").pattern("XXX").define('X', base)
            .unlockedBy("has_" + n, has(base))
            .save(out, rl(n + "_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wall, 6)
            .pattern("XXX").pattern("XXX").define('X', base)
            .unlockedBy("has_" + n, has(base))
            .save(out, rl(n + "_wall"));

        // ── Shaped: polished (2×2 of base) ───────────────────────────────────
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, pol, 4)
            .pattern("XX").pattern("XX").define('X', base)
            .unlockedBy("has_" + n, has(base))
            .save(out, rl("polished_" + n));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polSlab, 6)
            .pattern("XXX").define('X', pol)
            .unlockedBy("has_polished_" + n, has(pol))
            .save(out, rl("polished_" + n + "_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polStair, 4)
            .pattern("X  ").pattern("XX ").pattern("XXX").define('X', pol)
            .unlockedBy("has_polished_" + n, has(pol))
            .save(out, rl("polished_" + n + "_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, polWall, 6)
            .pattern("XXX").pattern("XXX").define('X', pol)
            .unlockedBy("has_polished_" + n, has(pol))
            .save(out, rl("polished_" + n + "_wall"));

        // ── Shaped: bricks (2×2 of polished) ─────────────────────────────────
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, brick, 4)
            .pattern("XX").pattern("XX").define('X', pol)
            .unlockedBy("has_polished_" + n, has(pol))
            .save(out, rl(n + "_bricks"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, brickSlab, 6)
            .pattern("XXX").define('X', brick)
            .unlockedBy("has_" + n + "_bricks", has(brick))
            .save(out, rl(n + "_bricks_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, brickStair, 4)
            .pattern("X  ").pattern("XX ").pattern("XXX").define('X', brick)
            .unlockedBy("has_" + n + "_bricks", has(brick))
            .save(out, rl(n + "_bricks_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, brickWall, 6)
            .pattern("XXX").pattern("XXX").define('X', brick)
            .unlockedBy("has_" + n + "_bricks", has(brick))
            .save(out, rl(n + "_bricks_wall"));

        // ── Shaped: cobbled variants ──────────────────────────────────────────
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, cobSlab, 6)
            .pattern("XXX").define('X', cobbled)
            .unlockedBy("has_cobbled_" + n, has(cobbled))
            .save(out, rl("cobbled_" + n + "_slab"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, cobStair, 4)
            .pattern("X  ").pattern("XX ").pattern("XXX").define('X', cobbled)
            .unlockedBy("has_cobbled_" + n, has(cobbled))
            .save(out, rl("cobbled_" + n + "_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, cobWall, 6)
            .pattern("XXX").pattern("XXX").define('X', cobbled)
            .unlockedBy("has_cobbled_" + n, has(cobbled))
            .save(out, rl("cobbled_" + n + "_wall"));

        // ── Smelting: cobbled → base ──────────────────────────────────────────
        SimpleCookingRecipeBuilder
            .smelting(Ingredient.of(cobbled), RecipeCategory.BUILDING_BLOCKS, base, 0.1f, 200)
            .unlockedBy("has_cobbled_" + n, has(cobbled))
            .save(out, rl(n + "_from_smelting"));

        SimpleCookingRecipeBuilder
            .blasting(Ingredient.of(cobbled), RecipeCategory.BUILDING_BLOCKS, base, 0.1f, 100)
            .unlockedBy("has_cobbled_" + n, has(cobbled))
            .save(out, rl(n + "_from_blasting"));

        // ── Stonecutter from base ─────────────────────────────────────────────
        cut(out, base, slab,       2, n + "_slab_sc");
        cut(out, base, stair,      1, n + "_stairs_sc");
        cut(out, base, wall,       1, n + "_wall_sc");
        cut(out, base, cobbled,    1, "cobbled_" + n + "_sc");
        cut(out, base, pol,        1, "polished_" + n + "_sc");
        cut(out, base, polSlab,    2, "polished_" + n + "_slab_sc");
        cut(out, base, polStair,   1, "polished_" + n + "_stairs_sc");
        cut(out, base, polWall,    1, "polished_" + n + "_wall_sc");
        cut(out, base, brick,      1, n + "_bricks_sc");
        cut(out, base, brickSlab,  2, n + "_bricks_slab_sc");
        cut(out, base, brickStair, 1, n + "_bricks_stairs_sc");
        cut(out, base, brickWall,  1, n + "_bricks_wall_sc");

        // ── Stonecutter from polished ─────────────────────────────────────────
        cut(out, pol, polSlab,    2, "polished_" + n + "_slab_from_pol_sc");
        cut(out, pol, polStair,   1, "polished_" + n + "_stairs_from_pol_sc");
        cut(out, pol, polWall,    1, "polished_" + n + "_wall_from_pol_sc");
        cut(out, pol, brick,      1, n + "_bricks_from_pol_sc");
        cut(out, pol, brickSlab,  2, n + "_bricks_slab_from_pol_sc");
        cut(out, pol, brickStair, 1, n + "_bricks_stairs_from_pol_sc");
        cut(out, pol, brickWall,  1, n + "_bricks_wall_from_pol_sc");

        // ── Stonecutter from bricks ───────────────────────────────────────────
        cut(out, brick, brickSlab,  2, n + "_bricks_slab_from_brick_sc");
        cut(out, brick, brickStair, 1, n + "_bricks_stairs_from_brick_sc");
        cut(out, brick, brickWall,  1, n + "_bricks_wall_from_brick_sc");

        // ── Stonecutter from cobbled ──────────────────────────────────────────
        cut(out, cobbled, cobSlab,  2, "cobbled_" + n + "_slab_from_cob_sc");
        cut(out, cobbled, cobStair, 1, "cobbled_" + n + "_stairs_from_cob_sc");
        cut(out, cobbled, cobWall,  1, "cobbled_" + n + "_wall_from_cob_sc");
    }

    private void cut(RecipeOutput out, Item input, Item result, int count, String id) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, result, count)
            .unlockedBy("has_input", has(input))
            .save(out, rl(id));
    }

    private static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(GeoStrata.MOD_ID, path);
    }
}
