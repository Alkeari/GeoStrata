package net.alkeari.geostrata.forge;

import dev.architectury.platform.forge.EventBuses;
import net.alkeari.geostrata.GeoStrata;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GeoStrata.MOD_ID)
public class GeoStrataForge {
    @SuppressWarnings("removal")
    public GeoStrataForge() {
        EventBuses.registerModEventBus(GeoStrata.MOD_ID,
                FMLJavaModLoadingContext.get().getModEventBus());
        GeoStrata.init();
    }
}
