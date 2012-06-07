package net.minecraft.src;

public class ItemCorpseDust extends ItemEbonMod
{
    public ItemCorpseDust(int i)
    {
        super(i);
    }

    public void useItemOnEntity(ItemStack itemstack, EntityLiving entityliving)
    {
        if (entityliving.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD && entityliving.getHealth() < entityliving.getMaxHealth())
        {
            entityliving.heal(6);
            entityliving.worldObj.playSoundAtEntity(entityliving, "mob.blaze.breathe", 0.1F, 1.0F);
            itemstack.stackSize--;
        }
    }
}
