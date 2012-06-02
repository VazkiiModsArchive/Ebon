// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.forge.ITextureProvider;

import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            ItemTool, Block, EnumToolMaterial, Material, 
//            Item, EntityLiving, ItemStack

public class ItemEbonPick extends ItemTool implements ITextureProvider
{

    protected ItemEbonPick(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i, 2, enumtoolmaterial, blocksEffectiveAgainst);
    }

	public String getTextureFile() {
		return "/vazkii/ebonmod/sprites.png";
	}
    
    public int getItemEnchantability()
    {
        return 22;
    }
    
    public boolean canHarvestBlock(Block block)
    {
        if(block == Block.obsidian)
        {
            return toolMaterial.getHarvestLevel() == 3;
        }
        if(block == Block.blockDiamond || block == Block.oreDiamond)
        {
            return toolMaterial.getHarvestLevel() >= 2;
        }
        if(block == Block.blockGold || block == Block.oreGold)
        {
            return toolMaterial.getHarvestLevel() >= 2;
        }
        if(block == Block.blockSteel || block == Block.oreIron)
        {
            return toolMaterial.getHarvestLevel() >= 1;
        }
        if(block == Block.blockLapis || block == Block.oreLapis)
        {
            return toolMaterial.getHarvestLevel() >= 1;
        }
        if(block == Block.oreRedstone || block == Block.oreRedstoneGlowing)
        {
            return toolMaterial.getHarvestLevel() >= 2;
        }
        if(block.blockMaterial == Material.rock)
        {
            return true;
        } else
        {
            return block.blockMaterial == Material.iron;
        }
    }

    public boolean onBlockDestroyed(ItemStack itemstack, int i, int j, int k, int l, EntityLiving entityliving)
    {
        if(random.nextInt(50) == 0 && !EbonAPI.doesPlayerHaveMagicExhaustion())
        {
            EntityPlayer ep = mc.thePlayer;
            ep.addChatMessage("I seem to have found a bone.");
            ep.dropItemWithOffset(Item.bone.shiftedIndex, 1, 0.0F);
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
            Block.cobblestone, Block.stairDouble, Block.stairSingle, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold, 
            Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, 
            Block.railDetector, Block.railPowered
        });
    }
}
