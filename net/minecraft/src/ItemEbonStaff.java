package net.minecraft.src;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.Random;

/**
 * @see EbonAPI_EbonStaffEvent
 */
public class ItemEbonStaff extends Ebon3DItems
{
    public ItemEbonStaff(int i, int j)
    {
        super(i, EnumRarity.rare);
        maxStackSize = 1;
        setMaxDamage(j);
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        int i1 = world.getBlockId(i, j, k);
        Block block;
        double d = entityplayer.posX;
        double d1 = entityplayer.posY;
        double d2 = entityplayer.posZ;

        if (EbonAPI.esEvents.containsKey(i1) && !EbonAPI.doesPlayerHaveMagicExhaustion() && EbonAPI.esEvents.get(i1).canDoEvent(world, i, j, k))
        {
            if (EbonAPI.esEvents.get(i1).requiresTome() && (!EbonAPI.hasNecroTome() || !((ItemNecroTome)EbonAPI.getNecroTome().getItem()).damageItemWithNotify(EbonAPI.getNecroTome(), 1, entityplayer)))
            {
                entityplayer.worldObj.playSoundEffect(d, d1, d2, "vazkii.ebonmod.fail", 1.0F, 1.0F);
                return false;
            }

            EbonAPI_EbonStaffEvent event = EbonAPI.esEvents.get(i1);
            event.doEventAt(world, i, j, k);
            doDamageCalc(itemstack, entityplayer);
            doParticles(world, i, j, k);
            entityplayer.worldObj.playSoundEffect(d, d1, d2, "vazkii.ebonmod.transmute", 1.0F, 1.0F);
            EbonAPI.addMagicalExhaustionOnPlayerFor(2);
            return true;
        }

        entityplayer.worldObj.playSoundEffect(d, d1, d2, "vazkii.ebonmod.fail", 1.0F, 1.0F);
        return false;
    }

    public static void doParticles(World world, int i, int j, int k)
    {
        Random rand = new Random();

        for (int l = 0; l < 100; l++)
        {
            float f = (float)i + rand.nextFloat();
            float f2 = (float)j + rand.nextFloat() * 0.5F + 0.5F;
            float f3 = (float)k + rand.nextFloat();
            world.spawnParticle("portal", f, f2, f3, 0.0D, 0.0D, 0.0D);
        }
    }

    private void doDamageCalc(ItemStack itemstack, EntityPlayer entityplayer)
    {
        itemstack.damageItem(1, entityplayer);

        if (itemstack.getItemDamage() == 9)
        {
            itemstack.damageItem(1, entityplayer);

            if (!entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_Ebon.ebonstaffu)))
            {
                entityplayer.dropItemWithOffset(mod_Ebon.ebonstaffu.shiftedIndex, 1, 0.0F);
            }

            entityplayer.addChatMessage("The Staff seems to be uncharged.");
        }
    }

    public void addInformation(ItemStack itemstack, List list)
    {
        list.add(new StringBuilder().append("Uses: ").append(9 - itemstack.getItemDamageForDisplay()).append("/9").toString());
    }

    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return !EbonAPI.doesPlayerHaveMagicExhaustion();
    }
}
