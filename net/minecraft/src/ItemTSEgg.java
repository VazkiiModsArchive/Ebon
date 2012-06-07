package net.minecraft.src;

import java.util.HashMap;

public class ItemTSEgg extends Item
{
    String spawn;
    int color2;
    public ItemTSEgg(int i, String s, int c)
    {
        super(i);
        setMaxStackSize(1);
        setHasSubtypes(true);
        spawn = s;
        color2 = c;
    }

    public int getColorFromDamage(int i, int j)
    {
        if (j == 0)
        {
            return 0x191919;
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
