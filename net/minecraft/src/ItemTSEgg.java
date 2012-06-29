package net.minecraft.src;

import java.util.HashMap;

public class ItemTSEgg extends Item
{
    String spawn;
    int color1;
    int color2;
    public ItemTSEgg(int i, String s, int c, int c2)
    {
        super(i);
        setMaxStackSize(1);
        setHasSubtypes(true);
        setIconCoord(9, 9);
        spawn = s;
        color1 = c;
        color2 = c2;
    }
    
    public ItemTSEgg(int i, String s, int c)
    {
    	this(i, s, c, 0x191919);
    }

    public int getColorFromDamage(int i, int j)
    {
        if (j == 0)
        {
            return color1;
        }

        return color2;
    }

    public boolean func_46058_c()
    {
        return true;
    }

    public int func_46057_a(int i, int j)
    {
        if (j > 0)
        {
            return super.func_46057_a(i, j) + 16;
        }
        else
        {
            return super.func_46057_a(i, j);
        }
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        i += Facing.offsetsXForSide[l];
        j += Facing.offsetsYForSide[l];
        k += Facing.offsetsZForSide[l];
        EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName(spawn, world);
        entityliving.setLocationAndAngles(i, j + 1, k, world.rand.nextFloat() * 360F, 0.0F);
        world.spawnEntityInWorld(entityliving);
        return true;
    }
}
