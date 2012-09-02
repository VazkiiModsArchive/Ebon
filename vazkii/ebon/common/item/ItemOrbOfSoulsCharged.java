package vazkii.ebon.common.item;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;

public class ItemOrbOfSoulsCharged extends ItemSpritesheet {

	public ItemOrbOfSoulsCharged(int par1) {
		super(par1);
	}

	@Override public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

}
