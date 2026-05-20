package net.alkeari.geostrata.datagen;

import net.alkeari.geostrata.GeoStrata;
import net.alkeari.geostrata.init.GeoStrataBlocks;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GeoStrataItemTagsProvider extends ItemTagsProvider {

    public GeoStrataItemTagsProvider(PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookup,
            CompletableFuture<TagsProvider.TagLookup<Block>> blockTags,
            ExistingFileHelper efh) {
        super(output, lookup, blockTags, GeoStrata.MOD_ID, efh);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Cobbled stone can be used to craft stone-tier tools
        Item[] cobbledItems = StoneTypes.ALL.stream()
            .map(t -> GeoStrataBlocks.COBBLED.get(t.name()).get().asItem())
            .toArray(Item[]::new);
        tag(ItemTags.STONE_TOOL_MATERIALS).add(cobbledItems);

        // Cobbled stone fills the cobblestone role in stone_crafting_materials
        tag(ItemTags.STONE_CRAFTING_MATERIALS).add(cobbledItems);
    }
}
