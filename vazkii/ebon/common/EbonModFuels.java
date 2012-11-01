package vazkii.ebon.common;

import net.minecraft.src.ItemStack;

import cpw.mods.fml.common.IFuelHandler;

public class EbonModFuels implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		return fuel.itemID == mod_Ebon.ebonCoal.shiftedIndex ? 6400 : 0;
	}

}
