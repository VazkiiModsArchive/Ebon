package net.minecraft.src;

import java.util.Random;

import net.minecraft.client.Minecraft;

/**
 * @see ItemEbonScepterInfinity
 * @see ItemEbonScepterZero
 * @see ItemEbonScepterVoid
 *
 */
public class ItemEbonScepter extends Ebon3DItems
{
    public ItemEbonScepter(int i, EnumRarity er)
    {
        super(i, er);
    }

    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return !EbonAPI.doesPlayerHaveMagicExhaustion();
    }
}
