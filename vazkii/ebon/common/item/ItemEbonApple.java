package vazkii.ebon.common.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.World;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModReference;

public class ItemEbonApple extends ItemFood {

	public ItemEbonApple(int par1) {
		super(par1, par1, false);
		setAlwaysEdible();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (EbonModHelper.isDarknessEnough(par3EntityPlayer, EbonModReference.DARKNESS_MIN_APPLE)) par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));

		return par1ItemStack;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.epic;
	}

	@Override
	protected void func_77849_c(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 12000, 3));
		par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 12000, 0));
		par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 12000, 0));
		par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 12000, 0));
		par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 12000, 0));
		par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 12000, 0));
		EbonModHelper.addMEToPlayer(par3EntityPlayer, EbonModReference.ME_APPLE);
		EbonModHelper.addShadeForPlayer(par3EntityPlayer, EbonModReference.SHADE_APPLE);
		par3EntityPlayer.heal(20);
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}
}
