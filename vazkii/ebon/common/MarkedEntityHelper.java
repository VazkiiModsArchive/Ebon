package vazkii.ebon.common;

import java.util.LinkedHashSet;
import java.util.Set;

import net.minecraft.src.Entity;

public final class MarkedEntityHelper {

	static Set<Entity> markedEntities = new LinkedHashSet();

	public static synchronized boolean markEntity(Entity entity) {
		boolean b = markedEntities.add(entity);
		update();
		return b;
	}

	public static synchronized boolean isMarked(Entity entity) {
		update();
		return markedEntities.contains(entity);
	}

	private static synchronized void update() {
		Set<Entity> deadEntities = new LinkedHashSet();
		for (Entity e : markedEntities)
			if (e.isDead) deadEntities.add(e);
		markedEntities.removeAll(deadEntities);
	}
}
