package net.alkeari.geostrata.mixin;

import net.alkeari.geostrata.worldgen.GeoStrataStoneReplacer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseBasedChunkGeneratorMixin {

    @Inject(
        method = "buildSurface(Lnet/minecraft/server/level/WorldGenRegion;" +
                 "Lnet/minecraft/world/level/StructureManager;" +
                 "Lnet/minecraft/world/level/levelgen/RandomState;" +
                 "Lnet/minecraft/world/level/chunk/ChunkAccess;)V",
        at = @At("TAIL")
    )
    private void geostrata_replaceStone(WorldGenRegion region, StructureManager structures,
            RandomState randomState, ChunkAccess chunk, CallbackInfo ci) {
        long seed = randomState.getOrCreateRandomFactory(
            new ResourceLocation("geostrata", "province_seed")).at(0, 0, 0).nextLong();
        GeoStrataStoneReplacer.replaceStone(chunk, seed);
    }
}
