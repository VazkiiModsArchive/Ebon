package vazkii.ebon.common.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModReference;

public class ItemSoulStone extends ItemSpritesheet {

	public ItemSoulStone(int par1) {
		super(par1);
		setMaxStackSize(1);
		setHasSubtypes(true);
	}

	@Override public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		itemstack.setItemDamage(EbonModHelper.isDarknessEnough(entityplayer, EbonModReference.DARKNESS_MIN_ARMOR) ? itemstack.getItemDamage() == 0 ? 1 : 0 : 0);
		return itemstack;
	}

	@Override public boolean hasEffect(ItemStack stack) {
		return stack.getItemDamage() == 1;
	}
}
