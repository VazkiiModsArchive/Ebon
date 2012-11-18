package vazkii.ebon.client;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import vazkii.codebase.common.CommonUtils;

import net.minecraft.src.EntityFX;
import net.minecraft.src.World;

public class ParticleHelper {

	public static HashMap<String, Class<? extends EntityFX>> particles = new HashMap();

	public static void constructParticle(String name, World world, double x, double y, double z, double velX, double velY, double velZ) {
		if (!particles.containsKey(name)) return;

		Class<? extends EntityFX> clazz = particles.get(name);
		try {
			Constructor<? extends EntityFX> constructor = clazz.getConstructor(World.class, double.class, double.class, double.class, double.class, double.class, double.class);
			EntityFX fx = constructor.newInstance(world, x, y, z, velX, velY, velZ);
			CommonUtils.getMc().effectRenderer.addEffect(fx);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	static {
		particles.put("corpsedust", EntityCorpseDustFX.class);
		particles.put("darkflame", EntityDarkFlameFX.class);
	}
}
