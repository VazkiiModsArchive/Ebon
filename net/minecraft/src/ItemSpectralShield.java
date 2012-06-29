package net.minecraft.src;

public class ItemSpectralShield extends ItemEbonMod{

	public ItemSpectralShield(int i) {
		super(i);
	}
	
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(!EbonAPI.doesPlayerHaveMagicExhaustion()){
        	par1ItemStack.damageItem(1, par3EntityPlayer);
        	EbonAPI.addMagicalExhaustionOnPlayerFor(240);
        	par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 4));
        	par3EntityPlayer.renderBrokenItemStack(par1ItemStack);
        	par1ItemStack.stackSize--;
    	}else par2World.playSoundAtEntity(par3EntityPlayer, "vazkii.ebonmod.fail", 1.0F, 1.0F);
    	
    	return par1ItemStack;
    }
    
    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.rare;
    }

}
