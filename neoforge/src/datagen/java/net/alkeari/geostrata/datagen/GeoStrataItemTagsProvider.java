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

    private static final TagKey<Item> C_COBBLESTONES_NORMAL =
        ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "cobblestones/normal"));
    private static final TagKey<Item> C_STONES =
        ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "stones"));

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        Item[] cobbledItems = StoneTypes.ALL.stream()
            .map(t -> GeoStrataBlocks.COBBLED.get(t.name()).get().asItem())
            .toArray(Item[]::new);
        tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbledItems);
        tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbledItems);
        tag(C_COBBLESTONES_NORMAL).add(cobbledItems);

        Item[] baseItems = StoneTypes.ALL.stream()
            .map(t -> GeoStrataBlocks.BASE.get(t.name()).get().asItem())
            .toArray(Item[]::new);
        tag(C_STONES).add(baseItems);
    }
}
