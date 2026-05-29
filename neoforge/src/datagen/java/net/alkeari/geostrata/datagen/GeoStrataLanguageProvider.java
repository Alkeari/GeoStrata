package net.alkeari.geostrata.datagen;

import net.alkeari.geostrata.GeoStrata;
import net.alkeari.geostrata.init.GeoStrataBlocks;
import net.alkeari.geostrata.init.GeoStrataOreBlocks;
import net.alkeari.geostrata.ore.OreType;
import net.alkeari.geostrata.ore.OreTypes;
import net.alkeari.geostrata.stone.StoneType;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class GeoStrataLanguageProvider extends LanguageProvider {

    public GeoStrataLanguageProvider(PackOutput output, String locale) {
        super(output, GeoStrata.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.geostrata.geostrata", "GeoStrata");
        for (StoneType type : StoneTypes.ALL) {
            addStoneType(type);
        }
        for (StoneType type : StoneTypes.ALL) {
            String displayName = "Deepslate " + toDisplayName(type.name());
            add(GeoStrataBlocks.DEEPSLATE.get(type.name()).get(), displayName);
        }
        for (OreType ore : OreTypes.ALL) {
            for (StoneType stone : StoneTypes.ALL) {
                String displayName = toDisplayName(stone.name()) + " " + ore.displayName() + " Ore";
                add(GeoStrataOreBlocks.ORE_BLOCKS.get(ore.name()).get(stone.name()).get(), displayName);
            }
        }
        for (OreType ore : OreTypes.ALL) {
            for (StoneType stone : StoneTypes.ALL) {
                String displayName = "Deepslate " + toDisplayName(stone.name()) + " " + ore.displayName() + " Ore";
                add(GeoStrataOreBlocks.ORE_BLOCKS.get(ore.name()).get("deepslate_" + stone.name()).get(), displayName);
            }
        }
    }

    private void addStoneType(StoneType type) {
        String n           = type.name();
        String displayName = toDisplayName(n);

        add(GeoStrataBlocks.BASE.get(n).get(),            displayName);
        add(GeoStrataBlocks.SLABS.get(n).get(),           displayName + " Slab");
        add(GeoStrataBlocks.STAIRS.get(n).get(),          displayName + " Stairs");
        add(GeoStrataBlocks.WALLS.get(n).get(),           displayName + " Wall");
        add(GeoStrataBlocks.BRICKS.get(n).get(),           displayName + " Bricks");
        add(GeoStrataBlocks.BRICKS_SLABS.get(n).get(),    displayName + " Brick Slab");
        add(GeoStrataBlocks.BRICKS_STAIRS.get(n).get(),   displayName + " Brick Stairs");
        add(GeoStrataBlocks.BRICKS_WALLS.get(n).get(),    displayName + " Brick Wall");
        add(GeoStrataBlocks.POLISHED.get(n).get(),        "Polished " + displayName);
        add(GeoStrataBlocks.POLISHED_SLABS.get(n).get(),  "Polished " + displayName + " Slab");
        add(GeoStrataBlocks.POLISHED_STAIRS.get(n).get(), "Polished " + displayName + " Stairs");
        add(GeoStrataBlocks.POLISHED_WALLS.get(n).get(),  "Polished " + displayName + " Wall");
        add(GeoStrataBlocks.COBBLED.get(n).get(),         "Cobbled " + displayName);
        add(GeoStrataBlocks.COBBLED_SLABS.get(n).get(),   "Cobbled " + displayName + " Slab");
        add(GeoStrataBlocks.COBBLED_STAIRS.get(n).get(),  "Cobbled " + displayName + " Stairs");
        add(GeoStrataBlocks.COBBLED_WALLS.get(n).get(),   "Cobbled " + displayName + " Wall");
    }

    private static String toDisplayName(String registryName) {
        String[] parts = registryName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (!sb.isEmpty()) sb.append(' ');
            sb.append(Character.toUpperCase(part.charAt(0)));
            sb.append(part.substring(1));
        }
        return sb.toString();
    }
}
