package net.alkeari.geostrata.datagen;

import net.alkeari.geostrata.GeoStrata;
import net.alkeari.geostrata.init.GeoStrataBlocks;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GeoStrataItemTagsProvider extends ItemTagsProvider {

    public GeoStrataItemTagsProvider(PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookup,
            CompletableFuture<TagsProvider.TagLookup<Block>> blockTags,
            ExistingFileHelper efh) {
        super(output, lookup, blockTags, GeoStrata.MOD_ID, efh);
    }

    private static final TagKey<Item> NEOFORGE_COBBLESTONE =
        ItemTags.create(ResourceLocation.fromNamespaceAndPath("neoforge", "cobblestone"));
    private static final TagKey<Item> NEOFORGE_STONE =
        ItemTags.create(ResourceLocation.fromNamespaceAndPath("neoforge", "stone"));

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        Item[] cobbledItems = StoneTypes.ALL.stream()
            .map(t -> GeoStrataBlocks.COBBLED.get(t.name()).get().asItem())
            .toArray(Item[]::new);
        tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbledItems);
        tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbledItems);
        tag(NEOFORGE_COBBLESTONE).add(cobbledItems);

        Item[] baseItems = StoneTypes.ALL.stream()
            .map(t -> GeoStrataBlocks.BASE.get(t.name()).get().asItem())
            .toArray(Item[]::new);
        tag(NEOFORGE_STONE).add(baseItems);
    }
}
