package vazkii.ebon.common;

import java.io.File;
import java.util.Random;

import vazkii.codebase.common.CommonUtils;
import vazkii.codebase.common.EnumVazkiiMods;
import vazkii.codebase.common.IOUtils;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.WorldClient;

public class EbonModHelper {

	public static int clientDarkness = -1;
	public static int clientLexicon = -1; // -1 : needs request, 0 : no, 1 : yes

	public static boolean doesPlayerHaveME(EntityLiving player) {
		return player.isPotionActive(mod_Ebon.magicalExhaustion);
	}

	public static void addMEToPlayer(EntityLiving player, int time) {
		player.addPotionEffect(new PotionEffect(mod_Ebon.magicalExhaustion.id, time * 20, 0));
	}

	public static boolean doesPlayerHaveLexicon(EntityPlayer player) {
		File cacheFile = IOUtils.getWorldCacheFile(EnumVazkiiMods.EBON, player.worldObj);
		NBTTagCompound cmp = IOUtils.getTagCompoundInFile(cacheFile);
		return cmp.hasKey("hasLexicon_" + player.username) ? cmp.getBoolean("hasLexicon_" + player.username) : false;
	}

	public static void setLexiconForPlayer(EntityPlayer player, boolean lex) {
		File cacheFile = IOUtils.getWorldCacheFile(EnumVazkiiMods.EBON, player.worldObj);
		NBTTagCompound cmp = IOUtils.getTagCompoundInFile(cacheFile);
		cmp.setBoolean("hasLexicon_" + player.username, lex);
		IOUtils.injectNBTToFile(cmp, cacheFile);
	}

	public static int getDarknessForPlayer(EntityPlayer player) {
		File cacheFile = IOUtils.getWorldCacheFile(EnumVazkiiMods.EBON, player.worldObj);
		NBTTagCompound cmp = IOUtils.getTagCompoundInFile(cacheFile);
		return cmp.hasKey("darknessLv_" + player.username) ? Math.max(0, cmp.getInteger("darknessLv_" + player.username)) : 0;
	}

	public static int evolveDarkness(EntityPlayer player) {
		File cacheFile = IOUtils.getWorldCacheFile(EnumVazkiiMods.EBON, player.worldObj);
		IOUtils.getTagCompoundInFile(cacheFile);
		int currentLevel = getDarknessForPlayer(player);

		Random rand = new Random();
		for (int i = 0; i < EbonModReference.PARTICLE_COUNT * 2; i++)
			EbonModPacketHandler.sendParticlePacket("darkflame", player.posX + (rand.nextDouble() - 0.5D) * player.width, player.posY + rand.nextDouble() * player.height, player.posZ + (rand.nextDouble() - 0.5D) * player.width, 0.0D, 0.0D, 0.0D, false);

		return setDarkness(player, ++currentLevel);
	}

	public static int setDarkness(EntityPlayer player, int i) {
		File cacheFile = IOUtils.getWorldCacheFile(EnumVazkiiMods.EBON, player.worldObj);
		NBTTagCompound cmp = IOUtils.getTagCompoundInFile(cacheFile);
		int level = Math.max(0, i);
		cmp.setInteger("darknessLv_" + player.username, level);
		cmp.setInteger("darknessXP_" + player.username, 0);
		IOUtils.injectNBTToFile(cmp, cacheFile);
		EbonModPacketHandler.sendDarknessPacket(player);
		return level;
	}

	public static int getShadeRequiredForLevelUp(int xp) {
		return (int) Math.floor(1.75 * (xp * xp) + 4.9997 * xp + 0.1327);
	}

	public static int getShadeRemainingForPlayer(EntityPlayer player) {
		int xp = getShadeForPlayer(player);
		int level = getDarknessForPlayer(player);
		return getShadeRequiredForLevelUp(level) - xp;
	}

	public static int addShadeForPlayer(EntityPlayer player, int shade) {
		int currentShade = getShadeForPlayer(player);
		int remainderShade = getShadeRemainingForPlayer(player);
		boolean wouldLevelUp = remainderShade <= shade;
		int actualShade = wouldLevelUp ? shade - remainderShade : shade;

		if (wouldLevelUp) {
			evolveDarkness(player);
			return addShadeForPlayer(player, actualShade);
		}
		return setShadeForPlayer(player, currentShade + actualShade);
	}

	public static int getShadeForPlayer(EntityPlayer player) {
		File cacheFile = IOUtils.getWorldCacheFile(EnumVazkiiMods.EBON, player.worldObj);
		NBTTagCompound cmp = IOUtils.getTagCompoundInFile(cacheFile);
		return cmp.hasKey("darknessXP_" + player.username) ? Math.max(0, cmp.getInteger("darknessXP_" + player.username)) : 0;
	}

	public static int setShadeForPlayer(EntityPlayer player, int shade) {
		int actualShade = Math.max(0, Math.min(getShadeRequiredForLevelUp(getDarknessForPlayer(player)), shade));
		File cacheFile = IOUtils.getWorldCacheFile(EnumVazkiiMods.EBON, player.worldObj);
		NBTTagCompound cmp = IOUtils.getTagCompoundInFile(cacheFile);
		cmp.setInteger("darknessXP_" + player.username, actualShade);
		IOUtils.injectNBTToFile(cmp, cacheFile);
		return actualShade;
	}

	public static boolean isDarknessEnough(EntityPlayer player, int required) {
		return isDarknessEnough(player, required, true);
	}

	public static boolean isDarknessEnough(EntityPlayer player, int required, boolean warn) {
		boolean enough = player.worldObj instanceof WorldClient ? clientDarkness >= required : getDarknessForPlayer(player) >= required;
		if (!enough && !(player.worldObj instanceof WorldClient) && warn) CommonUtils.sendChatMessage(player, EbonModReference.MSG_DARK_TOO_LOW);

		return enough;
	}
}
