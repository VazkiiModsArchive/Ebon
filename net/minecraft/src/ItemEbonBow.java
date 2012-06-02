package net.minecraft.src;

import java.util.Random;

import net.minecraft.src.forge.ForgeHooks;
import net.minecraft.src.forge.ITextureProvider;

public class ItemEbonBow extends ItemBow implements ITextureProvider
{

    public ItemEbonBow(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        if (ForgeHooks.onArrowLoose(par1ItemStack, par2World, par3EntityPlayer, getMaxItemUseDuration(par1ItemStack) - par4))
        {
            return;
        }
        
        boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (var5 || par3EntityPlayer.inventory.hasItem(Item.arrow.shiftedIndex))
        {
            int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
            float var7 = (float)var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            if ((double)var7 < 0.1D)
            {
                return;
            }

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer, var7 * 2.0F);

            if (var7 == 1.0F)
            {
                var8.arrowCritical = true;
            }

            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (var9 > 0)
            {
                var8.setDamage(var8.getDamage() + (double)var9 * 0.5D + 0.5D);
            }

            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (var10 > 0)
            {
                var8.func_46023_b(var10);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
                var8.setFire(100);
            }

            //par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

            if (!var5)
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
            }
            else
            {
                var8.doesArrowBelongToPlayer = false;
            }

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(var8);
            }
        }
    }
    
	public String getTextureFile() {
		return "/vazkii/ebonmod/sprites.png";
	}
    
    public int getItemEnchantability()
    {
        return 2;
    }
    
}
