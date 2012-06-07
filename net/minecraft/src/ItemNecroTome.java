package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class ItemNecroTome extends ItemEbonMod
{
    public ItemNecroTome(int i)
    {
        super(i);
        setMaxDamage(257);
        setMaxStackSize(1);
        setNoRepair();
    }

    private String[] messages = new String[]
    {
        "Chapter 1: Tormented Souls are know to be vulnerable to the hit of an ebon blade.",
        "Chapter 2: Phantom Chests don't vanish if they're unlocked.",
        "Chapter 3: Vazkii is one sneaky son of a sweetheart...",
        "Chapter 4: Corpse Dust is know to be reinvigorating to undead.",
        "Chapter 5: Ebon Staffs are know to pull out interesting information out of bookshelves.",
        "Chapter 6: Blood Leaves can harness mystical energy from the Ebon Staff.",
        "Chapter 7: Snow Golems resemble Villagers in a way right?",
        "Chapter 8: Ebon Armor can drain hits from undead.",
        "Chapter 9: Phantom Chests keep their chunk loaded.",
        "Chapter 10: Soul energy is know to create mushrooms in living beings",
        "Chapter 11: Legend tells that ancient wizards left powerful gems in phantom chests.",
        "Chapter 12: Those circle stone bricks must be able to be made somehow...",
        "Chapter 13: Minium and Plusium Charms seem to be immune to Magical Exhaustion.",
        "Chapter 14: The energy captured by the Vase of Souls must be able to be stored somewhere.",
        "Chapter 15: Wands of Retrieval and Imprisonment sometimes don't accept certain spawners."
    };

    private String[] colors = new String[] { "§c", "§9" };
    int color = 0;
    int lastMessage = 11;

    public boolean damageItemWithNotify(ItemStack itemstack, int i, EntityPlayer entityplayer)
    {
        if (itemstack.getItemDamage() != 256)
        {
            itemstack.damageItem(i, entityplayer);
            entityplayer.addChatMessage("Spell Successful.");
            return true;
        }

        entityplayer.addChatMessage("Spell Failed");
        return false;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par2World.playSoundAtEntity(par3EntityPlayer, "vazkii.ebonmod.tome", 1.0F, 1.0F);
        int m;

        do
        {
            m = new Random().nextInt(messages.length);
        }
        while (m == lastMessage);

        par3EntityPlayer.addChatMessage(colors[color] + messages[m]);
        lastMessage = m;

        if (color == 0)
        {
            color++;
        }
        else
        {
            color--;
        }

        return par1ItemStack;
    }

    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.rare;
    }

    public void addInformation(ItemStack itemstack, List list)
    {
        if (itemstack.getItemDamage() == 0)
        {
            list.add("Status: Pristine");
        }
        else if (itemstack.getItemDamage() <= 32)
        {
            list.add("Status: Mint");
        }
        else if (itemstack.getItemDamage() <= 64)
        {
            list.add("Status: Near Mint");
        }
        else if (itemstack.getItemDamage() <= 128)
        {
            list.add("Status: Very Good");
        }
        else if (itemstack.getItemDamage() <= 192)
        {
            list.add("Status: Good");
        }
        else if (itemstack.getItemDamage() <= 224)
        {
            list.add("Status: Moderate");
        }
        else if (itemstack.getItemDamage() == 256)
        {
            list.add("Status: Poor");
        }
    }
}
