package vazkii.ebon.common;

import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentFireAspect;
import net.minecraft.src.EnumEnchantmentType;
import net.minecraft.src.ItemStack;

public class EnchantmentVenomTouch extends Enchantment {

	protected EnchantmentVenomTouch(int par1, int par2) {
		super(par1, par2, EnumEnchantmentType.weapon);
	}

	@Override public int getMinEnchantability(int par1) {
		return 10 + 20 * (par1 - 1);
	}

	@Override public int getMaxEnchantability(int par1) {
		return super.getMinEnchantability(par1) + 50;
	}

	@Override public int getMaxLevel() {
		return 2;
	}

	@Override public boolean canApplyTogether(Enchantment par1Enchantment) {
		return !(par1Enchantment instanceof EnchantmentFireAspect) && super.canApplyTogether(par1Enchantment);
	}

	@Override public boolean canEnchantItem(ItemStack item) {
		return item.itemID == mod_Ebon.ebonBroadsword.shiftedIndex;
	}

}
