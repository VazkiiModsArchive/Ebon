package net.minecraft.src;

/**
 * An event for the Ebon Staff.
 * 
 * @author Vazkii
 * @see EbonAPI
 * @see ItemEbonStaff
 * @see EbonAPI_SimpleEbonStaffEvent
 */
public abstract class EbonAPI_EbonStaffEvent {

	/**
	 * Does an event at the defined location.
	 * 
	 * @param world The active World.
	 * @param xPos The X coordinate of the block in which the event is being done.
	 * @param zPos The Y coordinate of the block in which the event is being done.
	 * @param yPos The Z coordinate of the block in which the event is being done.
	 */
	public abstract void doEventAt(World world, int xPos, int yPos, int zPos);

	/**
	 * If the event requires a Necromancer's Lexicon.
	 * 
	 * @return If the event does or not.
	 */
	public abstract boolean requiresTome();
	
	/**
	 * Checks if the event can be done under the specified conditions, good for checking block metadata or light value.
	 * 
	 * @param world The active World.
	 * @param xPos The X coordinate of the block in which the event is being checked.
	 * @param zPos The Y coordinate of the block in which the event is being checked.
	 * @param yPos The Z coordinate of the block in which the event is being checked.
	 * @return If the event can be done or not.
	 */
	public abstract boolean canDoEvent(World world, int xPos, int yPos, int zPos);
}