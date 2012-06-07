package net.minecraft.src;

public class ItemSoulStone extends ItemEbonMod
{
    public ItemSoulStone(int i)
    {
        super(i);
        setHasSubtypes(true);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if (itemstack.getItemDamage() == 0)
        {
            return new ItemStack(itemstack.getItem(), itemstack.stackSize, 1);
        }

        return new ItemStack(itemstack.getItem(), itemstack.stackSize, 0);
    }

    public boolean hasEffect(ItemStack itemstack)
    {
        return itemstack.getItemDamage() == 1;
    }
}
