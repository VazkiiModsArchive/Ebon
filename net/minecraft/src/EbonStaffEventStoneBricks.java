package net.minecraft.src;

public class EbonStaffEventStoneBricks extends EbonAPI_SimpleEbonStaffEvent
{
    public EbonStaffEventStoneBricks()
    {
        super(Block.stoneBrick.blockID);
    }

    public void doEventAt(World world, int xPos, int yPos, int zPos)
    {
        int m = world.getBlockMetadata(xPos, yPos, zPos);

        if (m == 0)
        {
            world.setBlockAndMetadataWithNotify(xPos, yPos, zPos, Block.stoneBrick.blockID, 3);
        }
        else if (m == 3)
        {
            world.setBlockAndMetadataWithNotify(xPos, yPos, zPos, Block.stoneBrick.blockID, 0);
        }
    }
}
