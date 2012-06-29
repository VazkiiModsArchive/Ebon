package net.minecraft.src;

import java.util.List;

public class ItemSpecterEye extends ItemEbonMod{

	public ItemSpecterEye(int i) {
		super(i);
		setMaxStackSize(1);
	}
	
	public boolean hasEffect(ItemStack stack){
		EntityPlayer p = ModLoader.getMinecraftInstance().thePlayer;
		return ModLoader.getMinecraftInstance().theWorld.getEntitiesWithinAABB(EntitySpecter.class, AxisAlignedBB.getBoundingBoxFromPool(p.posX-16, p.posY-8, p.posZ-16, p.posX+16, p.posY+8, p.posZ+16)).size() > 0;
	}

}
