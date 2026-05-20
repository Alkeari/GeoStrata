package net.alkeari.geostrata.fabric;

import net.alkeari.geostrata.GeoStrata;
import net.alkeari.geostrata.ore.OreType;
import net.alkeari.geostrata.ore.OreTypes;
import net.alkeari.geostrata.stone.StoneType;
import net.alkeari.geostrata.stone.StoneTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.List;

public class GeoStrataFabric implements ModInitializer {

    private static final List<String> FEATURES_TO_REMOVE = List.of(
        // vanilla stone blobs
        "ore_granite_lower", "ore_granite_upper",
        "ore_diorite_lower", "ore_diorite_upper",
        "ore_andesite_lower", "ore_andesite_upper",
        "ore_tuff",
        // vanilla ores
        "ore_coal_lower", "ore_coal_upper",
        "ore_iron", "ore_iron_middle", "ore_iron_small",
        "ore_copper", "ore_copper_large",
        "ore_gold", "ore_gold_lower", "ore_gold_extra",
        "ore_redstone", "ore_redstone_lower",
        "ore_lapis", "ore_lapis_buried",
        "ore_diamond", "ore_diamond_large", "ore_diamond_buried",
        "ore_emerald"
    );

    @Override
    public void onInitialize() {
        GeoStrata.init();
        removeVanillaStoneOres();
        addGeoStrataStones();
        addGeoStrataOres();
    }

    private static void removeVanillaStoneOres() {
        BiomeModifications.create(new ResourceLocation(GeoStrata.MOD_ID, "remove_vanilla_stone_ores"))
            .add(ModificationPhase.REMOVALS, BiomeSelectors.foundInOverworld(), context -> {
                for (String feature : FEATURES_TO_REMOVE) {
                    context.getGenerationSettings().removeFeature(
                        GenerationStep.Decoration.UNDERGROUND_ORES,
                        ResourceKey.create(Registries.PLACED_FEATURE,
                            new ResourceLocation("minecraft", feature))
                    );
                }
            });
    }

    private static void addGeoStrataStones() {
        BiomeModifications.create(new ResourceLocation(GeoStrata.MOD_ID, "add_geostrata_stones"))
            .add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInOverworld(), context -> {
                for (StoneType stone : StoneTypes.ALL) {
                    context.getGenerationSettings().addFeature(
                        GenerationStep.Decoration.UNDERGROUND_ORES,
                        ResourceKey.create(Registries.PLACED_FEATURE,
                            new ResourceLocation(GeoStrata.MOD_ID, stone.name() + "_stone"))
                    );
                    context.getGenerationSettings().addFeature(
                        GenerationStep.Decoration.UNDERGROUND_ORES,
                        ResourceKey.create(Registries.PLACED_FEATURE,
                            new ResourceLocation(GeoStrata.MOD_ID, "deepslate_" + stone.name() + "_stone"))
                    );
                }
            });
    }

    private static void addGeoStrataOres() {
        BiomeModifications.create(new ResourceLocation(GeoStrata.MOD_ID, "add_geostrata_ores"))
            .add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInOverworld(), context -> {
                for (OreType ore : OreTypes.ALL) {
                    context.getGenerationSettings().addFeature(
                        GenerationStep.Decoration.UNDERGROUND_ORES,
                        ResourceKey.create(Registries.PLACED_FEATURE,
                            new ResourceLocation(GeoStrata.MOD_ID, ore.name() + "_ore"))
                    );
                }
            });
    }
}
