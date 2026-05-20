package net.alkeari.geostrata.worldgen;

import net.alkeari.geostrata.init.GeoStrataBlocks;
import net.alkeari.geostrata.stone.StoneType;
import net.alkeari.geostrata.stone.StoneTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public final class GeoStrataStoneReplacer {
    private GeoStrataStoneReplacer() {}

    private static final double PROVINCE_SCALE  = 1.0 / 512.0;
    private static final double ROCK_SCALE      = 1.0 / 64.0;
    private static final double IGNEOUS_MAX     = -0.2;
    private static final double METAMORPHIC_MAX =  0.2;

    // BlockState arrays per category — populated once after block registration.
    // IGNEOUS_STATES is written last so its volatile write acts as a publish fence.
    private static volatile BlockState[] IGNEOUS_STATES;
    private static volatile BlockState[] METAMORPHIC_STATES;
    private static volatile BlockState[] SEDIMENTARY_STATES;

    private record NoiseState(PerlinSimplexNoise province, PerlinSimplexNoise rock) {}
    private static final ConcurrentHashMap<Long, NoiseState> NOISE_CACHE = new ConcurrentHashMap<>();

    private static void ensureStates() {
        if (IGNEOUS_STATES == null) {
            synchronized (GeoStrataStoneReplacer.class) {
                if (IGNEOUS_STATES == null) {
                    METAMORPHIC_STATES = buildStates(StoneTypes.METAMORPHIC);
                    SEDIMENTARY_STATES = buildStates(StoneTypes.SEDIMENTARY);
                    IGNEOUS_STATES     = buildStates(StoneTypes.IGNEOUS);
                }
            }
        }
    }

    private static BlockState[] buildStates(List<StoneType> types) {
        return types.stream()
            .map(t -> GeoStrataBlocks.BASE.get(t.name()).get().defaultBlockState())
            .toArray(BlockState[]::new);
    }

    private static NoiseState getNoiseState(long seed) {
        return NOISE_CACHE.computeIfAbsent(seed, s -> new NoiseState(
            new PerlinSimplexNoise(
                new WorldgenRandom(new LegacyRandomSource(s ^ 0x5F3759DFL)),
                IntStream.rangeClosed(-4, 0).boxed().toList()
            ),
            new PerlinSimplexNoise(
                new WorldgenRandom(new LegacyRandomSource(s ^ 0xCAFEBABEL)),
                IntStream.rangeClosed(-2, 0).boxed().toList()
            )
        ));
    }

    public static void replaceStone(ChunkAccess chunk, long seed) {
        ensureStates();
        NoiseState noise  = getNoiseState(seed);
        int startX        = chunk.getPos().getMinBlockX();
        int startZ        = chunk.getPos().getMinBlockZ();
        int minY          = chunk.getMinBuildHeight();
        int maxY          = chunk.getMaxBuildHeight();
        BlockState stone  = Blocks.STONE.defaultBlockState();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int dx = 0; dx < 16; dx++) {
            int x = startX + dx;
            for (int dz = 0; dz < 16; dz++) {
                int z = startZ + dz;

                double provinceVal = noise.province().getValue(x * PROVINCE_SCALE, z * PROVINCE_SCALE, false);
                double rockVal     = noise.rock().getValue(x * ROCK_SCALE, z * ROCK_SCALE, false);

                // Precompute category Y thresholds — avoids (y - 48) / 200 per block
                double igneousMaxY     = 48.0 + 200.0 * (IGNEOUS_MAX     - provinceVal);
                double metamorphicMaxY = 48.0 + 200.0 * (METAMORPHIC_MAX - provinceVal);

                // Precompute rock-type index per category — normalised is fixed per column
                double normalised = (rockVal + 1.0) / 2.0;
                int indexI = Math.max(0, Math.min(IGNEOUS_STATES.length     - 1, (int)(normalised * IGNEOUS_STATES.length)));
                int indexM = Math.max(0, Math.min(METAMORPHIC_STATES.length - 1, (int)(normalised * METAMORPHIC_STATES.length)));
                int indexS = Math.max(0, Math.min(SEDIMENTARY_STATES.length - 1, (int)(normalised * SEDIMENTARY_STATES.length)));

                for (int y = minY; y < maxY; y++) {
                    pos.set(x, y, z);
                    if (chunk.getBlockState(pos) == stone) {
                        BlockState replacement;
                        if (y < igneousMaxY)          replacement = IGNEOUS_STATES[indexI];
                        else if (y < metamorphicMaxY) replacement = METAMORPHIC_STATES[indexM];
                        else                          replacement = SEDIMENTARY_STATES[indexS];
                        chunk.setBlockState(pos, replacement, false);
                    }
                }
            }
        }
    }
}
