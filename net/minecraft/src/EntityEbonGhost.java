// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            EntityMob, mod_Ebon, Item, ItemStack, 
//            World

public class EntityEbonGhost extends EntityMob
{

	Random part = new Random();

	public EntityEbonGhost(World world)
    {
        super(world);
        Random r = new Random();
        texture = "/vazkii/ebonmod/ebonspirit.png";
        moveSpeed = 0.9F;
        while(moveSpeed < 0.2F || moveSpeed > 0.6F){
        moveSpeed = r.nextFloat();}
        attackStrength = 16;
        health = 20000; //Only kill with ebon sword
        isImmuneToFire = true;
    }
   
    public int getMaxHealth()
    {
        return 20000;
    }
    
    protected String getLivingSound()
    {
        return null;
    }

    protected String getHurtSound()
    {
        return null;
    }

    protected String getDeathSound()
    {
        return "portal.portal";
    }
    
    protected float getSoundVolume() {
        return 0.3F;
     }

    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    protected int getDropItemId()
    {
        return mod_Ebon.soul.shiftedIndex;
    }
    protected void dropFewItems(boolean flag, int i)
    {
        int z = rand.nextInt(3);
        for(int k = 0; k < z; k++)
        {
            dropItem(mod_Ebon.soul.shiftedIndex, 1);
        }

    }
    
    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        Entity entity = damagesource.getEntity();

        if(super.attackEntityFrom(damagesource, i) && (entity instanceof EntityPlayer))
        {
        	EntityPlayer ep = mc.thePlayer;
        	 ItemStack itemstack = ep.inventory.getCurrentItem();
             if(itemstack == null || itemstack.itemID != mod_Ebon.ebonsword.shiftedIndex)
        	ep.addChatMessage("This weapon doesn't seem to be very effective, maybe I should attack it with something else...");
            return true;
        } else
            return false;
    }
    
    public void onLivingUpdate()
    {
        worldObj.spawnParticle("townaura", posX + (rand.nextDouble() - 0.5D) * (double)width, posY + rand.nextDouble() * (double)height, posZ + (rand.nextDouble() - 0.5D) * (double)width, 0.0D, 0.0D, 0.0D);
        worldObj.spawnParticle("townaura", posX + (rand.nextDouble() - 0.5D) * (double)width, posY + rand.nextDouble()+0.5D * (double)height, posZ + (rand.nextDouble() - 0.5D) * (double)width, 0.0D, 0.0D, 0.0D);
        findPlayerToAttack();
        super.onLivingUpdate();
    }
    
    protected Entity findPlayerToAttack()
    {
        EntityPlayer ep = mc.thePlayer;
        ItemStack itemstack = ep.getCurrentEquippedItem();
        if(itemstack != null && itemstack.getItem() instanceof ItemEbonScepter)
        {
        	return null;
        } else
        {
            double d = 16D;
            return worldObj.getClosestVulnerablePlayerToEntity(this, d);
        }
    }

    public EnumCreatureAttribute func_40124_t()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    private static final ItemStack defaultHeldItem;
    private Minecraft mc = ModLoader.getMinecraftInstance();

    static 
    {
        defaultHeldItem = new ItemStack(mod_Ebon.ebonstaffu, 1);
    }
}
