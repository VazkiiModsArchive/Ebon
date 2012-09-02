package vazkii.ebon.common.item;

import java.util.LinkedHashSet;
import java.util.Set;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityXPOrb;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;

public class ItemMiniumCharm extends ItemCharmBase {

	static Set<EntityPlayer> playersExtracting = new LinkedHashSet();
	static Set<EntityPlayer> playersNotReset = new LinkedHashSet();

	public ItemMiniumCharm(int par1) {
		super(par1);
	}

	private int getXP(ItemStack itemstack) {
		return itemstack.getTagCompound().hasKey("storedXP") ? itemstack.getTagCompound().getInteger("storedXP") : 0;
	}

	private void addXP(ItemStack itemstack, int i) {
		setXP(itemstack, getXP(itemstack) + i);
	}

	private void setXP(ItemStack itemstack, int i) {
		itemstack.getTagCompound().setInteger("storedXP", i);
	}

	@Override public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if (par1ItemStack.getTagCompound() == null) par1ItemStack.setTagCompound(new NBTTagCompound());

		if (par3Entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) par3Entity;

			extract:
			{
				if (hasMultiples(player)) break extract;

				if (playersExtracting.contains(player) && par2World.getEntitiesWithinAABB(EntityXPOrb.class, AxisAlignedBB.getBoundingBox(par3Entity.posX - 1, par3Entity.posY - 1, par3Entity.posZ - 1, par3Entity.posX + 1, par3Entity.posY + 1, par3Entity.posZ + 1)).size() <= 5) {

					addXP(par1ItemStack, -1);
					playersNotReset.add(player);
					par2World.spawnEntityInWorld(new EntityXPOrb(par2World, par3Entity.posX, par3Entity.posY, par3Entity.posZ, 1));

					if (getXP(par1ItemStack) <= 0) playersExtracting.remove(player);
				}
			}

			super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

			if (playersNotReset.contains(player)) playersNotReset.remove(player);
		}
	}

	@Override public void onXPChange(EntityPlayer player, ItemStack stack, int change) {
		if (change > 0 && !playersExtracting.contains(player) && !playersNotReset.contains(player)) {
			player.addExperience(-change);
			addXP(stack, change);
		}
	}

	@Override public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (getXP(par1ItemStack) > 0 && !playersExtracting.contains(par3EntityPlayer) && !(par2World instanceof WorldClient)) {
			playersExtracting.add(par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "ebonmod.minium", 1.0F, 1.0F);
		}

		return par1ItemStack;
	}

	@Override public boolean hasEffect(ItemStack par1ItemStack) {
		return playersExtracting.contains(par1ItemStack);
	}

}
