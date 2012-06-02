
package net.minecraft.src;

import java.util.Random;

public class ItemSoulPowder extends ItemEbonMod
{

    public ItemSoulPowder(int i)
    {
        super(i);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	int j = drop.nextInt(100);
    	int j2 = drop2.nextInt(3);
        if(j > 97)
        {
            if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_Ebon.staffshard)))
            	entityplayer.dropItemWithOffset(mod_Ebon.staffshard.shiftedIndex, 1, 0.0F);
            entityplayer.addChatMessage("I seem to have gotten a strange shard.");
            world.playSoundAtEntity(entityplayer, "random.levelup", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        } else
        if(j > 90)
        {
        if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_Ebon.ebongem)))
            	entityplayer.dropItemWithOffset(mod_Ebon.ebongem.shiftedIndex, 1, 0.0F);
        entityplayer.addChatMessage("I seem to have gotten an ebon gem.");
        world.playSoundAtEntity(entityplayer, "random.orb", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        } else
        if(j > 80)
        {
        	if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_Ebon.deaddust)))
        	entityplayer.dropItemWithOffset(mod_Ebon.deaddust.shiftedIndex, 1, 0.0F);
            entityplayer.addChatMessage("I seem to have gotten some undead essence.");
        } else
        if(j2 == 1)
        {
        	if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(Item.bone)))
        	entityplayer.dropItemWithOffset(Item.bone.shiftedIndex, 1, 0.0F);
            entityplayer.addChatMessage("I seem to have gotten a bone.");
        } else
        if(j2 == 2)
        {
        	if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_Ebon.corpsedust)))
        	entityplayer.dropItemWithOffset(mod_Ebon.corpsedust.shiftedIndex, 1, 0.0F);
            entityplayer.addChatMessage("I seem to have gotten some corpse dust.");
        } else
        {
        	if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(Item.gunpowder)))
        	entityplayer.dropItemWithOffset(Item.gunpowder.shiftedIndex, 1, 0.0F);
            entityplayer.addChatMessage("I seem to have gotten some gunpowder.");
        }
           
        itemstack.stackSize--;
        return itemstack;
    }

    private static Random drop = new Random();
    private static Random drop2 = new Random();

}
