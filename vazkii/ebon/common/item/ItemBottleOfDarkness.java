package vazkii.ebon.common.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemExpBottle;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModReference;

public class ItemBottleOfDarkness extends ItemExpBottle {

	public ItemBottleOfDarkness(int par1) {
		super(par1);
	}

	@Override
	public int getColorFromDamage(int par1, int par2) {
		return 0x3F0037;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (EbonModHelper.doesPlayerHaveME(par3EntityPlayer) || !EbonModHelper.isDarknessEnough(par3EntityPlayer, EbonModReference.DARKNESS_MIN_VASE_SOULS)) return par1ItemStack;

		int var2 = 3 + par2World.rand.nextInt(5) + par2World.rand.nextInt(5);
		EbonModHelper.addShadeForPlayer(par3EntityPlayer, (int) Math.round(var2 * EbonModReference.SHADE_BOTTLE_MODIFIER));

		return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
	}

}
