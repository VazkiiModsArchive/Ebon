package net.minecraft.src;

/**
 * A simple Ebon Staff event that turns a block into another.
 * 
 * @author Vazkii
 * @see EbonAPI_EbonStaffEvent
 */
public class EbonAPI_SimpleEbonStaffEvent extends EbonAPI_EbonStaffEvent{

	/**
	 * The Block ID that this event will turn the block into.
	 */
	private final int idToSet;
	
	/**
	 * @param blockID The Block ID that this event will turn the block into.
	 */
	public EbonAPI_SimpleEbonStaffEvent(int blockID) {
		idToSet = blockID;
	}

	public void doEventAt(World world, int xPos, int yPos, int zPos) {
		world.setBlockWithNotify(xPos, yPos, zPos, idToSet);
	}

	public boolean requiresTome() {
		return false;
	}

	public boolean canDoEvent(World world, int xPos, int yPos, int zPos) {
		return true;
	}

}
