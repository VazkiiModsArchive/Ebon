package vazkii.ebon.common.block;

import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.WorldClient;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.common.EbonModReference;

public class TileEntityWard extends TileEntity {

	boolean positive;

	public TileEntityWard() {
		super();
	}

	public TileEntityWard(boolean positive) {
		this();
		this.positive = positive;
	}

	@Override
	public void updateEntity() {

		if (worldObj instanceof WorldClient || positive && BlockEbonGlowstone.isAltar(worldObj, xCoord, yCoord, zCoord)) return;

		List<Entity> entities = worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(xCoord - EbonModReference.WARD_RANGE, yCoord - EbonModReference.WARD_RANGE, zCoord - EbonModReference.WARD_RANGE, xCoord + EbonModReference.WARD_RANGE, yCoord + EbonModReference.WARD_RANGE, zCoord + EbonModReference.WARD_RANGE));

		if (!positive && BlockEbonObsidian.isAltar(worldObj, xCoord, yCoord, zCoord)) {
			for (Entity e : entities)
				if (e instanceof EntityPlayer) ((EntityPlayer) e).clearActivePotions();
			return;
		}

		for (Entity e : entities)
			if (e instanceof EntityLiving && !(e instanceof EntityPlayer) && !e.isDead || e instanceof EntityItem) if (positive) CommonUtils.moveEntityAwayFromPos(e, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, EbonModReference.WARD_SPEED);
			else CommonUtils.moveEntityTowardsPos(e, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, EbonModReference.WARD_SPEED);

	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		positive = par1NBTTagCompound.getBoolean("positive");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("positive", positive);
	}
}
