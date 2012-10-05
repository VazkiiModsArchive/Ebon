package vazkii.ebon.common.item;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumCreatureAttribute;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.EntityTormentedSoul;
import vazkii.ebon.common.mod_Ebon;

public class ItemEbonBroadsword extends ItemSword {

	public ItemEbonBroadsword(int par1) {
		super(par1, CommonUtils.<EnumToolMaterial> getEnumConstant("EBON", EnumToolMaterial.class));
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
		if (par2EntityLiving.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) EbonModHelper.addShadeForPlayer((EntityPlayer) par3EntityLiving, EbonModReference.SHADE_SWORD_KILL);

		int level;
		if ((level = EnchantmentHelper.getEnchantmentLevel(mod_Ebon.venomTouch.effectId, par1ItemStack)) > 0) par2EntityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 60 * level, 0));

		par1ItemStack.damageItem(1, par3EntityLiving);
		return true;
	}

	@Override
	public int getDamageVsEntity(Entity par1Entity) {
		if (par1Entity instanceof EntityTormentedSoul) return 4000;

		if (par1Entity instanceof EntityLiving && ((EntityLiving) par1Entity).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
			((EntityLiving) par1Entity).attackEntityFrom(DamageSource.causeIndirectMagicDamage(par1Entity, par1Entity), ((EntityLiving) par1Entity).getHealth());
			return 1;
		}

		return super.getDamageVsEntity(par1Entity);
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

}
