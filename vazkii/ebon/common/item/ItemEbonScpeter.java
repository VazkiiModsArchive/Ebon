package vazkii.ebon.common.item;

import vazkii.ebon.api.EbonAPIRegistry;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;

public class ItemEbonScpeter extends ItemSpritesheet {

	public ItemEbonScpeter(int par1) {
		super(par1);
		setMaxStackSize(1);
		setFull3D();
		if (getClass() != ItemEbonScpeter.class) EbonAPIRegistry.registerScepter(this);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return getClass() != ItemEbonScpeter.class ? EnumRarity.rare : super.getRarity(stack);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
