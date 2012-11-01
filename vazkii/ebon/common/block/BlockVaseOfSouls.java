package vazkii.ebon.common.block;

import java.util.List;
import java.util.Random;

import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModReference;
import vazkii.ebon.common.mod_Ebon;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BlockCauldron;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityXPOrb;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryLargeChest;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockVaseOfSouls extends BlockCauldron {

	public BlockVaseOfSouls(int par1) {
		super(par1);
		isBlockContainer = true;
		GameRegistry.registerBlock(this);
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		par1World.scheduleBlockUpdate(par2, par3, par4, blockID, tickRate());
		par1World.setBlockTileEntity(par2, par3, par4, createNewTileEntity(par1World));
	}

	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityVaseOfSouls();
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		List<Entity> entities = world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(i, j + 1, k, i + 1, j + 1.2, k + 1));
		entities.addAll(world.getEntitiesWithinAABB(EntityXPOrb.class, AxisAlignedBB.getBoundingBox(i, j + 1, k, i + 1, j + 1.2, k + 1)));

		for (Entity e : entities)
			onEntityCollidedWithBlock(world, i, j, k, e);

				world.scheduleBlockUpdate(i, j, k, blockID, tickRate());
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		if (entity instanceof EntityItem) {
			IInventory inv = getInventoryAt(world, i, j - 1, k);
			if (inv != null && placeItemInInventory(inv, ((EntityItem) entity).item)) entity.setDead();
			else if (!world.isBlockOpaqueCube(i, j - 1, k)) entity.setPosition(entity.posX, entity.posY - 0.666666667, entity.posZ);
		} else if (entity instanceof EntityXPOrb) {
			TileEntityVaseOfSouls tile = (TileEntityVaseOfSouls) world.getBlockTileEntity(i, j, k);
			tile.setXP(tile.getXP() + ((EntityXPOrb) entity).getXpValue());
			entity.setDead();
		}
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (par1World instanceof WorldClient) return true;

		TileEntityVaseOfSouls tile = (TileEntityVaseOfSouls) par1World.getBlockTileEntity(par2, par3, par4);
		int xp = tile.getXP();

		if (xp >= EbonModReference.VASE_OF_SOULS_MIN_XP && par5EntityPlayer.inventory.getCurrentItem() != null && par5EntityPlayer.inventory.getCurrentItem().isItemEqual(new ItemStack(Item.glassBottle)) && EbonModHelper.isDarknessEnough(par5EntityPlayer, EbonModReference.DARKNESS_MIN_VASE_SOULS)) {
			ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
			item.stackSize--;

			ItemStack stack = new ItemStack(mod_Ebon.bottleOfDarkness, 1);
			if (!par5EntityPlayer.inventory.addItemStackToInventory(stack)) par5EntityPlayer.dropPlayerItem(stack);

			tile.setXP(xp - EbonModReference.VASE_OF_SOULS_MIN_XP);

			par1World.playSoundEffect(par2, par3, par4, "ebonmod.vase", 1.0F, 1.0F);
		}

		return true;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
		int xp = ((TileEntityVaseOfSouls) par1World.getBlockTileEntity(par2, par3, par4)).getXP();

		if (xp > 0) {
			EntityXPOrb e = new EntityXPOrb(par1World, par2, par3, par4, xp);
			par1World.spawnEntityInWorld(e);
			EntityXPOrb.getXPSplit(xp);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
		par1World.removeBlockTileEntity(par2, par3, par4);

	}

	private IInventory getInventoryAt(World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);

		if (!(tile instanceof IInventory)) return null;

		return getInventoryAtAndCheckForDoubleChests(world, x, y, z);
	}

	private IInventory getInventoryAtAndCheckForDoubleChests(World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);

		if (!(tile instanceof TileEntityChest)) return tile instanceof IInventory ? (IInventory) tile : null;

		int id = world.getBlockId(x, y, z);
		IInventory chest = (IInventory) tile;
		IInventory otherChest = null;

		if (world.getBlockId(x + 1, y, z) == id) otherChest = (IInventory) world.getBlockTileEntity(x + 1, y, z);

		if (world.getBlockId(x - 1, y, z) == id) otherChest = (IInventory) world.getBlockTileEntity(x - 1, y, z);

		if (world.getBlockId(x, y, z + 1) == id) otherChest = (IInventory) world.getBlockTileEntity(x, y, z + 1);

		if (world.getBlockId(x, y, z - 1) == id) otherChest = (IInventory) world.getBlockTileEntity(x, y, z - 1);

		return otherChest == null ? chest : new InventoryLargeChest("", chest, otherChest);
	}

	private boolean placeItemInInventory(IInventory inventory, ItemStack item) {
		if (item == null) return false;

		int slot = getFirstFreeSlotInInventory(inventory, item);

		if (slot >= 0) {
			ItemStack stack = inventory.getStackInSlot(slot);

			if (stack != null) {
				stack.stackSize += item.stackSize;
				inventory.setInventorySlotContents(slot, stack);
				return true;
			} else {
				inventory.setInventorySlotContents(slot, item);
				return true;
			}
		}
		return false;
	}

	private int getFirstFreeSlotInInventory(IInventory inv, ItemStack item) {
		int size = inv.getSizeInventory();

		for (int i = 0; i < size; i++) {
			boolean stackable = false;

			if (inv.getStackInSlot(i) != null && inv.getStackInSlot(i).itemID == item.itemID && (!item.getItem().getHasSubtypes() || inv.getStackInSlot(i).getItemDamage() == item.getItemDamage())) stackable = inv.getStackInSlot(i).stackSize <= item.getMaxStackSize() - item.stackSize;

			if (inv.getStackInSlot(i) == null || stackable) return i;
		}

		return -1;
	}

	@Override
	public void onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6) {
		super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
		TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);

		if (tile != null) tile.receiveClientEvent(par5, par6);
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}
}
