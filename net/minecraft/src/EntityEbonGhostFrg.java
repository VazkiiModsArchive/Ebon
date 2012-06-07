package net.minecraft.src;

import java.util.Random;

public class EntityEbonGhostFrg extends EntityEbonGhost
{
    public EntityEbonGhostFrg(World world)
    {
        super(world);
        texture = "/vazkii/ebonmod/ebonspiritf.png";
        health = 24000; //Only kill with ebon sword
        moveSpeed = 1.0000001F;
        attackStrength = 21;
    }

    public int getMaxHealth()
    {
        return 24000;
    }

    public void onLivingUpdate()
    {
        worldObj.spawnParticle("flame", posX + (rand.nextDouble() - 0.5D) * (double)width, posY + rand.nextDouble() + 0.5D * (double)height, posZ + (rand.nextDouble() - 0.5D) * (double)width, 0.0D, 0.0D, 0.0D);
        super.onLivingUpdate();
    }

    protected int getDropItemId()
    {
        return mod_Ebon.sould.shiftedIndex;
    }
    protected void dropFewItems(boolean flag, int i)
    {
        int z = rand.nextInt(4);

        for (int k = 0; k < z; k++)
        {
            dropItem(mod_Ebon.sould.shiftedIndex, 1);
        }
    }
}
