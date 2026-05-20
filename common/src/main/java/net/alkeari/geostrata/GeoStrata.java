package net.alkeari.geostrata;

import net.alkeari.geostrata.init.GeoStrataBlocks;
import net.alkeari.geostrata.init.GeoStrataCreativeTabs;
import net.alkeari.geostrata.init.GeoStrataItems;
import net.alkeari.geostrata.init.GeoStrataOreBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeoStrata {
    public static final String MOD_ID = "geostrata";
    public static final Logger LOGGER = LoggerFactory.getLogger("GeoStrata");

    public static void init() {
        GeoStrataBlocks.init();
        GeoStrataOreBlocks.init();
        GeoStrataItems.init();
        GeoStrataCreativeTabs.init();
        LOGGER.info("{} initialized — {} blocks registered.", MOD_ID,
            GeoStrataBlocks.ALL_BLOCKS.size());
    }
}
