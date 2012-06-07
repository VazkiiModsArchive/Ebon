package net.minecraft.src;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.forge.IBonemealHandler;

public class BlockBloodLeaf extends BlockCrops implements ITextureProvider
{
    protected BlockBloodLeaf(int i, int j)
    {
        super(i, j);
        blockIndexInTexture = j;
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        float f = (float)i + random.nextFloat();
        float f1 = (float)j + random.nextFloat() * 0.5F + 0.5F;
        float f2 = (float)k + random.nextFloat();

        if (world.getBlockMetadata(i, j, k) == 7)
        {
            world.spawnParticle("portal", f, f1, f2, 0F, 0F, 0F);
        }
    }

    protected boolean canThisPlantGrowOnThisBlockID(int i)
    {
        return i == mod_Ebon.quicksand.blockID;
    }

    public void fertilize(World world, int i, int j, int k)
    {
        world.createExplosion(null, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, 2);
    }

    public static void fertilizePropperly(World world, int i, int j, int k)
    {
        Random r = new Random();
        world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "mob.blaze.death", 1.0F + r.nextFloat(), r.nextFloat() * 0.7F + 0.3F);
        world.setBlockMetadataWithNotify(i, j, k, 7);
    }

    private float getGrowthRate(World world, int i, int j, int k)
    {
        float f = 1.0F;
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        int l1 = world.getBlockId(i - 1, j, k - 1);
        int i2 = world.getBlockId(i + 1, j, k - 1);
        int j2 = world.getBlockId(i + 1, j, k + 1);
        int k2 = world.getBlockId(i - 1, j, k + 1);
        boolean flag = j1 == blockID || k1 == blockID;
        boolean flag1 = l == blockID || i1 == blockID;
        boolean flag2 = l1 == blockID || i2 == blockID || j2 == blockID || k2 == blockID;

        for (int l2 = i - 1; l2 <= i + 1; l2++)
        {
            for (int i3 = k - 1; i3 <= k + 1; i3++)
            {
                int j3 = world.getBlockId(l2, j - 1, i3);
                float f1 = 0.0F;

                if (j3 == mod_Ebon.quicksand.blockID)
                {
                    f1 = 1.0F;

                    if (world.getBlockMetadata(l2, j - 1, i3) > 0)
                    {
                        f1 = 3F;
                    }
                }

                if (l2 != i || i3 != k)
                {
                    f1 /= 4F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }

        return f;
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        super.updateTick(world, i, j, k, random);

        if (world.getBlockLightValue(i, j + 1, k) >= 5)
        {
            int l = world.getBlockMetadata(i, j, k);

            if (l < 7)
            {
                float f = getGrowthRate(world, i, j, k);

                if (random.nextInt((int)(25F / f) + 1) == 0)
                {
                    l++;
                    world.setBlockMetadataWithNotify(i, j, k, l);
                }
            }
        }
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int i, int j, int k, int meta, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        if (meta == 7)
        {
            ret.add(new ItemStack(mod_Ebon.bloodLeaf));
        }

        ret.add(new ItemStack(mod_Ebon.bloodSeeds));
        return ret;
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        if (j < 0)
        {
            j = 7;
        }

        return blockIndexInTexture + j;
    }

    public int idDropped(int i, Random random, int j)
    {
        if (i == 7)
        {
            return mod_Ebon.bloodLeaf.shiftedIndex;
        }
        else
        {
            return -1;
        }
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        Random r = new Random();
        world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "mob.endermen.portal", 1.0F + r.nextFloat(), r.nextFloat() * 0.7F + 0.3F);
        super.onBlockRemoval(world, i, j, k);
    }

    public int quantityDropped(Random random)
    {
        Random rand = new Random();
        return rand.nextInt(3);
    }

    public String getTextureFile()
    {
        return "/vazkii/ebonmod/sprites.png";
    }
}
