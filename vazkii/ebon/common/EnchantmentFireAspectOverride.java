package vazkii.ebon.common;

import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentFireAspect;

public class EnchantmentFireAspectOverride extends EnchantmentFireAspect {

	protected EnchantmentFireAspectOverride(int par1, int par2) {
		super(par1, par2);
	}

	@Override public boolean canApplyTogether(Enchantment par1Enchantment) {
		return !(par1Enchantment instanceof EnchantmentVenomTouch) && super.canApplyTogether(par1Enchantment);
	}

}
