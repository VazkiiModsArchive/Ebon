package vazkii.ebon.common.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;
import net.minecraftforge.common.MinecraftForge;
import vazkii.ebon.api.event.EbonStaffEvent;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.mod_Ebon;

public class ItemEbonStaff extends ItemSpritesheet {

	public ItemEbonStaff(int par1) {
		super(par1);
		setFull3D();
		setMaxStackSize(1);
		setNoRepair();
		setMaxDamage(EbonModReference.EBON_STAFF_DAMAGE);
	}

	@Override public boolean tryPlaceIntoWorld(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par3World instanceof WorldClient) return true;

		double d = par2EntityPlayer.posX;
		double d1 = par2EntityPlayer.posY;
		double d2 = par2EntityPlayer.posZ;

		if (EbonModHelper.doesPlayerHaveME(par2EntityPlayer) || par3World instanceof WorldClient || !EbonModHelper.isDarknessEnough(par2EntityPlayer, EbonModReference.DARKNESS_MIN_STAFF)) return true;

		if (MinecraftForge.EVENT_BUS.post(new EbonStaffEvent(par3World, par2EntityPlayer, par1ItemStack, par4, par5, par6))) {
			par3World.playSoundEffect(d, d1, d2, "ebonmod.transmute", 1.0F, 1.0F);
			EbonModHelper.addMEToPlayer(par2EntityPlayer, EbonModReference.ME_STAFF);
			EbonModHelper.addShadeForPlayer(par2EntityPlayer, EbonModReference.SHADE_STAFF);

			for (int l = 0; l < EbonModReference.PARTICLE_COUNT; l++) {
				double p = par4 + par3World.rand.nextDouble();
				double p1 = par5 + par3World.rand.nextDouble() * 0.5F + 0.5F;
				double p2 = par6 + par3World.rand.nextDouble();
				EbonModPacketHandler.sendParticlePacket("portal", p, p1, p2, 0.0D, 0.0D, 0.0D, true);
			}

			par1ItemStack.damageItem(1, par2EntityPlayer);

			if (par1ItemStack.getItemDamage() == EbonModReference.EBON_STAFF_DAMAGE) {
				ItemStack dropStack = new ItemStack(mod_Ebon.ebonStaff);
				par1ItemStack.damageItem(1, par2EntityPlayer);
				if (!par2EntityPlayer.inventory.addItemStackToInventory(dropStack)) par2EntityPlayer.dropPlayerItem(dropStack);
			}
		}
		else par3World.playSoundEffect(d, d1, d2, "ebonmod.fail", 1.0F, 1.0F);
		return false;
	}

	@Override public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

	@Override public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
