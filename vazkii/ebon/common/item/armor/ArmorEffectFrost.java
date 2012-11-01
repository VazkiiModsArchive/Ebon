package vazkii.ebon.common.item.armor;

import java.util.EnumSet;

import vazkii.ebon.api.ArmorEffect;
import vazkii.ebon.api.ArmorType;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;

public class ArmorEffectFrost extends ArmorEffect {

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
		int idToSet = id == Block.waterStill.blockID ? Block.ice.blockID : id == Block.lavaStill.blockID ? Block.obsidian.blockID : -1;
		if (idToSet >= 0) player.worldObj.setBlockWithNotify(x, y - 1, z, idToSet);

		id = player.worldObj.getBlockId(x + 1, y - 1, z);
		idToSet = id == Block.waterStill.blockID ? Block.ice.blockID : id == Block.lavaStill.blockID ? Block.obsidian.blockID : -1;
		if (idToSet >= 0) player.worldObj.setBlockWithNotify(x + 1, y - 1, z, idToSet);

		id = player.worldObj.getBlockId(x - 1, y - 1, z);
		idToSet = id == Block.waterStill.blockID ? Block.ice.blockID : id == Block.lavaStill.blockID ? Block.obsidian.blockID : -1;
		if (idToSet >= 0) player.worldObj.setBlockWithNotify(x - 1, y - 1, z, idToSet);

		id = player.worldObj.getBlockId(x, y - 1, z + 1);
		idToSet = id == Block.waterStill.blockID ? Block.ice.blockID : id == Block.lavaStill.blockID ? Block.obsidian.blockID : -1;
		if (idToSet >= 0) player.worldObj.setBlockWithNotify(x, y - 1, z + 1, idToSet);

		id = player.worldObj.getBlockId(x, y - 1, z - 1);
		idToSet = id == Block.waterStill.blockID ? Block.ice.blockID : id == Block.lavaStill.blockID ? Block.obsidian.blockID : -1;
		if (idToSet >= 0) player.worldObj.setBlockWithNotify(x, y - 1, z - 1, idToSet);
	}

	@Override
	public String name() {
		return "Frost";
	}

}
