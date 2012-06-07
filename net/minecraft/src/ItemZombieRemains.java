package net.minecraft.src;

import net.minecraft.src.forge.ForgeHooks;

public class ItemZombieRemains extends ItemEbonMod
{
    public ItemZombieRemains(int i)
    {
        super(i);
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7)
    {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6))
        {
            return false;
        }
        else
        {
            int var8 = par3World.getBlockId(par4, par5, par6);

            if (ForgeHooks.onUseBonemeal(par3World, var8, par4, par5, par6))
            {
                if (!par3World.isRemote)
                {
                    par1ItemStack.stackSize--;
                }

                return true;
            }

            if (var8 == Block.sapling.blockID)
            {
                if (!par3World.isRemote)
                {
                    ((BlockSapling)Block.sapling).growTree(par3World, par4, par5, par6, par3World.rand);
                    --par1ItemStack.stackSize;
                }

                return true;
            }

            if (var8 == Block.mushroomBrown.blockID || var8 == Block.mushroomRed.blockID)
            {
                if (!par3World.isRemote && ((BlockMushroom)Block.blocksList[var8]).fertilizeMushroom(par3World, par4, par5, par6, par3World.rand))
                {
                    --par1ItemStack.stackSize;
                }

                return true;
            }

            if (var8 == Block.melonStem.blockID || var8 == Block.pumpkinStem.blockID)
            {
                if (!par3World.isRemote)
                {
                    ((BlockStem)Block.blocksList[var8]).fertilizeStem(par3World, par4, par5, par6);
                    --par1ItemStack.stackSize;
                }

                return true;
            }

            if (var8 == Block.crops.blockID)
            {
                if (!par3World.isRemote)
                {
                    ((BlockCrops)Block.crops).fertilize(par3World, par4, par5, par6);
                    --par1ItemStack.stackSize;
                }

                return true;
            }

            if (var8 == Block.grass.blockID)
            {
                if (!par3World.isRemote)
                {
                    --par1ItemStack.stackSize;
                    label73:

                    for (int var9 = 0; var9 < 128; ++var9)
                    {
                        int var10 = par4;
                        int var11 = par5 + 1;
                        int var12 = par6;

                        for (int var13 = 0; var13 < var9 / 16; ++var13)
                        {
                            var10 += itemRand.nextInt(3) - 1;
                            var11 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
                            var12 += itemRand.nextInt(3) - 1;

                            if (par3World.getBlockId(var10, var11 - 1, var12) != Block.grass.blockID || par3World.isBlockNormalCube(var10, var11, var12))
                            {
                                continue label73;
                            }
                        }

                        if (par3World.getBlockId(var10, var11, var12) == 0)
                        {
                            if (itemRand.nextInt(10) != 0)
                            {
                                par3World.setBlockAndMetadataWithNotify(var10, var11, var12, Block.tallGrass.blockID, 1);
                            }
                            else
                            {
                                ForgeHooks.plantGrassPlant(par3World, var10, var11, var12);
                            }
                        }
                    }
                }

                return true;
            }

            return false;
        }
    }
}
