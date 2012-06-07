package net.minecraft.src;

import java.util.List;

public class ItemWandOfImprisonment extends Ebon3DItems
{
    public ItemWandOfImprisonment(int i)
    {
        super(i, EnumRarity.rare);
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        if (world.getBlockId(i, j, k) == Block.mobSpawner.blockID && !EbonAPI.doesPlayerHaveMagicExhaustion() && EbonAPI.hasNecroTome())
        {
            TileEntityMobSpawner t = (TileEntityMobSpawner)world.getBlockTileEntity(i, j, k);
            List list = world.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBoxFromPool(i, j + 1, k, i + 1, j + 2, i + 1));

            if (list.size() == 1)
            {
                EntityLiving e = (EntityLiving)list.get(0);

                if (e instanceof EntityPlayer)
                {
                    return false;
                }

                if (t.getMobID() != e.getEntityString() && !EbonAPI.blacklistedSpawners.contains(e.getEntityString()))
                {
                    t.setMobID(e.getEntityString());
                    e.setDead();
                    ItemEbonStaff.doParticles(world, i, j, k);
                    entityplayer.worldObj.playSoundAtEntity(entityplayer, "vazkii.ebonmod.retrieval", 1.0F, 1.0F);

                    if (ModLoader.getMinecraftInstance().theWorld.worldInfo.getGameType() != 1)
                    {
                        itemstack.stackSize = 0;
                    }

                    EbonAPI.addMagicalExhaustionOnPlayerFor(150);
                    return true;
                }

                world.playSoundAtEntity(entityplayer, "vazkii.ebonmod.fail", 1.0F, 1.0F);
            }
        }

        world.playSoundAtEntity(entityplayer, "vazkii.ebonmod.fail", 1.0F, 1.0F);
        return true;
    }

    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return !EbonAPI.doesPlayerHaveMagicExhaustion();
    }
}
