package net.minecraft.src;

public class ItemEbonScepterInfinity extends ItemEbonScepter {

	public ItemEbonScepterInfinity(int i, EnumRarity er) {
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
				if(((ItemNecroTome)i).damageItemWithNotify(is, 1, ep))
		        	entityliving.worldObj.addWeatherEffect(new EntityLightningBolt(entityliving.worldObj, entityliving.posX, entityliving.posY, entityliving.posZ));
				if(entityliving.worldObj.worldInfo.getGameType() != 1)	
				EbonAPI.addMagicalExhaustionOnPlayerFor(30);
			}else
		entityliving.worldObj.playSoundEffect(entityliving.posX, entityliving.posY, entityliving.posZ, "mob.endermen.portal", 1.0F, 1.0F);
	}
	
}
