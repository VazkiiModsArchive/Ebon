// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src;

import java.io.File;
import java.util.List;
import java.util.Random;
import net.minecraft.src.forge.ITextureProvider;

// Referenced classes of package net.minecraft.src:
//            BlockStone, mod_Ebon, Block

public class BlockEbonObsidian extends BlockStone implements ITextureProvider
{
    public BlockEbonObsidian(int i, int j)
    {
        super(i, j);
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    public int idDropped(int i, Random random)
    {
        return mod_Ebon.darkobsidian.blockID;
    }

    public String getTextureFile()
    {
        return "/vazkii/ebonmod/sprites.png";
    }

    double maxX = 0.0;
    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;

        if (isAltar(world, i, j, k))
        {
            float f = (float)i + random.nextFloat();
            float f1 = (float)j + random.nextFloat() * 0.5F + 0.5F;
            float f2 = (float)k + random.nextFloat();
            world.spawnParticle("portal", f, f1, f2, 0F, 0F, 0F);
            world.spawnParticle("portal", f, f1, f2, 0F, 0F, 0F);
            world.spawnParticle("portal", f, f1, f2, 0F, 0F, 0F);
            List list = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBoxFromPool(i - 5, j - 3, k - 5 , i + 5, j + 3, k + 5));

            if (!list.isEmpty() && !player.activePotionsMap.isEmpty())
            {
                player.clearActivePotions();
            }
        }
    }

    public static boolean isAltar(World world, int x, int y, int z)
    {
        return  world.getBlockId(x , y, z) == mod_Ebon.darkobsidian.blockID &&
                world.getBlockId(x  , y - 1, z) == Block.obsidian.blockID &&
                world.getBlockId(x + 4 , y + 1, z) == mod_Ebon.ebonglow.blockID &&
                world.getBlockId(x + 4 , y, z) == Block.obsidian.blockID &&
                world.getBlockId(x + 4 , y - 1, z) == Block.obsidian.blockID &&
                world.getBlockId(x - 4 , y + 1, z) == mod_Ebon.ebonglow.blockID &&
                world.getBlockId(x - 4 , y, z) == Block.obsidian.blockID &&
                world.getBlockId(x - 4 , y - 1, z) == Block.obsidian.blockID &&
                world.getBlockId(x , y + 1, z + 4) == mod_Ebon.ebonglow.blockID &&
                world.getBlockId(x , y, z + 4) == Block.obsidian.blockID &&
                world.getBlockId(x , y - 1, z + 4) == Block.obsidian.blockID &&
                world.getBlockId(x , y + 1, z + 4) == mod_Ebon.ebonglow.blockID &&
                world.getBlockId(x , y, z + 4) == Block.obsidian.blockID &&
                world.getBlockId(x , y - 1, z + 4) == Block.obsidian.blockID &&
                world.getBlockId(x , y + 1, z) == 0 &&
                world.getBlockId(x + 1 , y + 1, z) == 0 &&
                world.getBlockId(x + 2 , y + 1, z) == 0 &&
                world.getBlockId(x + 3 , y + 1, z) == 0 &&
                world.getBlockId(x - 1 , y + 1, z) == 0 &&
                world.getBlockId(x - 2 , y + 1, z) == 0 &&
                world.getBlockId(x - 3 , y + 1, z) == 0 &&
                world.getBlockId(x , y + 1, z + 1) == 0 &&
                world.getBlockId(x , y + 1, z + 2) == 0 &&
                world.getBlockId(x , y + 1, z + 3) == 0 &&
                world.getBlockId(x , y + 1, z - 1) == 0 &&
                world.getBlockId(x , y + 1, z - 2) == 0 &&
                world.getBlockId(x , y + 1, z - 3) == 0;
    }
}
