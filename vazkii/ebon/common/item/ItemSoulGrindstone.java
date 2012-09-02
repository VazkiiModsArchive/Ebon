package vazkii.ebon.common.item;

import net.minecraft.src.ItemStack;

public class ItemSoulGrindstone extends ItemSpritesheet {

	public ItemSoulGrindstone(int par1) {
		super(par1);
		setMaxStackSize(1);
		setContainerItem(this);
	}

	@Override public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
		return false;
	}

}
