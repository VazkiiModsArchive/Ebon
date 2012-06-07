package net.minecraft.src;

import java.util.Random;
import java.util.Set;

import net.minecraft.src.forge.IChunkLoadHandler;
import net.minecraft.src.forge.MinecraftForge;

public class BlockPhantomChest extends BlockContainer implements IChunkLoadHandler
{
    Random rand = new Random();

    public BlockPhantomChest(int par1, Material par2Material)
    {
        super(par1, par2Material);
        setTickRandomly(true);
        MinecraftForge.registerChunkLoadHandler(this);
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        if (par5EntityLiving instanceof EntityPlayer)
        {
            TileEntityPhantomChest var6 = (TileEntityPhantomChest)par1World.getBlockTileEntity(par2, par3, par4);
            var6.initializeEntity(rand);
        }
    }

    public BlockPhantomChest(int par1, int par2, Material par3Material)
    {
        super(par1, par2, par3Material);
    }

    public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        TileEntityPhantomChest var6 = (TileEntityPhantomChest)par1World.getBlockTileEntity(par2, par3, par4);

        if (var6 != null && !var6.isLocked())
        {
            if (var6.getRank() == 4)
            {
                for (int i = 0; i < 50; i++)
                {
                    float f = (float)par2 + rand.nextFloat();
                    float f2 = (float)par3 + rand.nextFloat() * 0.5F + 0.5F;
                    float f3 = (float)par4 + rand.nextFloat();
                    var6.worldObj.spawnParticle("explode", f, f2, f3, 0.0D, 0.0D, 0.0D);
                }

                doRandomBadThing(par1World, par2, par3, par4, var6);
                var6.worldObj.playSoundEffect(var6.xCoord, var6.yCoord, var6.zCoord, "mob.endermen.portal", 1.0F, 1.0F);
                return true;
            }

            ModLoader.openGUI(par5EntityPlayer, new GuiPhantomChest(par5EntityPlayer.inventory, var6));
        }
        else if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().itemID == mod_Ebon.phantomKey.shiftedIndex)
        {
            if (par1World.worldInfo.getGameType() != 1)
            {
                par5EntityPlayer.getCurrentEquippedItem().stackSize--;
            }

            var6.unlock();
        }

        return true;
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityPhantomChest();
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return mod_Ebon.phantomChestRenderID;
    }

    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        for (int l = 0; l < 25; l++)
        {
            float f = (float)par2 + par5Random.nextFloat();
            float f2 = (float)par3 + par5Random.nextFloat() * 0.5F;
            float f3 = (float)par4 + par5Random.nextFloat();
            par1World.spawnParticle("portal", f, f2, f3, 0.0D, 0.0D, 0.0D);
        }
    }

    public void doRandomBadThing(World world, int x, int y, int z, TileEntityPhantomChest t)
    {
        Random rand = new Random();
        EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;

        if (t.getBadThing())
        {
            return;
        }

        t.setDoneBadThing();
        int r = rand.nextInt(10);

        switch (r)
        {
            case 1:
            {
                EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("Creeper", world);
                entityliving.setLocationAndAngles(x, y + 1, z, world.rand.nextFloat() * 360F, 0.0F);
                world.spawnEntityInWorld(entityliving);
                break;
            }

            case 2:
            {
                world.spawnEntityInWorld(new EntityPotion(world, player, 16484));
                break;
            }

            case 3:
            {
                world.addWeatherEffect(new EntityLightningBolt(world, x, y, z));
                break;
            }

            case 4:
            {
                EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("CaveSpider", world);
                entityliving.setLocationAndAngles(x, y + 1, z, world.rand.nextFloat() * 360F, 0.0F);
                world.spawnEntityInWorld(entityliving);
                break;
            }

            case 5:
            {
                EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("Ghast", world);
                entityliving.setLocationAndAngles(x, y + 1, z, world.rand.nextFloat() * 360F, 0.0F);
                world.spawnEntityInWorld(entityliving);
                break;
            }

            case 6:
            {
                world.setBlockWithNotify(x, y, z, Block.lavaStill.blockID);
                world.notifyBlockChange(x, y, z, Block.lavaStill.blockID);
                break;
            }

            case 7:
            {
                for (int i = 0; i < 9; i++)
                {
                    EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("Zombie", world);
                    entityliving.setLocationAndAngles(x, y + 1, z, world.rand.nextFloat() * 360F, 0.0F);
                    world.spawnEntityInWorld(entityliving);
                }

                break;
            }

            case 8:
            {
                for (int i = 0; i < 4; i++)
                {
                    EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("Skeleton", world);
                    entityliving.setLocationAndAngles(x, y + 1, z, world.rand.nextFloat() * 360F, 0.0F);
                    world.spawnEntityInWorld(entityliving);
                }

                break;
            }

            case 9:
            {
                for (int i = 0; i < 3; i++)
                {
                    Entity entity = EntityList.createEntityByName("FallingSand", world);
                    entity.setLocationAndAngles(player.posX, player.posY + 3, player.posZ, world.rand.nextFloat() * 360F, 0.0F);
                    world.spawnEntityInWorld(entity);
                }

                break;
            }

            case 0:
            {
                player.setFire(15);
                break;
            }
        }

        if (r != 6)
        {
            t.worldObj.setBlockWithNotify(x, y, z, 0);
        }

        t.worldObj.removeBlockTileEntity(x, y, z);
    }

    public void addActiveChunks(World world, Set<ChunkCoordIntPair> chunkList)
    {
    }

    public boolean canUnloadChunk(Chunk chunk)
    {
        return !mod_Ebon.phantomChestsChunkLoading;
    }

    public boolean canUpdateEntity(Entity entity)
    {
        return true;
    }
}
