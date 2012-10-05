package vazkii.ebon.common.item;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.IMob;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;
import vazkii.ebon.common.EbonModHelper;
import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;

public class ItemDarknessImbiber extends ItemSpritesheet {

	public static Set<EntityLiving> alreadyImbibedEntities = new LinkedHashSet();

	public ItemDarknessImbiber(int par1) {
		super(par1);
		setMaxStackSize(1);
		setMaxDamage(1024);
		setFull3D();
	}

	private boolean successfulImbibe;

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (EbonModHelper.doesPlayerHaveME(par3EntityPlayer) || !EbonModHelper.isDarknessEnough(par3EntityPlayer, EbonModReference.DARKNESS_MIN_IMBIBER)) {
			par3EntityPlayer.worldObj.playSoundAtEntity(par3EntityPlayer, "ebonmod.fail", 0.6F, par3EntityPlayer.worldObj.rand.nextFloat());
			return par1ItemStack;
		}

		List<EntityLiving> entities = par2World.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(par3EntityPlayer.posX - EbonModReference.IMBIBER_RANGE, par3EntityPlayer.posY - EbonModReference.IMBIBER_RANGE, par3EntityPlayer.posZ - EbonModReference.IMBIBER_RANGE, par3EntityPlayer.posX + EbonModReference.IMBIBER_RANGE, par3EntityPlayer.posY + EbonModReference.IMBIBER_RANGE, par3EntityPlayer.posZ + EbonModReference.IMBIBER_RANGE));
		ItemStack stack = par1ItemStack;

		successfulImbibe = false;

		for (EntityLiving entity : entities)
			if ((stack = imbibeEntity(entity, stack, par3EntityPlayer)) == null) return stack;

		if (successfulImbibe) EbonModHelper.addMEToPlayer(par3EntityPlayer, EbonModReference.ME_DARKNESS_IMBIBER);

		return stack;
	}

	public ItemStack imbibeEntity(EntityLiving entity, ItemStack stack, EntityPlayer player) {
		if (entity == null || !canEntityBeImbibed(entity) || !(entity instanceof IMob)) return stack;

		if (entity.worldObj instanceof WorldClient) return stack;

		Random rand = new Random();
		EbonModHelper.addShadeForPlayer(player, EbonModReference.SHADE_IMBIBER);
		for (int i = 0; i < EbonModReference.PARTICLE_COUNT * 2; i++)
			EbonModPacketHandler.sendParticlePacket("portal", entity.posX + (rand.nextDouble() - 0.5D) * entity.width, entity.posY + rand.nextDouble() * entity.height - 0.25D, entity.posZ + (rand.nextDouble() - 0.5D) * entity.width, -((rand.nextDouble() - 0.5D) * 2.0D), rand.nextDouble(), -((rand.nextDouble() - 0.5D) * 2.0D), true);
		entity.worldObj.playSoundAtEntity(entity, "mob.blaze.breathe", 0.6F, rand.nextFloat());
		entity.getEntityData().setBoolean("alreadyImbibed", true);
		stack.damageItem(1, player);
		successfulImbibe = true;
		return stack;
	}

	public boolean canEntityBeImbibed(EntityLiving entity) {
		NBTTagCompound data = entity.getEntityData();
		return entity.getHealth() >= entity.getMaxHealth() ? data.hasKey("alreadyImbibed") ? data.getBoolean("alreadyImbibed") : true : false;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.rare;
	}

}
