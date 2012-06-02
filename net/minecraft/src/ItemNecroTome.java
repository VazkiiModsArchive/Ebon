package net.minecraft.src;

import java.util.List;

public class ItemNecroTome extends ItemEbonMod {

	public ItemNecroTome(int i) {
		super(i);
		setMaxDamage(257);
		setMaxStackSize(1);
		setNoRepair();
	}
	
	public boolean damageItemWithNotify(ItemStack itemstack, int i, EntityPlayer entityplayer){
		if(itemstack.getItemDamage() != 256){
		itemstack.damageItem(i, entityplayer);
		entityplayer.addChatMessage("Spell Successful.");
		return true;
		}entityplayer.addChatMessage("Spell Failed"); 
			return false;
	}
	
    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.rare;
    }
    
    public void addInformation(ItemStack itemstack, List list)
    {
    	if(itemstack.getItemDamage() == 0)
    		list.add("Status: Pristine");
    	else if(itemstack.getItemDamage() <= 32)
    		list.add("Status: Mint");
    	else if(itemstack.getItemDamage() <= 64)
    		list.add("Status: Near Mint");
    	else if(itemstack.getItemDamage() <= 128)
    		list.add("Status: Very Good");
    	else if(itemstack.getItemDamage() <= 192)
    		list.add("Status: Good");
    	else if(itemstack.getItemDamage() <= 224)
    		list.add("Status: Moderate");
    	else if(itemstack.getItemDamage() == 256) 
    		list.add("Status: Poor");
    }

}
