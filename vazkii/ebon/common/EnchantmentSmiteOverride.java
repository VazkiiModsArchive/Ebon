package vazkii.ebon.common;

import net.minecraft.src.EnchantmentDamage;
import net.minecraft.src.ItemStack;

public class EnchantmentSmiteOverride extends EnchantmentDamage {

	public EnchantmentSmiteOverride(int par1, int par2, int par3) {
		super(par1, par2, par3);
	}

	@Override
	public boolean canEnchantItem(ItemStack item) {
		return item.itemID == mod_Ebon.ebonBroadsword.shiftedIndex ? false : super.canEnchantItem(item);
	}

}
