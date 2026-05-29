package net.alkeari.geostrata.init;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.alkeari.geostrata.GeoStrata;
import net.alkeari.geostrata.ore.OreType;
import net.alkeari.geostrata.ore.OreTypes;
import net.alkeari.geostrata.stone.StoneType;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

public final class GeoStrataCreativeTabs {
    private GeoStrataCreativeTabs() {}

    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(GeoStrata.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> GEOSTRATA_TAB = CREATIVE_TABS.register(
        GeoStrata.MOD_ID,
        () -> CreativeTabRegistry.create(
            Component.translatable("itemGroup." + GeoStrata.MOD_ID + "." + GeoStrata.MOD_ID),
            () -> new ItemStack(blockItem(GeoStrataBlocks.BASE.get("granite")))
        )
    );

    public static void init() {
        CREATIVE_TABS.register();

        // RegistrySupplier extends DeferredSupplier — pass directly to modify()
        CreativeTabRegistry.modify(GEOSTRATA_TAB, (params, output, hasPermissions) -> {
            for (StoneType type : StoneTypes.ALL) {
                String n = type.name();
                output.accept(blockItem(GeoStrataBlocks.BASE.get(n)));
                output.accept(blockItem(GeoStrataBlocks.SLABS.get(n)));
                output.accept(blockItem(GeoStrataBlocks.STAIRS.get(n)));
                output.accept(blockItem(GeoStrataBlocks.WALLS.get(n)));
                output.accept(blockItem(GeoStrataBlocks.BRICKS.get(n)));
                output.accept(blockItem(GeoStrataBlocks.POLISHED.get(n)));
                output.accept(blockItem(GeoStrataBlocks.COBBLED.get(n)));
                output.accept(blockItem(GeoStrataBlocks.COBBLED_SLABS.get(n)));
                output.accept(blockItem(GeoStrataBlocks.COBBLED_STAIRS.get(n)));
                output.accept(blockItem(GeoStrataBlocks.COBBLED_WALLS.get(n)));
                for (OreType ore : OreTypes.ALL) {
                    output.accept(blockItem(GeoStrataOreBlocks.ORE_BLOCKS.get(ore.name()).get(n)));
                }
            }
            for (StoneType type : StoneTypes.ALL) {
                String n = type.name();
                output.accept(blockItem(GeoStrataBlocks.DEEPSLATE.get(n)));
                for (OreType ore : OreTypes.ALL) {
                    output.accept(blockItem(GeoStrataOreBlocks.ORE_BLOCKS.get(ore.name()).get("deepslate_" + n)));
                }
            }
        });

        CreativeTabRegistry.modify(
            CreativeTabRegistry.defer(CreativeModeTabs.NATURAL_BLOCKS),
            (params, output, hasPermissions) -> {
                for (StoneType type : StoneTypes.ALL) {
                    output.accept(blockItem(GeoStrataBlocks.BASE.get(type.name())));
                    output.accept(blockItem(GeoStrataBlocks.DEEPSLATE.get(type.name())));
                }
            }
        );
    }

    private static Item blockItem(RegistrySupplier<Block> block) {
        return Objects.requireNonNull(Item.BY_BLOCK.get(block.get()));
    }
}
