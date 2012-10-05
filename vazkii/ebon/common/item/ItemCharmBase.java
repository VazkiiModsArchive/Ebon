package vazkii.ebon.common.item;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import vazkii.ebon.common.mod_Ebon;

public abstract class ItemCharmBase extends ItemSpritesheet {

	HashMap<EntityPlayer, Integer> xpLastTick = new HashMap();
	HashMap<EntityPlayer, Integer> xpNow = new HashMap();
	HashMap<EntityPlayer, Integer> lastXPChanges = new HashMap();
	Set<EntityPlayer> playersTickedFor = new LinkedHashSet();

	public ItemCharmBase(int par1) {
		super(par1);
	}

	int getLastXPChange(EntityPlayer player) {
		return xpNow.get(player) - xpLastTick.get(player);
	}

	boolean hasMultiples(EntityPlayer player) {
		if (player == null) return false;

		InventoryPlayer inv = player.inventory;
		boolean found = false;

		for (ItemStack stack : inv.mainInventory) {
			if (stack == null) continue;

			if (stack.isStackEqual(new ItemStack(mod_Ebon.plusiumCharm)) || stack.isStackEqual(new ItemStack(mod_Ebon.miniumCharm))) {
				if (found) return true;

				found = true;
			}
		}

		return false;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if (par3Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) par3Entity;
			xpNow.put(player, player.experienceTotal);

			if (!playersTickedFor.contains(player)) {
				xpLastTick.put(player, player.experienceTotal);
				playersTickedFor.add(player);
				return;
			}

			lastXPChanges.put(player, xpLastTick.get(player) != xpNow.get(player) ? getLastXPChange(player) : 0);
			xpLastTick.put(player, player.experienceTotal);

			if (lastXPChanges.get(player) != 0 && !hasMultiples(player)) onXPChange(player, par1ItemStack, lastXPChanges.get(player));
		}
	}

	public void onXPChange(EntityPlayer player, ItemStack stack, int change) {
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack) {
		return EnumRarity.rare;
	}

}
