// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.ITextureProvider;

// Referenced classes of package net.minecraft.src:
//            ItemTool, Item, EntityLiving, Block, 
//            EnumToolMaterial, ItemStack

public class ItemEbonAx extends ItemTool implements ITextureProvider
{

    protected ItemEbonAx(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, 3, enumtoolmaterial, blocksEffectiveAgainst);
    }
    
    public int getItemEnchantability()
    {
        return 22;
    }
    
	public String getTextureFile() {
		return "/vazkii/ebonmod/sprites.png";
	}

    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        if(random.nextInt(50) == 0 && !EbonAPI.doesPlayerHaveMagicExhaustion())
        {
            EntityPlayer ep = mc.thePlayer;
            ep.addChatMessage("I seem to have salvaged some gunpowder.");
            ep.dropItemWithOffset(Item.gunpowder.shiftedIndex, 1, 0.0F);
            EbonAPI.addMagicalExhaustionOnPlayerFor(3);
            return super.onBlockDestroyed(itemstack, i, j, k, l, entityliving);
        } else
        {
            return super.onBlockDestroyed(itemstack, i, j, k, l, entityliving);
        }
    }

    private static Block blocksEffectiveAgainst[];
    private static Random random = new Random();
    private Minecraft mc = ModLoader.getMinecraftInstance();

    static 
    {
        blocksEffectiveAgainst = (new Block[] {
            Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stairDouble, Block.stairSingle, Block.pumpkin, Block.pumpkinLantern
        });
    }
}
