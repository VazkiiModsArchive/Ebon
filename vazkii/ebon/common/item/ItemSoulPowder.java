package vazkii.ebon.common.item;

import java.util.Random;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.common.mod_Ebon;

public class ItemSoulPowder extends ItemSpritesheet {

	public ItemSoulPowder(int par1) {
		super(par1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (world instanceof WorldClient) return itemstack;

		Random rand = new Random();
		int chance = rand.nextInt(100);
		int chance2 = rand.nextInt(3);

		ItemStack stack;
		String gotten;
		boolean rare = false;

		if (chance > 95) {
			stack = new ItemStack(mod_Ebon.staffShard);
			gotten = "a strange shard.";
			rare = true;
		} else if (chance > 90) {
			stack = new ItemStack(mod_Ebon.ebonGem);
			gotten = "a Ebon Gem.";
			rare = true;
		} else if (chance > 80) {
			stack = new ItemStack(mod_Ebon.undeadEssence);
			gotten = "some Undead Essence.";
		} else if (chance2 == 0) {
			stack = new ItemStack(Item.bone);
			gotten = "a Bone.";
		} else if (chance2 == 1) {
			stack = new ItemStack(mod_Ebon.corpseDust);
			gotten = "some Corpse Dust.";
		} else {
			stack = new ItemStack(Item.gunpowder);
			gotten = "some Gunpowder.";
		}

		if (!entityplayer.inventory.addItemStackToInventory(stack)) entityplayer.dropPlayerItem(stack);

		CommonUtils.sendChatMessage(entityplayer, "I seem to have gotten " + gotten);
		world.playSoundAtEntity(entityplayer, "ebonmod.sp" + (rare ? "Rare" : "Normal"), 1.0F, 1.0F);

		itemstack.stackSize--;
		return itemstack;
	}

}
