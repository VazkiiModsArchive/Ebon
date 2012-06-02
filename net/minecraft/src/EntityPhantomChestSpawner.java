package net.minecraft.src;

import java.util.Random;

public class EntityPhantomChestSpawner extends EntityLiving {
	
	Random rand = new Random();

	public EntityPhantomChestSpawner(World par1World) {
		super(par1World);
	}
	
	public void onLivingUpdate(){
		super.onLivingUpdate();
		worldObj.setBlockWithNotify((int)posX, (int)posY, (int)posZ, mod_Ebon.phantomChest.blockID);
		((TileEntityPhantomChest)worldObj.getBlockTileEntity((int)posX, (int)posY, (int)posZ)).initializeEntity(new Random());
		setDead();
	}
	
	protected void entityInit() {}
	public void readEntityFromNBT(NBTTagCompound var1) {}
	public void writeEntityToNBT(NBTTagCompound var1) {}
	public int getMaxHealth() { return 1; }
	public void updatePotionEffects(){}
    public int getMaxSpawnedInChunk(){
        return 1;
    }

	public boolean getCanSpawnHere(){
		return (worldObj.worldInfo.getDimension() == 0 || worldObj.worldInfo.getDimension() == 7);
	}
}
