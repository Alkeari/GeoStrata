# GeoStrata

GeoStrata replaces the uniform stone layer of your world with a rich, noise-driven geological system. Instead of one type of stone from bedrock to surface, you'll find 27 distinct rock types arranged in realistic geological provinces — igneous rock deep underground, metamorphic rock in the middle layers, and sedimentary rock near the surface. Every one of those stones hosts its own variants of all common ores, and every stone type comes with a full suite of building blocks.

---

## Geological World Generation

The underground is divided into three geological categories, each occupying a different depth range that varies naturally across the world:

| Category | Depth Range | Description |
|---|---|---|
| **Igneous** | Deep underground | Formed from magma; dense, volcanic rocks |
| **Metamorphic** | Mid-layer | Formed under heat and pressure; harder, banded rocks |
| **Sedimentary** | Upper layers | Formed from accumulated material; layered, varied rocks |

The boundaries between these zones shift gradually using noise — no sharp borders. The result is a world where geology feels continuous and organic rather than grid-like.

---

## Stone Types

### Igneous (12 types)

| Stone | Hardness | Min. Tool |
|---|---|---|
| Andesite | 1.5 | Wooden Pickaxe |
| Basalt | 5.0 | Iron Pickaxe |
| Diorite | 1.5 | Wooden Pickaxe |
| Granite | 3.0 | Stone Pickaxe |
| Rhyolite | 1.5 | Wooden Pickaxe |
| Pegmatite | 1.5 | Wooden Pickaxe |
| Diabase | 5.0 | Iron Pickaxe |
| Gabbro | 5.0 | Iron Pickaxe |
| Peridotite | 3.0 | Wooden Pickaxe |
| Basaltic Glass | 3.0 | Wooden Pickaxe |
| Scoria | 1.0 | Wooden Pickaxe |
| Tuff | 2.0 | Wooden Pickaxe |

### Metamorphic (8 types)

| Stone | Hardness | Min. Tool |
|---|---|---|
| Slate | 1.5 | Wooden Pickaxe |
| Schist | 3.0 | Stone Pickaxe |
| Gneiss | 3.0 | Stone Pickaxe |
| Phyllite | 1.5 | Wooden Pickaxe |
| Amphibolite | 3.0 | Stone Pickaxe |
| Hornfels | 3.0 | Stone Pickaxe |
| Quartzite | 4.0 | Stone Pickaxe |
| Novaculite | 3.0 | Stone Pickaxe |

### Sedimentary (7 types)

| Stone | Hardness | Min. Tool |
|---|---|---|
| Shale | 1.5 | Wooden Pickaxe |
| Conglomerate | 1.5 | Wooden Pickaxe |
| Dolomite | 3.0 | Stone Pickaxe |
| Limestone | 1.5 | Wooden Pickaxe |
| Marble | 1.5 | Wooden Pickaxe |
| Siltstone | 1.0 | Wooden Pickaxe |
| Rock Salt | 1.5 | Wooden Pickaxe |

> Basalt, Diabase, and Gabbro are the hardest stones in the mod, with explosion resistance equivalent to deepslate. They require an iron pickaxe or better to mine.

---

## Block Variants

Every stone type comes with **17 block variants**, giving you a complete palette of building materials:

| Variant Group | Blocks |
|---|---|
| **Base** | Stone, Slab, Stairs, Wall |
| **Cobbled** | Cobbled Stone, Cobbled Slab, Cobbled Stairs, Cobbled Wall |
| **Bricks** | Stone Bricks, Brick Slab, Brick Stairs, Brick Wall |
| **Polished** | Polished Stone, Polished Slab, Polished Stairs, Polished Wall |
| **Deepslate** | Deepslate variant (deepslate layer equivalent) |

That's **459 stone blocks** in total across all types.

### Crafting Chains

The four variant groups progress naturally from one to the next:

- **Base stone** is obtained by smelting or blasting the cobbled variant
- **Cobbled** drops when mining base stone without Silk Touch
- **Polished** is crafted from base stone (2×2 → 4 blocks)
- **Bricks** are crafted from polished stone (2×2 → 4 blocks)
- Slabs, stairs, and walls follow standard vanilla recipes for their respective tier
- A **stonecutter** can convert base stone directly into any variant, including across tiers — no intermediate steps required

Cobbled stone functions as a crafting material in the same role as vanilla cobblestone for stone-tier tools and related recipes.

---

## Ore System

All 8 vanilla ores generate inside GeoStrata stone instead of plain vanilla stone. Every ore type appears in every stone type, with ore appearance matching the host rock — each ore has an overlay texture composited on top of the surrounding stone, so iron ore in granite looks visually distinct from iron ore in limestone.

| Ore | Depth Range | Drops |
|---|---|---|
| Coal | -60 to 192 | Coal |
| Iron | -60 to 72 | Raw Iron |
| Copper | -16 to 112 | Raw Copper |
| Gold | -64 to 32 | Raw Gold |
| Redstone | -64 to 15 | Redstone |
| Lapis | -32 to 64 | Lapis Lazuli |
| Diamond | -64 to 16 | Diamond |
| Emerald | -16 to 480 | Emerald |

Deepslate variants of each ore also generate at the appropriate depths, hosted by GeoStrata deepslate blocks rather than vanilla deepslate.

Vanilla iron, copper, coal, gold, and other ores no longer generate in plain stone — all ore generation runs through GeoStrata's system.

---

## Compatibility

- **Minecraft:** 1.20.1
- **Forge:** 47.4.x
- **Fabric:** 0.14.x (via Architectury)

GeoStrata runs on both Forge and Fabric from a single jar distributed per platform. No additional dependencies are required beyond Architectury API.

---

## Credits

Created by **Alkeari**
