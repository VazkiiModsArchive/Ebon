package net.minecraft.src;

import java.util.List;

public class ItemPlusiumCharm extends ItemEbonCharmBase
{
    public ItemPlusiumCharm(int i)
    {
        super(i);
    }

    private double accXP(ItemStack itemstack)
    {
        return itemstack.stackTagCompound.getDouble("accXP");
    }

    private void addXP(ItemStack itemstack, double d)
    {
        itemstack.stackTagCompound.setDouble("accXP", accXP(itemstack) + d);
    }

    private void setXP(ItemStack itemstack, double d)
    {
        itemstack.stackTagCompound.setDouble("accXP", d);
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (hasMultiples(((EntityPlayer)par3Entity).inventory))
        {
            return;
        }

        if (par1ItemStack.stackTagCompound == null)
        {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }

        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

        if (lastXPChange > 0)
        {
            addXP(par1ItemStack, (double)lastXPChange / 4);

            if (accXP(par1ItemStack) >= 1)
            {
                ((EntityPlayer)par3Entity).addExperience((int)Math.floor(accXP(par1ItemStack)));
                addXP(par1ItemStack, -Math.floor(accXP(par1ItemStack)));
            }
        }
    }
}
