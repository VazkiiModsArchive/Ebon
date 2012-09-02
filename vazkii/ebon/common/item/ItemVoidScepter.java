package vazkii.ebon.common.item;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import net.minecraft.src.EntityList;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.WorldClient;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModHooks;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.EntityVoidInsect;

public class ItemVoidScepter extends ItemEbonScpeter {

	public static HashMap<EntityPlayer, Set<EntityVoidInsect>> minions = new HashMap();

	public ItemVoidScepter(int par1) {
		super(par1);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving) {
		if (par2EntityLiving.worldObj instanceof WorldClient) return true;

		EntityPlayer player = EbonModHooks.getInteractingPlayer();
		if (EbonModHelper.doesPlayerHaveLexicon(player) && !EbonModHelper.doesPlayerHaveME(player) && EbonModHelper.isDarknessEnough(player, EbonModReference.DARKNESS_MIN_SCEPTER) && !minions.containsKey(player)) {
			Set<EntityVoidInsect> minionsSet = new LinkedHashSet();
			for (int i = 0; i < EbonModReference.VOID_SCEPTER_INSECT_COUNT; i++) {
				EntityVoidInsect insect = (EntityVoidInsect) EntityList.createEntityByName("VoidInsect", par2EntityLiving.worldObj);
				insect.setPosition(player.posX, player.posY, player.posZ);
				par2EntityLiving.worldObj.spawnEntityInWorld(insect);
				insect.setTechincalTarget(par2EntityLiving);
				insect.setSummoner(player);
				minionsSet.add(insect);
			}
			minions.put(player, minionsSet);
			par2EntityLiving.worldObj.playSoundEffect(par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ, "ebonmod.spell", 1.0F, 1.0F);
			EbonModHelper.addMEToPlayer(player, EbonModReference.ME_SCEPTER);
			EbonModHelper.addShadeForPlayer(player, EbonModReference.SHADE_SCEPTER);
		}
		else par2EntityLiving.worldObj.playSoundEffect(par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ, "ebonmod.fail", 1.0F, 1.0F);

		return true;
	}

}
