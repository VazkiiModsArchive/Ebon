// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.forge.ITextureProvider;

// Referenced classes of package net.minecraft.src:
//            Block, mod_Ebon, Item, Material

public class BlockEbonGlowstone extends Block implements ITextureProvider
{
    public BlockEbonGlowstone(int i, int j, Material material)
    {
        super(i, j, material);
    }

    public int quantityDropped(Random random)
    {
        return 2 + random.nextInt(3);
    }

    public int idDropped(int i, Random random)
    {
        return mod_Ebon.glowdustEbon.shiftedIndex;
    }

    public String getTextureFile()
    {
        return "/vazkii/ebonmod/sprites.png";
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        if (isAltar(world, i, j, k))
        {
            float f = (float)i + random.nextFloat();
            float f1 = (float)j + random.nextFloat() * 0.5F + 0.5F;
            float f2 = (float)k + random.nextFloat();
            world.spawnParticle("portal", f, f1, f2, 0F, 0F, 0F);
            world.spawnParticle("portal", f, f1, f2, 0F, 0F, 0F);
            world.spawnParticle("portal", f, f1, f2, 0F, 0F, 0F);
        }
    }

    public static boolean isAltar(World world, int x, int y, int z)
    {
        return  BlockEbonObsidian.isAltar(world, x + 4, y - 1, z) ||
                BlockEbonObsidian.isAltar(world, x - 4, y - 1, z) ||
                BlockEbonObsidian.isAltar(world, x, y - 1, z - 4) ||
                BlockEbonObsidian.isAltar(world, x, y - 1, z + 4);
    }
}
