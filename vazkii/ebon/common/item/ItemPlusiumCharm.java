package vazkii.ebon.common.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;

public class ItemPlusiumCharm extends ItemCharmBase {

	public ItemPlusiumCharm(int par1) {
		super(par1);
	}

	private double getXP(ItemStack itemstack) {
		return itemstack.getTagCompound().hasKey("accXP") ? itemstack.getTagCompound().getDouble("accXP") : 0D;
	}

	private void addXP(ItemStack itemstack, double d) {
		setXP(itemstack, getXP(itemstack) + d);
	}

	private void setXP(ItemStack itemstack, double d) {
		itemstack.getTagCompound().setDouble("accXP", d);
	}

	@Override public void onXPChange(EntityPlayer player, ItemStack stack, int change) {
		if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());

		addXP(stack, (double) change / 4);
		if (getXP(stack) >= 1) {
			player.addExperience((int) Math.floor(getXP(stack)));
			addXP(stack, -Math.floor(getXP(stack)));
		}
	}

}
