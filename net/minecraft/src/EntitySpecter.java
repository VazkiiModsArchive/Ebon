package net.minecraft.src;

public class EntitySpecter extends EntityCreature {

	private int ticksVisible = 0;
	
	public EntitySpecter(World par1World) {
		super(par1World);
		ticksVisible = 0;
		isImmuneToFire = true;
        texture = "/vazkii/ebonmod/specter.png";
        experienceValue = 10;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(2, new EntityAIWander(this, 0.3F));
        this.tasks.addTask(3, new EntityAIWatchClosest2(this, EntityPlayer.class, 6.0F, 1.0F));
	}
	
    public boolean isAIEnabled()
    {
        return isVisible();
    }
    
    protected void updateAITick()
    {
    	if(!isVisible()) return;
    	super.updateAITick();		
    }
	
    protected boolean canTriggerWalking()
    {
        return false;
    }
    
    public float getShadowSize()
    {
        return 0.0F;
    }
    
    public boolean isPotionApplicable(PotionEffect par1PotionEffect)
    {
    	return false;
    }
    
    public void onUpdate()
    {
    	if(worldObj.difficultySetting == 0) setDead();
    	
    	if(isVisible()){
    		--ticksVisible;
    		if(getDistanceToEntity(ModLoader.getMinecraftInstance().thePlayer) <= 3.33333333334)
    			teleportRandomly();
    		else;
    	} else if(getDistanceToEntity(ModLoader.getMinecraftInstance().thePlayer) < 6)
    		teleportRandomly();
    	super.onUpdate();
    }
    

	public int getMaxHealth() {
		return 24;
	} 
	
	public void visibilify(int ticks){
		ticksVisible = ticks;
		spawnExplosionParticle();
	}
	
	public boolean isVisible(){
		return ticksVisible > 0;
	}
	
    protected boolean teleportRandomly()
    {
        double var1 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
        double var3 = this.posY + (double)(this.rand.nextInt(64) - 32);
        double var5 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
        return this.teleportTo(var1, var3, var5);
    }
    
    protected boolean teleportTo(double par1, double par3, double par5)
    {
        double var7 = this.posX;
        double var9 = this.posY;
        double var11 = this.posZ;
        this.posX = par1;
        this.posY = par3;
        this.posZ = par5;
        boolean var13 = false;
        int var14 = MathHelper.floor_double(this.posX);
        int var15 = MathHelper.floor_double(this.posY);
        int var16 = MathHelper.floor_double(this.posZ);
        int var18;

        if (this.worldObj.blockExists(var14, var15, var16))
        {
            boolean var17 = false;

            while (!var17 && var15 > 0)
            {
                var18 = this.worldObj.getBlockId(var14, var15 - 1, var16);

                if (var18 != 0 && Block.blocksList[var18].blockMaterial.blocksMovement())
                {
                    var17 = true;
                }
                else
                {
                    --this.posY;
                    --var15;
                }
            }

            if (var17)
            {
                this.setPosition(this.posX, this.posY, this.posZ);

                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox))
                {
                    var13 = true;
                }
            }
        }

        if (!var13)
        {
            this.setPosition(var7, var9, var11);
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
    	if(!isVisible()) return false;
    	return super.attackEntityFrom(par1DamageSource, par2);
    }
	
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeEntityToNBT(par1NBTTagCompound);
    	par1NBTTagCompound.setInteger("ticksVisible", ticksVisible);
    }
    
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.readEntityFromNBT(par1NBTTagCompound);
    	ticksVisible = par1NBTTagCompound.getInteger("ticksVisible");
    }
    
    protected int getDropItemId()
    {
        return mod_Ebon.specterEssence.shiftedIndex;
    }
    
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

}
