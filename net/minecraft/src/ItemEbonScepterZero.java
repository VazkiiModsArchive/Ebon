package net.minecraft.src;

public class ItemEbonScepterZero extends ItemEbonScepter {

	public ItemEbonScepterZero(int i, EnumRarity er) {
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
			if(is != null && mod_Ebon.getLivingBomb() == null){
				if(((ItemNecroTome)i).damageItemWithNotify(is, 1, ep))
					mod_Ebon.setLivingBomb(entityliving);
				entityliving.worldObj.playSoundEffect(entityliving.posX, entityliving.posY, entityliving.posZ, "vazkii.ebonmod.spell", 1.0F, 1.0F);
				if(entityliving.worldObj.worldInfo.getGameType() != 1)	
				EbonAPI.addMagicalExhaustionOnPlayerFor(30);
			}else {ep.addChatMessage("Spell Failed, there's already a Living Bomb");
			entityliving.worldObj.playSoundEffect(entityliving.posX, entityliving.posY, entityliving.posZ, "vazkii.ebonmod.fail", 1.0F, 1.0F);
				}
			}else
		entityliving.worldObj.playSoundEffect(entityliving.posX, entityliving.posY, entityliving.posZ, "vazkii.ebonmod.fail", 1.0F, 1.0F);
	}

}
