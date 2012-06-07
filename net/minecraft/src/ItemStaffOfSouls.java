package net.minecraft.src;

import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.Random;

/**
 * @see EbonAPI_StaffOfSoulsEvent
 */
public class ItemStaffOfSouls extends Ebon3DItems
{
    public ItemStaffOfSouls(int i, int j)
    {
        super(i, EnumRarity.rare);
        maxStackSize = 1;
        setMaxDamage(j);
    }

    private Minecraft mc = ModLoader.getMinecraftInstance();

    public void useItemOnEntity(ItemStack itemstack, EntityLiving entityliving)
    {
        double d = entityliving.posX;
        double d1 = entityliving.posY;
        double d2 = entityliving.posZ;
        EntityPlayer ep = mc.thePlayer;

        if (entityliving != null && !entityliving.isDead)
        {
            if (EbonAPI.sosEvents.containsKey(entityliving.getEntityString()) && !EbonAPI.doesPlayerHaveMagicExhaustion())
            {
                if (EbonAPI.sosEvents.get(entityliving.getEntityString()).requiresTome() && (!EbonAPI.hasNecroTome() || !((ItemNecroTome)EbonAPI.getNecroTome().getItem()).damageItemWithNotify(EbonAPI.getNecroTome(), 1, (EntityPlayer)entityliving)))
                {
                    entityliving.worldObj.playSoundEffect(d, d1, d2, "vazkii.ebonmod.fail", 1.0F, 1.0F);
                    return;
                }

                EbonAPI_StaffOfSoulsEvent event = EbonAPI.sosEvents.get(entityliving.getEntityString());
                event.doEventAt(entityliving.worldObj, entityliving);
                Random rand = new Random();

                for (int z = 0; z < 1000; z++)
                {
                    entityliving.worldObj.spawnParticle("portal", entityliving.posX + (rand.nextDouble() - 0.5D) * (double)entityliving.width, (entityliving.posY + rand.nextDouble() * (double)entityliving.height) - 0.25D, entityliving.posZ + (rand.nextDouble() - 0.5D) * (double)entityliving.width, (rand.nextDouble() - 0.5D) * 2D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2D);
                }

                if (mc.theWorld.worldInfo.getGameType() != 1)
                {
                    itemstack.damageItem(1, ep);
                }

                entityliving.worldObj.playSoundEffect(d, d1, d2, "vazkii.ebonmod.transmute", 1.0F, 1.0F);

                if (itemstack.getItemDamage() == 9)
                {
                    itemstack.damageItem(1, ep);

                    if (!ep.inventory.addItemStackToInventory(new ItemStack(mod_Ebon.soulstaffu)))
                    {
                        entityliving.dropItemWithOffset(mod_Ebon.soulstaffu.shiftedIndex, 1, 0.0F);
                    }

                    ep.addChatMessage("The Staff seems to be uncharged.");
                }

                EbonAPI.addMagicalExhaustionOnPlayerFor(5);
                return;
            }

            entityliving.worldObj.playSoundEffect(d, d1, d2, "vazkii.ebonmod.fail", 1.0F, 1.0F);
            ep.addChatMessage("I can't seem to be able to polymorph this.");
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
