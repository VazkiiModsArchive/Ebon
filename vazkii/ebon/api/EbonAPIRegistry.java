package vazkii.ebon.api;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.Item;

public class EbonAPIRegistry {

	public static final HashMap<Integer, Integer> simpleTransmutations = new HashMap();
	public static final HashMap<Class<? extends EntityLiving>, String> simpleTransfigurations = new HashMap();
	public static final HashMap<ArmorType, List<ArmorEffect>> armorEffects = new HashMap();
	public static final HashMap<String, ArmorEffect> armorEffectNameMappings = new HashMap();
	public static final Set<Item> scepterSet = new LinkedHashSet();
	public static final Set<String> blacklistedSpawners = new LinkedHashSet();

	public static boolean registerSimpleTransmutation(int from, int to) {
		if (simpleTransmutations.containsKey(from)) return false;
		simpleTransmutations.put(from, to);
		return true;
	}

	public static boolean registerSimpleTransfiguration(Class<? extends EntityLiving> clazzFrom, String nameTo) {
		if (simpleTransfigurations.containsKey(clazzFrom)) return false;
		simpleTransfigurations.put(clazzFrom, nameTo);
		return true;
	}

	public static boolean registerScepter(Item item) {
		return scepterSet.add(item);
	}

	public static boolean blacklistSpawner(String name) {
		return blacklistedSpawners.add(name);
	}

	public static boolean registerArmorEffect(ArmorEffect effect) {
		boolean fail = false;
		for (ArmorType type : effect.armorTypes()) {
			if (!armorEffects.containsKey(type)) armorEffects.put(type, new LinkedList());

			if (!armorEffects.get(type).add(effect)) fail = true;
		}
		armorEffectNameMappings.put(effect.name(), effect);

		return !fail;
	}
}
