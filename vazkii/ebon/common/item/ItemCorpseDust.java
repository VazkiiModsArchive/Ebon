package vazkii.ebon.common.item;

import java.util.Random;

import vazkii.ebon.common.EbonModPacketHandler;
import vazkii.ebon.common.EbonModReference;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EnumCreatureAttribute;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.WorldClient;

public class ItemCorpseDust extends Item {

	public ItemCorpseDust(int id) {
		super(id);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving) {
		if (par2EntityLiving.worldObj instanceof WorldClient) return true;

		Random rand = new Random();

		if (par2EntityLiving.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
			par2EntityLiving.heal(5);
			for (int i = 0; i < EbonModReference.PARTICLE_COUNT * 2; i++)
				EbonModPacketHandler.sendParticlePacket("corpsedust", par2EntityLiving.posX + (rand.nextDouble() - 0.5D) * par2EntityLiving.width, par2EntityLiving.posY + rand.nextDouble() * par2EntityLiving.height - 0.25D, par2EntityLiving.posZ + (rand.nextDouble() - 0.5D) * par2EntityLiving.width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D, false);
			par2EntityLiving.worldObj.playSoundAtEntity(par2EntityLiving, "mob.blaze.breathe", 0.6F, rand.nextFloat());
		}

		par1ItemStack.stackSize--;
		return true;
	}

	@Override
	public String getTextureFile() {
		return EbonModReference.SPRITESHEET_PATH;
	}

}
