// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;
import net.minecraft.src.forge.ITextureProvider;


// Referenced classes of package net.minecraft.src:
//            BlockSand, AxisAlignedBB, Entity, World

public class BlockQuicksand extends BlockSand implements ITextureProvider
{

    public BlockQuicksand(int i, int j)
    {
        super(i, j);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        float f = 0.8F;
        return AxisAlignedBB.getBoundingBoxFromPool(i, j, k, i + 1, (float)(j + 1) - f, k + 1);
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
    	if(entity instanceof EntityLiving){
        entity.motionX *= 0.32000000000000001D;
        entity.motionZ *= 0.32000000000000017D;
    	}
    }
    
	public String getTextureFile() {
		return "/vazkii/ebonmod/sprites.png";
	}
}
