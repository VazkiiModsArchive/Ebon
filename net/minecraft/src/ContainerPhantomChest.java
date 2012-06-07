package net.minecraft.src;

public class ContainerPhantomChest extends Container
{
    private int numRows;
    private IInventory inv;

    public ContainerPhantomChest(IInventory par1IInventory, IInventory par2IInventory)
    {
        this.numRows = par2IInventory.getSizeInventory() / 9;
        inv = par2IInventory;
        par2IInventory.openChest();
        int var3 = (this.numRows - 4) * 18;
        int var4;
        int var5;

        for (var4 = 0; var4 < this.numRows; ++var4)
        {
            for (var5 = 0; var5 < 9; ++var5)
            {
                this.addSlot(new SlotPhantomChest(par2IInventory, var5 + var4 * 9, 8 + var5 * 18, 18 + var4 * 18));
            }
        }

        for (var4 = 0; var4 < 3; ++var4)
        {
            for (var5 = 0; var5 < 9; ++var5)
            {
                this.addSlot(new Slot(par1IInventory, var5 + var4 * 9 + 9, 8 + var5 * 18, 103 + var4 * 18 + var3));
            }
        }

        for (var4 = 0; var4 < 9; ++var4)
        {
            this.addSlot(new Slot(par1IInventory, var4, 8 + var4 * 18, 161 + var3));
        }
    }

    public ItemStack transferStackInSlot(int par1)
    {
        ItemStack var2 = null;
        Slot var3 = (Slot)this.inventorySlots.get(par1);

        if (var3 != null && var3.getHasStack())
        {
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (par1 < this.numRows * 9)
            {
                if (!this.mergeItemStack(var4, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else
            {
                return null;
            }

            if (var4.stackSize == 0)
            {
                var3.putStack((ItemStack)null);
            }
            else
            {
                var3.onSlotChanged();
            }
        }

        return var2;
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return inv.isUseableByPlayer(var1);
    }
}
