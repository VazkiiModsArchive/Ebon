package vazkii.ebon.common.block;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

public class TileEntityVaseOfSouls extends TileEntity {

	int xp = 0;

	@Override public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		xp = par1NBTTagCompound.getInteger("xp");
	}

	@Override public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("xp", xp);
	}

	public int getXP() {
		return xp;
	}

	public void setXP(int xp) {
		this.xp = xp;
	}

}
