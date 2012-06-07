package net.minecraft.src;

import java.util.List;
import java.util.Random;

import net.minecraft.src.forge.ArmorProperties;
import net.minecraft.src.forge.ISpecialArmor;
import net.minecraft.src.forge.ITextureProvider;

public class ItemEbonArmor extends ItemArmor implements ITextureProvider, ISpecialArmor
{
    private String[] armorEffectSuffixes = new String[]
    {
        "", "Nature", "Respiration", "Frost", "Velocity",
        "Obsidian Skin", "Revitalization", "Regeneration", "Leaping"
        //All Souls are handled in mod_Ebon.class (onTickInGame())
    };

    EnumArmorMaterial mat;

    public ItemEbonArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k)
    {
        super(i, enumarmormaterial, j, k);
        mat = enumarmormaterial;
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public void addInformation(ItemStack itemstack, List list)
    {
        if ((itemstack.itemID == mod_Ebon.ebonArmor_Hood.shiftedIndex || itemstack.itemID == mod_Ebon.ebonArmor_RobeTop.shiftedIndex) && itemstack.getItemDamage() != 0)
        {
            if (itemstack.itemID == mod_Ebon.ebonArmor_Hood.shiftedIndex)
            {
                list.add(new StringBuilder().append("Soul of ").append(armorEffectSuffixes[itemstack.getItemDamage() + 4]).toString());
            }

            if (itemstack.itemID == mod_Ebon.ebonArmor_RobeTop.shiftedIndex)
            {
                list.add(new StringBuilder().append("Soul of ").append(armorEffectSuffixes[itemstack.getItemDamage()]).toString());
            }
        }
    }

    public int getItemEnchantability()
    {
        return 25;
    }

    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.rare;
    }

    public String getTextureFile()
    {
        return "/vazkii/ebonmod/sprites.png";
    }

    public ArmorProperties getProperties(EntityLiving player, ItemStack armor,
            DamageSource source, double damage, int slot)
    {
        return new ArmorProperties(0, 0, 0);
    }

    public void damageArmor(EntityLiving entity, ItemStack stack,
            DamageSource source, int damage, int slot)
    {
        if (source.getEntity() instanceof EntityLiving && ((EntityLiving)source.getEntity()).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD)
        {
            if (new Random().nextInt(100) >= 90)
            {
                entity.heal(damage + damage / 4);
                ((EntityPlayer)entity).addChatMessage("Drain: " + (damage + damage / 4));
            }
        }
    }

    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        return mat.getDamageReductionAmount(slot);
    }
}
