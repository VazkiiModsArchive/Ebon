package net.minecraft.src;

/**
 * A simple Staff of Souls event that turns an Entity into another.
 * 
 * @author Vazkii
 * @see EbonAPI_StaffOfSoulsEvent
 */
public class EbonAPI_SimpleStaffOfSoulsEvent extends EbonAPI_StaffOfSoulsEvent {

	/**
	 * The Entity String that this event will turn the entity into.
	 */
	private final String entityToSet;
	
	/**
	 * @param entityName The Entity String that this event will turn the entity into.
	 */
	public EbonAPI_SimpleStaffOfSoulsEvent(String entityName) {
		entityToSet = entityName;
	}

	public void doEventAt(World world, EntityLiving entity) {
		entity.setDead();
		EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName(entityToSet, world);
        entityliving.setLocationAndAngles(entity.posX, entity.posY+0.33333, entity.posZ, world.rand.nextFloat() * 360F, 0.0F);
        world.spawnEntityInWorld(entityliving);
	}

	public boolean requiresTome() {
		return false;
	}

	public boolean canDoEvent(World world, EntityLiving entity) {
		return true;
	}

}
