// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            Item, ModLoader, EntityLiving, EntityEbonGhost, 
//            ItemStack, EntityPlayer, World, InventoryPlayer, 
//            mod_Ebon

public class ItemOrbOfSouls extends ItemEbonMod
{

    protected boolean canRepair = true;
    private int intToDamage;
    private int thisDmg;
    private Minecraft mc;

    public ItemOrbOfSouls(int i, int j)
    {
        super(i);
        mc = ModLoader.getMinecraftInstance();
        intToDamage = j;
        setMaxDamage(j);
        setMaxStackSize(1);
    }

    public void useItemOnEntity(ItemStack itemstack, EntityLiving entityliving)
    {
        double d = entityliving.posX;
        double d1 = entityliving.posY;
        double d2 = entityliving.posZ;
        EntityPlayerSP entityplayersp = mc.thePlayer;
        if(entityliving instanceof EntityEbonGhost && !EbonAPI.doesPlayerHaveMagicExhaustion())
        {
            if(!itemstack.isItemDamaged())
            {
                itemstack.damageItem(intToDamage, entityliving);
            }
            if(itemstack.isItemDamaged())
            {
                Random random = new Random();
                for(int i = 0; i < 1000; i++)
                {
                    entityliving.worldObj.spawnParticle("portal", entityliving.posX + (random.nextDouble() - 0.5D) * (double)entityliving.width, (entityliving.posY + random.nextDouble() * (double)entityliving.height) - 0.25D, entityliving.posZ + (random.nextDouble() - 0.5D) * (double)entityliving.width, (random.nextDouble() - 0.5D) * 2D, -random.nextDouble(), (random.nextDouble() - 0.5D) * 2D);
                }

                entityliving.worldObj.playSoundEffect(d, d1, d2, "mob.blaze.breathe", 1.0F, 1.0F);
                entityliving.setDead();
                thisDmg = itemstack.getItemDamage();
                thisDmg--;
                itemstack.setItemDamage(thisDmg);
                if(!itemstack.isItemDamaged())
                {
                    itemstack.damageItem(intToDamage + 1, entityliving);
                    ItemStack itemstack1 = ((EntityPlayer) (entityplayersp)).inventory.getCurrentItem();
                    itemstack1 = new ItemStack(mod_Ebon.soulorbc);
                    if(!((EntityPlayer) (entityplayersp)).inventory.addItemStackToInventory(itemstack1))
                    	entityliving.dropItemWithOffset(mod_Ebon.soulorb.shiftedIndex, 1, 0.0F);
                    entityplayersp.addChatMessage("The Orb seems to be fully charged.");
                }
            } else
            {
                entityliving.worldObj.playSoundEffect(d, d1, d2, "mob.endermen.portal", 1.0F, 1.0F);
            }
        } else
        {
            entityliving.worldObj.playSoundEffect(d, d1, d2, "mob.endermen.portal", 1.0F, 1.0F);
        }
        if(!(entityliving instanceof EntityEbonGhost))
        {
            entityplayersp.addChatMessage("The Orb seems to reject this soul.");
        }
    }
    
    public void addInformation(ItemStack itemstack, List list)
    {
    	if(itemstack.getItemDamageForDisplay() == 0)
        list.add("No Souls");
    	else
    	list.add(new StringBuilder().append("Souls: ").append(20 - itemstack.getItemDamageForDisplay()).append("/20").toString());
    }
}
