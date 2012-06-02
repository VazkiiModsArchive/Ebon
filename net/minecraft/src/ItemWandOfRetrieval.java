package net.minecraft.src;

import java.util.Random;

public class ItemWandOfRetrieval extends Ebon3DItems {
	
	Random rand = new Random();
	
	public ItemWandOfRetrieval(int i) {
		super(i, EnumRarity.rare);
		setMaxStackSize(1);
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        int i1 = world.getBlockId(i, j, k);
        String entityString;
        Entity entityClass;
        int entityID;
        
        double playerX = entityplayer.posX;
        double playerY = entityplayer.posY;
        double playerZ = entityplayer.posZ;
        if(i1 == Block.mobSpawner.blockID && !EbonAPI.doesPlayerHaveMagicExhaustion() && EbonAPI.hasNecroTome()){
        	entityString = ((TileEntityMobSpawner)world.getBlockTileEntity(i, j, k)).getMobID();
        	entityClass = EntityList.createEntityByName(entityString, world);
        	entityID = EntityList.getEntityID(entityClass);
        	if(entityID != 0 && ((ItemNecroTome)EbonAPI.getNecroTome().getItem()).damageItemWithNotify(EbonAPI.getNecroTome(), 1, entityplayer)){
        		world.setBlockWithNotify(i, j, k, 0);
        		world.removeBlockTileEntity(i, j, k);
        		ItemEbonStaff.doParticles(world, i, j, k);
        		entityplayer.worldObj.playSoundEffect(playerX, playerY, playerZ, "mob.endermen.portal", 1.0F, 1.0F);
        		
        		if(ModLoader.isModLoaded("mod_NotEnoughItems")){
            		if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(Block.mobSpawner, 1, entityID)))
            			entityplayer.dropPlayerItem(new ItemStack(Block.mobSpawner, 1, entityID));
        		}else{
        		if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_Ebon.mobSpawnerItem, 1, entityID)))
        			entityplayer.dropPlayerItem(new ItemStack(mod_Ebon.mobSpawnerItem, 1, entityID));
        		}
        		
        		if(ModLoader.getMinecraftInstance().theWorld.worldInfo.getGameType() != 1)
        		itemstack.stackSize = 0;
        		EbonAPI.addMagicalExhaustionOnPlayerFor(120);
        		return true;
        	}
        }

        return false;
    }
	
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return !EbonAPI.doesPlayerHaveMagicExhaustion();
    }
	
}
