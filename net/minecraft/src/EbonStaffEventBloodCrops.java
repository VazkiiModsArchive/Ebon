package net.minecraft.src;

public class EbonStaffEventBloodCrops extends EbonAPI_EbonStaffEvent {

	public void doEventAt(World world, int xPos, int yPos, int zPos) {
		((BlockBloodLeaf)mod_Ebon.bloodCrops).fertilizePropperly(world, xPos, yPos, zPos);
	}

	@Override
	public boolean requiresTome() {
		return false;
	}

	public boolean canDoEvent(World world, int xPos, int yPos, int zPos) {
		return (world.getBlockId(xPos, yPos, zPos) == mod_Ebon.bloodCrops.blockID && world.getBlockMetadata(xPos, yPos, zPos) != 7);
	}

}
