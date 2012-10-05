package vazkii.ebon.common.item;

import net.minecraft.src.EntityLightningBolt;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.WorldClient;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModHooks;
import vazkii.ebon.common.EbonModReference;

public class ItemInfinityScepter extends ItemEbonScpeter {

	public ItemInfinityScepter(int par1) {
		super(par1);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving) {
		if (par2EntityLiving.worldObj instanceof WorldClient) return true;

		EntityPlayer player = EbonModHooks.getInteractingPlayer();
		if (EbonModHelper.doesPlayerHaveLexicon(player) && !EbonModHelper.doesPlayerHaveME(player) && EbonModHelper.isDarknessEnough(player, EbonModReference.DARKNESS_MIN_SCEPTER)) {
			par2EntityLiving.worldObj.addWeatherEffect(new EntityLightningBolt(par2EntityLiving.worldObj, par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ));
			par2EntityLiving.worldObj.playSoundEffect(par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ, "ebonmod.spell", 1.0F, 1.0F);
			EbonModHelper.addMEToPlayer(player, EbonModReference.ME_SCEPTER);
			EbonModHelper.addShadeForPlayer(player, EbonModReference.SHADE_SCEPTER);
		} else par2EntityLiving.worldObj.playSoundEffect(par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ, "ebonmod.fail", 1.0F, 1.0F);
		return true;
	}

}
