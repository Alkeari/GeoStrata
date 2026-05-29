package net.alkeari.geostrata.datagen;

import net.alkeari.geostrata.GeoStrata;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = GeoStrata.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class GeoStrataDatagen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        var efh = event.getExistingFileHelper();
        CompletableFuture<net.minecraft.core.HolderLookup.Provider> lookup =
            event.getLookupProvider();

        gen.addProvider(event.includeClient(), new GeoStrataBlockStateProvider(output, efh));
        gen.addProvider(event.includeServer(), new GeoStrataLootTableProvider(output, lookup));
        gen.addProvider(event.includeServer(), new GeoStrataRecipeProvider(output, lookup));
        GeoStrataTagsProvider blockTags = new GeoStrataTagsProvider(output, lookup, efh);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new GeoStrataItemTagsProvider(output, lookup, blockTags.contentsGetter(), efh));
        gen.addProvider(event.includeClient(), new GeoStrataLanguageProvider(output, "en_us"));
    }
}
