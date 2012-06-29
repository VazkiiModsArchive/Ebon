package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class ItemSpectralMirror extends ItemEbonMod {

	public ItemSpectralMirror(int i) {
		super(i);
		setMaxStackSize(1);
		setMaxDamage(16);
	}

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(!EbonAPI.doesPlayerHaveMagicExhaustion()){
        	par1ItemStack.damageItem(1, par3EntityPlayer);
        	EbonAPI.addMagicalExhaustionOnPlayerFor(240);
        	par3EntityPlayer.addPotionEffect(new PotionEffect(mod_Ebon.spectral.id, 600, 4));
    	}
    	return par1ItemStack;
    }
    
    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.rare;
    }
	
}
