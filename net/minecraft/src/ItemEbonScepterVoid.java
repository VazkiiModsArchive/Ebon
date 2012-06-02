package net.minecraft.src;

import java.util.Random;

public class ItemEbonScepterVoid extends ItemEbonScepter {

	public ItemEbonScepterVoid(int i, EnumRarity er) {
		super(i, er);
	}
	
	public void useItemOnEntity(ItemStack itemstack, EntityLiving entityliving)
    {
		EntityPlayer ep = ModLoader.getMinecraftInstance().thePlayer;
		ItemStack is = null;
		Item i = null;
			
		if(EbonAPI.hasNecroTome() && !EbonAPI.doesPlayerHaveMagicExhaustion()){
			is = EbonAPI.getNecroTome();
			i = is.getItem();
				if(((ItemNecroTome)i).damageItemWithNotify(is, 1, ep)){
					if(entityliving.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
			        	entityliving.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1000, 0));
					else entityliving.addPotionEffect(new PotionEffect(Potion.poison.id, 1000, 0));
	        		entityliving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1000, 1));
				if(entityliving.worldObj.worldInfo.getGameType() != 1)	
				EbonAPI.addMagicalExhaustionOnPlayerFor(30);
				}
			}else
		entityliving.worldObj.playSoundEffect(entityliving.posX, entityliving.posY, entityliving.posZ, "mob.endermen.portal", 1.0F, 1.0F);
		}
	
}
