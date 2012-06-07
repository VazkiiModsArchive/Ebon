package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.forge.ITextureProvider;

public class BlockVaseOfSouls extends BlockCauldron implements ITextureProvider
{
    protected BlockVaseOfSouls(int par1)
    {
        super(par1);
        setTickRandomly(true);
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        for (Object o : world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(i, j + 1, k, i + 1, j + 1.2, k + 1)))
        {
            Entity e = (Entity)o;
            onEntityCollidedWithBlock(world, i, j, k, e);
        }

        for (Object o : world.getEntitiesWithinAABB(EntityXPOrb.class, AxisAlignedBB.getBoundingBox(i, j + 1, k, i + 1, j + 1.2, k + 1)))
        {
            Entity e = (Entity)o;
            onEntityCollidedWithBlock(world, i, j, k, e);
        }
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        if (entity instanceof EntityItem)
        {
            if (getContainerAt(world, i, j - 1, k) != null)
            {
                IInventory inv = getContainerAt(world, i, j - 1, k);

                if ((putItemInContainer(inv, ((EntityItem)entity).item, getFirstAvailableSlotInInventory(inv, ((EntityItem)entity).item))))
                {
                    entity.setDead();
                }
            }
            else if (!world.isBlockOpaqueCube(i, j - 1, k))
            {
                entity.setPosition(entity.posX, entity.posY - 0.666666667, entity.posZ);
            }
        }
        else if (entity instanceof EntityXPOrb)
        {
            world.setBlockMetadata(i, j, k, world.getBlockMetadata(i, j, k) + ((EntityXPOrb)entity).getXpValue());
            entity.setDead();
        }
    }

    private IInventory getContainerAt(World world, int i, int j, int k)
    {
        TileEntity tile = world.getBlockTileEntity(i, j, k);

        if (!(tile instanceof IInventory))
        {
            return null;
        }

        return checkForDoubleChest(world, i, j, k);
    }

    public int getFirstAvailableSlotInInventory(IInventory inv, ItemStack stack)
    {
        for (int i = 0; i < inv.getSizeInventory(); i++)
        {
            if (inv.getStackInSlot(i) == null || inv.getStackInSlot(i).itemID == stack.itemID && inv.getStackInSlot(i).stackSize < inv.getStackInSlot(i).getItem().maxStackSize)
            {
                return i;
            }
            else
            {
                continue;
            }
        }

        return -1;
    }

    //TODO Add support for ISidedInventory.
    private int getFirstFreeSlotInInventory(IInventory inv, ItemStack item)
    {
        int size = inv.getSizeInventory();

        for (int i = 0; i < size; i++)
        {
            boolean canStack = false;

            if ((inv.getStackInSlot(i) != null)
                    && (inv.getStackInSlot(i).itemID == item.itemID) &&
                    ((!item.getItem().getHasSubtypes()) || (inv.getStackInSlot(i).getItemDamage() == item.getItemDamage())))
            {
                canStack = (inv.getStackInSlot(i).stackSize <= (item.getMaxStackSize() - item.stackSize));
            }

            if ((inv.getStackInSlot(i) == null) || canStack)
            {
                return i;
            }
        }

        return -1;
    }

    private boolean putItemInContainer(IInventory inventory, ItemStack item, int slot)
    {
        if (item == null)
        {
            return false;
        }

        if (slot >= 0)
        {
            ItemStack stack = inventory.getStackInSlot(slot);

            if (stack != null)
            {
                stack.stackSize += item.stackSize;
                inventory.setInventorySlotContents(slot, stack);
                return true;
            }
            else
            {
                inventory.setInventorySlotContents(slot, item);
                return true;
            }
        }

        return false;
    }

    private IInventory checkForDoubleChest(World world, int i, int j, int k)
    {
        TileEntity tile = world.getBlockTileEntity(i, j, k);

        if (!(tile instanceof TileEntityChest))
        {
            if (tile instanceof IInventory)
            {
                return (IInventory)tile;
            }
            else
            {
                return null;
            }
        }

        int cblockID = world.getBlockId(i, j, k);
        IInventory chest1 = (IInventory)(world.getBlockTileEntity(i, j, k));

        if (world.getBlockId(i + 1, j, k) == cblockID)
        {
            IInventory chest2 = (IInventory)(world.getBlockTileEntity(i + 1, j, k));
            return new InventoryLargeChest("", chest1, chest2);
        }

        if (world.getBlockId(i - 1, j, k) == cblockID)
        {
            IInventory chest2 = (IInventory)(world.getBlockTileEntity(i - 1, j, k));
            return new InventoryLargeChest("", chest2, chest1);
        }

        if (world.getBlockId(i, j, k + 1) == cblockID)
        {
            IInventory chest2 = (IInventory)(world.getBlockTileEntity(i, j, k + 1));
            return new InventoryLargeChest("", chest1, chest2);
        }

        if (world.getBlockId(i, j, k - 1) == cblockID)
        {
            IInventory chest2 = (IInventory)(world.getBlockTileEntity(i, j, k - 1));
            return new InventoryLargeChest("", chest2, chest1);
        }

        return chest1;
    }

    public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (meta >= 8)
        {
            if (par5EntityPlayer.inventory.getCurrentItem() != null && par5EntityPlayer.inventory.getCurrentItem().isItemEqual(new ItemStack(Item.glassBottle)))
            {
                ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
                item.stackSize--;

                if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.expBottle, 1)))
                {
                    par5EntityPlayer.dropItem(Item.expBottle.shiftedIndex, 1);
                }

                par1World.setBlockMetadata(par2, par3, par4, meta - 8);
                par1World.playSoundEffect(par2, par3, par4, "vazkii.ebonmod.vase", 1.0F, 1.0F);
            }
        }

        return true;
    }

    public void onBlockRemoval(World par1World, int par2, int par3, int par4)
    {
        int m = par1World.getBlockMetadata(par2, par3, par4);

        if (m > 0)
        {
            EntityXPOrb e = new EntityXPOrb(par1World, (double)par2, (double)par3, (double)par4, m);
            par1World.spawnEntityInWorld(e);
            e.getXPSplit(m);
        }

        super.onBlockRemoval(par1World, par2, par3, par4);
    }

    public String getTextureFile()
    {
        return "/vazkii/ebonmod/sprites.png";
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_Ebon.soulVaseItem.shiftedIndex;
    }
}
