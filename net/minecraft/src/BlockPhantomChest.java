package net.minecraft.src;

import java.util.Random;
import java.util.Set;

import net.minecraft.src.forge.IChunkLoadHandler;

public class BlockPhantomChest extends BlockContainer implements IChunkLoadHandler{

	Random rand = new Random();
	
	public BlockPhantomChest(int par1, Material par2Material) {
		super(par1, par2Material);
		setTickRandomly(true);
		
	}
	
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
    	if(par5EntityLiving instanceof EntityPlayer){
        TileEntityPhantomChest var6 = (TileEntityPhantomChest)par1World.getBlockTileEntity(par2, par3, par4);
        var6.initializeEntity(rand);
        //((EntityPlayer)par5EntityLiving).addChatMessage(new StringBuilder().append("[Debug] Chest Rank: ").append(var6.getRank()).toString());
    	}
    }

	public BlockPhantomChest(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
	}
	
	public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
		if(par5EntityPlayer.getCurrentEquippedItem() == null || par5EntityPlayer.getCurrentEquippedItem().itemID != mod_Ebon.phantomKey.shiftedIndex) return false;
        TileEntityPhantomChest var6 = (TileEntityPhantomChest)par1World.getBlockTileEntity(par2, par3, par4);
            
        if(var6 != null && var6.getRank() == 4){
        	par5EntityPlayer.getCurrentEquippedItem().stackSize--;
                var6.worldObj.setBlockWithNotify(var6.xCoord, var6.yCoord, var6.zCoord, 0);
                var6.worldObj.removeBlockTileEntity(var6.xCoord, var6.yCoord, var6.zCoord);
                for(int i=0; i<50; i++){
                    float f = (float)par2 + rand.nextFloat();
                    float f2 = (float)par3 + rand.nextFloat() * 0.5F + 0.5F;
                    float f3 = (float)par4 + rand.nextFloat();
                var6.worldObj.spawnParticle("explode", f, f2, f3, 0.0D, 0.0D, 0.0D);
                }
                doRandomBadThing(par1World, par2, par3, par4);
                var6.worldObj.playSoundEffect(var6.xCoord, var6.yCoord, var6.zCoord, "mob.endermen.portal", 1.0F, 1.0F);
                return true;
            }

            if (var6 != null){
            par5EntityPlayer.getCurrentEquippedItem().stackSize--;
            ModLoader.openGUI(par5EntityPlayer, new GuiPhantomChest(par5EntityPlayer.inventory, var6));
            }
            
            return true;
    }

	public TileEntity getBlockEntity() {
		return new TileEntityPhantomChest();
	}
	
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public int getRenderType()
    {
        return mod_Ebon.phantomChestRenderID;
    }

    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	for(int l = 0; l < 25; l++)
        {
            float f = (float)par2 + par5Random.nextFloat();
            float f2 = (float)par3 + par5Random.nextFloat() * 0.5F;
            float f3 = (float)par4 + par5Random.nextFloat();
            par1World.spawnParticle("portal", f, f2, f3, 0.0D, 0.0D, 0.0D);
                }
    }
    
    public void doRandomBadThing(World world, int x, int y, int z){
    	Random rand = new Random();
    	EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;
    	
    	switch(rand.nextInt(10)){
    	
    	case 1:{
            EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("Creeper", world);
            entityliving.setLocationAndAngles(x, y+1, z, world.rand.nextFloat() * 360F, 0.0F);
            world.spawnEntityInWorld(entityliving);
    	}
    	case 2:{
    		world.spawnEntityInWorld(new EntityPotion(world, player, 16484));
    	}
    	case 3:{
    		world.addWeatherEffect(new EntityLightningBolt(world, x, y, z));
    	}
    	case 4:{
            EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("CaveSpider", world);
            entityliving.setLocationAndAngles(x, y+1, z, world.rand.nextFloat() * 360F, 0.0F);
            world.spawnEntityInWorld(entityliving);
    	}
    	case 5:{
            EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("Ghast", world);
            entityliving.setLocationAndAngles(x, y+1, z, world.rand.nextFloat() * 360F, 0.0F);
            world.spawnEntityInWorld(entityliving);
    	}
    	case 6:{
    		world.setBlockWithNotify(x, y, z, Block.lavaStill.blockID);
    	}
    	case 7:{
    		for(int i=0; i<9; i++){
            EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("Zombie", world);
            entityliving.setLocationAndAngles(x, y+1, z, world.rand.nextFloat() * 360F, 0.0F);
            world.spawnEntityInWorld(entityliving);
    		}
    	}
    	case 8:{
    		for(int i=0; i<4; i++){
                EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("Skeleton", world);
                entityliving.setLocationAndAngles(x, y+1, z, world.rand.nextFloat() * 360F, 0.0F);
                world.spawnEntityInWorld(entityliving);
        		}
    	}
    	case 9:{
    		for(int i=0; i<3; i++){
            EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("FallingSand", world);
            entityliving.setLocationAndAngles(player.posX, player.posY+3, player.posZ, world.rand.nextFloat() * 360F, 0.0F);
            world.spawnEntityInWorld(entityliving);
    		}
    	}
    	default:{
    		player.setFire(15);
    	}
    	}
    }

	public void addActiveChunks(World world, Set<ChunkCoordIntPair> chunkList) {
	}

	public boolean canUnloadChunk(Chunk chunk) {
		return false;
	}

	public boolean canUpdateEntity(Entity entity) {
		return true;
	}
    
}
