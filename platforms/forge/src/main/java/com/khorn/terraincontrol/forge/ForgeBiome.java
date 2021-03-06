package com.khorn.terraincontrol.forge;

import com.khorn.terraincontrol.BiomeIds;
import com.khorn.terraincontrol.LocalBiome;
import com.khorn.terraincontrol.configuration.BiomeConfig;
import com.khorn.terraincontrol.forge.generator.BiomeGenCustom;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class ForgeBiome implements LocalBiome
{
    private final Biome biomeBase;
    private final BiomeConfig biomeConfig;

    /**
     * Creates a new biome with the given name and id.
     * 
     * @param biomeConfig The config of the biome.
     * @param minecraftBiome The Minecraft instance of the biome.
     * @return The registered biome.
     */
    public static ForgeBiome forBiome(BiomeConfig biomeConfig, Biome minecraftBiome)
    {
        return new ForgeBiome(biomeConfig, minecraftBiome);
    }

    private ForgeBiome(BiomeConfig biomeConfig, Biome biome)
    {
        this.biomeBase = biome;
        this.biomeConfig = biomeConfig;
    }

    @Override
    public boolean isCustom()
    {
        return biomeBase instanceof BiomeGenCustom;
    }

    @Override
    public String getName()
    {
        return biomeBase.getBiomeName();
    }

    public Biome getHandle()
    {
        return biomeBase;
    }

    @Override
    public BiomeIds getIds()
    {
        if (biomeBase instanceof BiomeGenCustom)
        {
            return new BiomeIds(((BiomeGenCustom) biomeBase).generationId, Biome.getIdForBiome(biomeBase));
        }
        return new BiomeIds(Biome.getIdForBiome(biomeBase));
    }

    @Override
    public float getTemperatureAt(int x, int y, int z)
    {
        return biomeBase.getFloatTemperature(new BlockPos(x, y, z));
    }

    @Override
    public BiomeConfig getBiomeConfig()
    {
        return biomeConfig;
    }
    
    @Override
    public String toString()
    {
        return getName() + "[" + getIds() + "]";
    }
}
