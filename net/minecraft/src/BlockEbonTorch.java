package net.minecraft.src;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

import net.minecraft.src.forge.ITextureProvider;

public class BlockEbonTorch extends BlockTorch implements ITextureProvider{

	protected BlockEbonTorch(int i, int j) {
		super(i, j);
	}
	
	public String getTextureFile() {
		return "/vazkii/ebonmod/sprites.png";
	}
	
	private boolean isIndirectlyPowered(World world, int i, int j, int k, int l)
    {
        if (l != 0 && world.isBlockIndirectlyProvidingPowerTo(i, j - 1, k, 0))
        {
            return true;
        }
        if (l != 1 && world.isBlockIndirectlyProvidingPowerTo(i, j + 1, k, 1))
        {
            return true;
        }
        if (l != 2 && world.isBlockIndirectlyProvidingPowerTo(i, j, k - 1, 2))
        {
            return true;
        }
        if (l != 3 && world.isBlockIndirectlyProvidingPowerTo(i, j, k + 1, 3))
        {
            return true;
        }
        if (l != 5 && world.isBlockIndirectlyProvidingPowerTo(i + 1, j, k, 5))
        {
            return true;
        }
        if (l != 4 && world.isBlockIndirectlyProvidingPowerTo(i - 1, j, k, 4))
        {
            return true;
        }
        if (world.isBlockIndirectlyProvidingPowerTo(i, j, k, 0))
        {
            return true;
        }
        if (world.isBlockIndirectlyProvidingPowerTo(i, j + 2, k, 1))
        {
            return true;
        }
        if (world.isBlockIndirectlyProvidingPowerTo(i, j + 1, k - 1, 2))
        {
            return true;
        }
        if (world.isBlockIndirectlyProvidingPowerTo(i, j + 1, k + 1, 3))
        {
            return true;
        }
        if (world.isBlockIndirectlyProvidingPowerTo(i - 1, j + 1, k, 4))
        {
            return true;
        }
        return world.isBlockIndirectlyProvidingPowerTo(i + 1, j + 1, k, 5);
    }
	
    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        int l = world.getBlockMetadata(i, j, k);
        double d = (float)i + 0.5F;
        double d1 = (float)j + 0.7F;
        double d2 = (float)k + 0.5F;
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;
        if (l == 1)
        {
            world.spawnParticle("smoke", d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            if(!isIndirectlyPowered(world, i, j, k, l))
            world.spawnParticle("portal", d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        }
        else if (l == 2)
        {
            world.spawnParticle("smoke", d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
            if(!isIndirectlyPowered(world, i, j, k, l))
            world.spawnParticle("portal", d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
        }
        else if (l == 3)
        {
            world.spawnParticle("smoke", d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
            if(!isIndirectlyPowered(world, i, j, k, l))
            world.spawnParticle("portal", d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
        }
        else if (l == 4)
        {
            world.spawnParticle("smoke", d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
            if(!isIndirectlyPowered(world, i, j, k, l))
            world.spawnParticle("portal", d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
        }
        else
        {
            world.spawnParticle("smoke", d, d1, d2, 0.0D, 0.0D, 0.0D);
            if(!isIndirectlyPowered(world, i, j, k, l))
            world.spawnParticle("portal", d, d1, d2, 0.0D, 0.0D, 0.0D);
        }
        
        List list = world.getEntitiesWithinAABB(net.minecraft.src.EntityLiving.class, AxisAlignedBB.getBoundingBoxFromPool(i, j, k, i + 1.0D, j + 1.0D, k + 1.0D).expand(3D, 3D, 3D));
        Iterator iterator = list.iterator();
        while(iterator.hasNext())
        {	
            Entity entity = (Entity)iterator.next();
            EntityLiving entityliving = (EntityLiving)entity;
            if(!(entityliving instanceof EntityPlayer) && random.nextInt(4) == 0 && !isIndirectlyPowered(world, i, j, k, l)){
            	entityliving.attackEntityFrom(DamageSource.magic, 1);
            	entityliving.worldObj.spawnParticle("portal", entityliving.posX + (random.nextDouble() - 0.5D) * (double)entityliving.width, (entityliving.posY + random.nextDouble() * (double)entityliving.height) - 0.25D, entityliving.posZ + (random.nextDouble() - 0.5D) * (double)entityliving.width, (random.nextDouble() - 0.5D) * 2D, -random.nextDouble(), (random.nextDouble() - 0.5D) * 2D);
            }
        }
    }

}
