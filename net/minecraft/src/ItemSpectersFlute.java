package net.minecraft.src;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ItemSpectersFlute extends ItemEbonMod {

	boolean isLocked = false;
	int lockedTime = 0;
	
	double lockedX = 0;
	double lockedY = 0;
	double lockedZ = 0;
	float lockedRotationPitch = 0;
	float lockedRotationYaw = 0;
	float lockedRotationYawHead = 0;
	
	public ItemSpectersFlute(int i) {
		super(i);
		setMaxStackSize(1);
	}
	
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
    	if(isLocked){
    		EntityPlayer p = (EntityPlayer)par3Entity;
        	p.setPosition(lockedX, lockedY, lockedZ);
        	p.rotationPitch = lockedRotationPitch;
        	p.rotationYaw = lockedRotationYaw;
        	p.rotationYawHead = lockedRotationYawHead;
    		if(ModLoader.getMinecraftInstance().currentScreen != null && (ModLoader.getMinecraftInstance().currentScreen instanceof GuiContainer || !ModLoader.getMinecraftInstance().currentScreen.doesGuiPauseGame())) ModLoader.getMinecraftInstance().displayGuiScreen(null);
    		if(p.inventory.currentItem != par4) p.inventory.currentItem = par4;
    		
    		p.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        	isLocked = lockedTime-- >= 0;
        	if(!isLocked){
        		Set keySet = p.activePotionsMap.keySet();
        		Iterator it = keySet.iterator();
        			while(it.hasNext()){
        				Integer i = (Integer)it.next();
        				PotionEffect pe = (PotionEffect)p.activePotionsMap.get(i);
        				if(pe.getPotionID() == mod_Ebon.spectral.id){
        					it.remove();
        					p.onFinishedPotionEffect(pe);
        				}
        			}	
        		
        		List<EntitySpecter> nearbySpecters = par2World.getEntitiesWithinAABB(EntitySpecter.class, AxisAlignedBB.getBoundingBoxFromPool(p.posX-16, p.posY-8, p.posZ-16, p.posX+16, p.posY+8, p.posZ+16));
        		for(EntitySpecter s : nearbySpecters)
        			if(!s.isVisible())
        				s.visibilify(2400);
        	}
    	}
    	
    }
	
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }
    
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(!isLocked){
        	lockedX = par3EntityPlayer.posX;
        	lockedY = par3EntityPlayer.posY;
        	lockedZ = par3EntityPlayer.posZ;
        	lockedRotationPitch = par3EntityPlayer.rotationPitch;
        	lockedRotationYaw = par3EntityPlayer.rotationYaw;
        	lockedRotationYawHead = par3EntityPlayer.rotationYawHead;
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            par2World.playSoundAtEntity(par3EntityPlayer, "vazkii.ebonmod.spectersFlute", 1.0F, 1.0F);
            lockedTime = 180;
            isLocked = true;
    	}
        return par1ItemStack;
    }
    
    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.rare;
    }
}
