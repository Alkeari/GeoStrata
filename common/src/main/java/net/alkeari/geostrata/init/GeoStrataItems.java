package net.alkeari.geostrata.init;

import dev.architectury.registry.registries.DeferredRegister;
import net.alkeari.geostrata.GeoStrata;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class GeoStrataItems {
    private GeoStrataItems() {}

    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(GeoStrata.MOD_ID, Registries.ITEM);

    public static void init() {
        GeoStrataBlocks.ALL_BLOCKS.forEach((name, blockSupplier) ->
            ITEMS.register(name, () -> new BlockItem(blockSupplier.get(), new Item.Properties()))
        );
        GeoStrataOreBlocks.ALL_ORE_BLOCKS.forEach((name, blockSupplier) ->
            ITEMS.register(name, () -> new BlockItem(blockSupplier.get(), new Item.Properties()))
        );
        ITEMS.register();
    }
}
