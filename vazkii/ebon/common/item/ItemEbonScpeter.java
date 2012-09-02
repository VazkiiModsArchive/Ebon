package vazkii.ebon.common.item;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import vazkii.ebon.api.EbonAPIRegistry;

public class ItemEbonScpeter extends ItemSpritesheet {

	public ItemEbonScpeter(int par1) {
		super(par1);
		setMaxStackSize(1);
		setFull3D();
		if (getClass() != ItemEbonScpeter.class) EbonAPIRegistry.registerScepter(this);
	}

	@Override public EnumRarity getRarity(ItemStack stack) {
		return getClass() != ItemEbonScpeter.class ? EnumRarity.rare : super.getRarity(stack);
	}

	@Override public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
