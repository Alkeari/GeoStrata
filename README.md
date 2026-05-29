# GeoStrata - Mineralogy Remade
 
**Vanilla stone is one block. GeoStrata makes it 459.**
 
You break a block of stone and you get... stone. Bedrock to surface, every inch of overworld rock is the same gray cube. GeoStrata replaces all of it with a real geological system: 27 distinct rock types organized into depth-based provinces, each with hand-crafted textures, hardness tiers, and a full suite of building blocks.
 
---
 
## A World With Real Geology
 
The underground splits into three depth zones, blended together by noise so the borders feel organic instead of grid-like:
 
- **Igneous** rocks dominate the deeps: granite, basalt, gabbro, peridotite, the hot stuff that cooled from magma far below.
- **Metamorphic** rocks fill the middle layers: slate, schist, gneiss, quartzite, the dense, banded results of heat and pressure on older rock.
- **Sedimentary** rocks line the upper crust: limestone, shale, dolomite, conglomerate, the layered materials that accumulate near the surface.
Twelve igneous, eight metamorphic, seven sedimentary, twenty-seven stones total. Which specific ones you encounter is driven by a second noise layer, so a granite region of igneous deep gradually shifts to gabbro or diorite as you traverse the map.
 
## Mining Has Texture Again
 
Some stones snap under a wooden pickaxe. Granite needs stone. Quartzite needs iron. Basalt, diabase, and gabbro are as tough as deepslate and demand an iron pickaxe or better. Tools wear differently across the world, and the terrain knows what you're holding.
 
## Ores That Live Where They're Found
 
GeoStrata replaces vanilla ore generation entirely. Every (ore, host rock) combination has its own dedicated block with an overlay texture composited onto the surrounding stone. Iron in granite looks different from iron in limestone, even though they drop the same raw iron. Depth ranges follow vanilla exactly (diamond stays in the deeps, lapis at mid-depth, coal everywhere), but the visual texture of every ore tile is now context-aware.
 
All eight vanilla ores are supported, with proper deepslate variants for each. Vanilla iron, copper, gold, lapis, redstone, diamond, emerald, and coal placements are removed in favor of GeoStrata's host-aware versions.
 
## A Building Palette You Can Live In
 
Every one of the 27 stone types ships with 17 block variants:
 
- **Base** (stone, slab, stairs, wall)
- **Cobbled** (stone, slab, stairs, wall, drops when you mine base stone)
- **Polished** (stone, slab, stairs, wall, crafted from base 2x2)
- **Bricks** (block, slab, stairs, wall, crafted from polished 2x2)
- A deepslate-style variant for each stone type
That's **459 building blocks** total. Smelt cobbled into base. Use a stonecutter to go from base directly to any variant, no intermediate steps. Cobbled stone functions exactly like vanilla cobblestone in stone-tier tool recipes, so existing crafting muscle memory still works.
 
The point: you can build a granite manor, a schist cathedral, a limestone amphitheater, or a peridotite fortress without ever resorting to vanilla stone or stacking a half-dozen "more stone" mods to get there.
 
## Works With Your Worldgen
 
GeoStrata runs after vanilla surface generation completes, so terrain shape, biome placement, and surface features all stay vanilla. Worldgen mods like Tectonic, Terralith, and TerraBlender stack on top normally.
 
Ore mods that target the standard `stone_ore_replaceables` and `deepslate_ore_replaceables` tags will place into GeoStrata stones automatically since every GeoStrata stone is tagged into both. Ore mods that hardcode the vanilla `minecraft:stone` blockstate won't see GeoStrata replacements.
 
GeoStrata pairs naturally with the rest of the geo-suite: **GeoGradient** for latitude-driven climate above ground, **Genesis** for previewing seeds before you commit, and **GeoTectonic** for hand-designed cave systems carving through your new strata.
 
## Compatibility
 
GeoStrata is built for **Minecraft 1.20.1** on **Forge** and **Fabric**.
 
## Credits
 
Created by **Alkeari**, inspired by Minecraft Mineralogy by jriwanek.
 
## License
 
[Alkeari License Agreement (ALA v2.2)](https://github.com/Alkeari/GeoStrata/blob/main/LICENSE.md)
 
## Issues & Feedback
 
Found a bug or have a suggestion? Open an issue on [GitHub](https://github.com/Alkeari/GeoStrata/issues).