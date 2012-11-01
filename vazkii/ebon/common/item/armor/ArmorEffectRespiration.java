package vazkii.ebon.common.item.armor;

import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;

import vazkii.ebon.api.ArmorEffect;
import vazkii.ebon.api.ArmorType;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;

public class ArmorEffectRespiration extends ArmorEffect {

	static Set<EntityPlayer> effectTickedForPlayers = new LinkedHashSet();

	@Override
	public EnumSet<ArmorType> armorTypes() {
		return EnumSet.of(ArmorType.CHEST);
	}

	@Override
	public void onTick(EnumSet<ArmorType> armorType, EntityPlayer player) {
		if (player.getAir() == -19 && !effectTickedForPlayers.contains(player)) {
			player.setAir(301);
			effectTickedForPlayers.add(player);
		}

		if (effectTickedForPlayers.contains(player) && !player.isInsideOfMaterial(Material.water)) effectTickedForPlayers.remove(player);
	}

	@Override
	public String name() {
		return "Respiration";
	}

}
