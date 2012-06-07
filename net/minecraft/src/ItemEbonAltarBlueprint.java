package net.minecraft.src;

public class ItemEbonAltarBlueprint extends Item
{
    public ItemEbonAltarBlueprint(int par1)
    {
        super(par1);
        setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par2World.playSoundAtEntity(par3EntityPlayer, "vazkii.ebonmod.tome", 1.0F, 1.0F);
        ModLoader.openGUI(par3EntityPlayer, new GuiEbonAltarBlueprint());
        return par1ItemStack;
    }

    public boolean hasEffect()
    {
        return true;
    }
}
