package net.minecraft.src;

public class ItemMiniumCharm extends ItemEbonCharmBase
{
    public ItemMiniumCharm(int i)
    {
        super(i);
    }

    boolean isExtracting;
    int hasExtractedLastTick;

    private int storedXP(ItemStack itemstack)
    {
        return itemstack.stackTagCompound.getInteger("storedXP");
    }

    private void addXP(ItemStack itemstack, int i)
    {
        itemstack.stackTagCompound.setInteger("storedXP", storedXP(itemstack) + i);
    }

    private void setXP(ItemStack itemstack, int i)
    {
        itemstack.stackTagCompound.setInteger("storedXP", i);
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (hasMultiples(((EntityPlayer)par3Entity).inventory))
        {
            return;
        }

        if (isExtracting && par2World.getEntitiesWithinAABB(EntityXPOrb.class, AxisAlignedBB.getBoundingBox(par3Entity.posX - 1, par3Entity.posY - 1, par3Entity.posZ - 1, par3Entity.posX + 1, par3Entity.posY + 1, par3Entity.posZ + 1)).size() <= 5)
        {
            addXP(par1ItemStack, -1);
            hasExtractedLastTick = 20;
            par2World.spawnEntityInWorld(new EntityXPOrb(par2World, par3Entity.posX, par3Entity.posY, par3Entity.posZ, 1));

            if (storedXP(par1ItemStack) == 0)
            {
                isExtracting = false;
            }
        }

        if (par1ItemStack.stackTagCompound == null)
        {
            par1ItemStack.setTagCompound(new NBTTagCompound());
            setXP(par1ItemStack, 0);
        }

        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

        if (lastXPChange > 0 && !isExtracting && hasExtractedLastTick == 0)
        {
            ((EntityPlayer)par3Entity).addExperience(-lastXPChange);
            addXP(par1ItemStack, lastXPChange);
        }

        if (hasExtractedLastTick > 0)
        {
            hasExtractedLastTick -= 1;
        }
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (storedXP(par1ItemStack) > 0 && !isExtracting)
        {
            isExtracting = true;
            par2World.playSoundAtEntity(par3EntityPlayer, "vazkii.ebonmod.minium", 1.0F, 1.0F);
        }

        return par1ItemStack;
    }

    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return isExtracting;
    }
}
