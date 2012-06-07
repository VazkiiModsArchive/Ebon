package net.minecraft.src;

public class ItemEbonCharmBase extends ItemEbonMod
{
    int lastTickXP = 0;
    int nowXP = 0;
    int lastXPChange;
    boolean firstTick = true;

    public ItemEbonCharmBase(int i)
    {
        super(i);
    }

    int getLastXPChange(EntityPlayer player)
    {
        return nowXP - lastTickXP;
    }

    boolean hasMultiples(InventoryPlayer inv)
    {
        if (inv == null)
        {
            return false;
        }

        boolean hasFound = false;

        for (int i = 0; i < inv.mainInventory.length; i++)
        {
            if (inv.mainInventory[i] == null)
            {
                continue;
            }

            if (inv.mainInventory[i].isStackEqual(new ItemStack(mod_Ebon.plusiumCharm)) || inv.mainInventory[i].isStackEqual(new ItemStack(mod_Ebon.miniumCharm)))
            {
                if (hasFound)
                {
                    return true;
                }

                hasFound = true;
            }
        }

        return false;
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (par3Entity instanceof EntityPlayer)
        {
            double xpCalc;
            EntityPlayer player = (EntityPlayer)par3Entity;
            nowXP = player.experienceTotal;

            if (firstTick)
            {
                lastTickXP = player.experienceTotal;
                firstTick = false;
                return;
            }

            if (lastTickXP != nowXP)
            {
                lastXPChange = getLastXPChange(player);
            }
            else
            {
                lastXPChange = 0;
            }

            lastTickXP = player.experienceTotal;
        }
    }

    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.rare;
    }
}
