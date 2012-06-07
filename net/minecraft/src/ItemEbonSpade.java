// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.forge.ITextureProvider;

import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            ItemTool, Block, mod_Ebon, Item,
//            EntityLiving, EnumToolMaterial, ItemStack

public class ItemEbonSpade extends ItemTool implements ITextureProvider
{
    public ItemEbonSpade(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, 1, enumtoolmaterial, blocksEffectiveAgainst);
    }

    public int getItemEnchantability()
    {
        return 22;
    }

    public String getTextureFile()
    {
        return "/vazkii/ebonmod/sprites.png";
    }

    public boolean canHarvestBlock(Block block)
    {
        if (block == Block.snow)
        {
            return true;
        }
        else
        {
            return block == Block.blockSnow;
        }
    }

    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        if (random.nextInt(50) == 0 && !EbonAPI.doesPlayerHaveMagicExhaustion())
        {
            EntityPlayer ep = mc.thePlayer;
            ep.addChatMessage("I seem to have dug some corpse dust.");
            ep.dropItemWithOffset(mod_Ebon.corpsedust.shiftedIndex, 1, 0.0F);
            entityliving.worldObj.playSoundAtEntity(entityliving, "vazkii.ebonmod.tool", 1.0F, 1.0F);
            EbonAPI.addMagicalExhaustionOnPlayerFor(3);
            return super.onBlockDestroyed(itemstack, i, j, k, l, entityliving);
        }
        else
        {
            return super.onBlockDestroyed(itemstack, i, j, k, l, entityliving);
        }
    }

    private static Block blocksEffectiveAgainst[];
    private static Random random = new Random();
    private Minecraft mc = ModLoader.getMinecraftInstance();

    static
    {
        blocksEffectiveAgainst = (new Block[]
                {
                    Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium
                });
    }
}
