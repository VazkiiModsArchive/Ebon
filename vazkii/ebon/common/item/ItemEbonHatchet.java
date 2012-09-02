package vazkii.ebon.common.item;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemAxe;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModReference;

public class ItemEbonHatchet extends ItemAxe {

	public ItemEbonHatchet(int par1) {
		super(par1, CommonUtils.<EnumToolMaterial> getEnumConstant("EBON", EnumToolMaterial.class));
	}

	@Override public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
		par1ItemStack.damageItem(1, par3EntityLiving);
		return true;
	}

	@Override public boolean func_77660_a(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving) {
		Random random = new Random();
		Block block = Block.blocksList[par2World.getBlockId(par4, par5, par6)];
		if (Arrays.asList(blocksEffectiveAgainst).contains(block) && random.nextInt(50) == 0 && !EbonModHelper.doesPlayerHaveME(par7EntityLiving)) {
			EbonModHelper.addShadeForPlayer((EntityPlayer) par7EntityLiving, EbonModReference.SHADE_TOOL_USE);
			CommonUtils.sendChatMessage((EntityPlayer) par7EntityLiving, "I seem to have found some Gunpowder.");
			par7EntityLiving.dropItem(Item.gunpowder.shiftedIndex, 1);
			par7EntityLiving.worldObj.playSoundAtEntity(par7EntityLiving, "ebonmod.tool", 1.0F, 1.0F);
			EbonModHelper.addMEToPlayer(par7EntityLiving, EbonModReference.ME_TOOL_USE);
		}

		return super.func_77660_a(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLiving);
	}

	@Override public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.block;
	}

	@Override public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	@Override public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

}
