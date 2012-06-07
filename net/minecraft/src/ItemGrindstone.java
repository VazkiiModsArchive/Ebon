package net.minecraft.src;

public class ItemGrindstone extends ItemEbonMod
{
    public ItemGrindstone(int i)
    {
        super(i);
    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack ist)
    {
        return false;
    }
}
