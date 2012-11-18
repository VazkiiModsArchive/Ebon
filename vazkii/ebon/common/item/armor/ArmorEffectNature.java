package vazkii.ebon.common.item.armor;

import java.util.EnumSet;

import vazkii.ebon.api.ArmorEffect;
import vazkii.ebon.api.ArmorType;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;

public class ArmorEffectNature extends ArmorEffect {

	@Override
	public EnumSet<ArmorType> armorTypes() {
		return EnumSet.of(ArmorType.CHEST);
	}

	@Override
	public void onTick(EnumSet<ArmorType> armorType, EntityPlayer player) {
		int x = (int) Math.round(player.posX);
		int y = (int) Math.round(player.posY);
		int z = (int) Math.round(player.posZ);
		int id = player.worldObj.getBlockId(x, y - 1, z);
		if (id == Block.dirt.blockID) player.worldObj.setBlockWithNotify(x, y - 1, z, Block.grass.blockID);

		id = player.worldObj.getBlockId(x + 1, y - 1, z);
		if (id == Block.dirt.blockID) player.worldObj.setBlockWithNotify(x + 1, y - 1, z, Block.grass.blockID);

		id = player.worldObj.getBlockId(x - 1, y - 1, z);
		if (id == Block.dirt.blockID) player.worldObj.setBlockWithNotify(x - 1, y - 1, z, Block.grass.blockID);

		id = player.worldObj.getBlockId(x, y - 1, z + 1);
		if (id == Block.dirt.blockID) player.worldObj.setBlockWithNotify(x, y - 1, z + 1, Block.grass.blockID);

		id = player.worldObj.getBlockId(x, y - 1, z - 1);
		if (id == Block.dirt.blockID) player.worldObj.setBlockWithNotify(x, y - 1, z - 1, Block.grass.blockID);
	}

	@Override
	public String name() {
		return "Nature";
	}

}
