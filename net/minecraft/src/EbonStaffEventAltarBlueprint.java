package net.minecraft.src;

public class EbonStaffEventAltarBlueprint extends EbonAPI_EbonStaffEvent
{
    public void doEventAt(World world, int xPos, int yPos, int zPos)
    {
        if (!ModLoader.getMinecraftInstance().thePlayer.inventory.addItemStackToInventory(new ItemStack(mod_Ebon.altarBlueprint, 1)))
        {
            ModLoader.getMinecraftInstance().thePlayer.dropItemWithOffset(mod_Ebon.altarBlueprint.shiftedIndex, 1, 0F);
        }
    }

    public boolean requiresTome()
    {
        return false;
    }

    public boolean canDoEvent(World world, int xPos, int yPos, int zPos)
    {
        return !ModLoader.getMinecraftInstance().thePlayer.inventory.hasItem(mod_Ebon.altarBlueprint.shiftedIndex);
    }
}
