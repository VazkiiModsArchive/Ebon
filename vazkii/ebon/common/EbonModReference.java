package vazkii.ebon.common;

import vazkii.codebase.common.ColorCode;

public class EbonModReference {

	public static final String SPRITESHEET_PATH = "/vazkii/ebon/client/resources/sprites.png";

	public static final int PARTICLE_COUNT = 25;

	public static final int ME_TOOL_USE = 3;
	public static final int ME_DARKNESS_IMBIBER = 30;
	public static final int ME_STAFF = 4;
	public static final int ME_STAFF_SOULS = 6;
	public static final int ME_WAND_RETRIEVAL = 120;
	public static final int ME_SCEPTER = 30;
	public static final int ME_WAND_IMPRISIONMENT = 150;
	public static final int ME_APPLE = 900;

	public static final int SHADE_TOOL_USE = 2;
	public static final int SHADE_SWORD_KILL = 3;
	public static final int SHADE_IMBIBER = 5;
	public static final int SHADE_STAFF = 12;
	public static final int SHADE_ORB = 9;
	public static final int SHADE_STAFF_SOULS = 16;
	public static final int SHADE_WAND_RETRIEVAL = 80;
	public static final int SHADE_SCEPTER = 40;
	public static final int SHADE_WAND_IMPRISIONMENT = 180;
	public static final int SHADE_APPLE = 600;

	public static final int DARKNESS_MIN_IMBIBER = 2;
	public static final int DARKNESS_MIN_STAFF = 6;
	public static final int DARKNESS_MIN_ORB = 8;
	public static final int DARKNESS_MIN_STAFF_SOULS = 10;
	public static final int DARKNESS_MIN_LEXICON = 13;
	public static final int DARKNESS_MIN_WAND_RETRIEVAL = 15;
	public static final int DARKNESS_MIN_SCEPTER = 17;
	public static final int DARKNESS_MIN_ARMOR = 19;
	public static final int DARKNESS_MIN_VASE_SOULS = 21;
	public static final int DARKNESS_MIN_WAND_IMPRISIONMENT = 20;
	public static final int DARKNESS_MIN_APPLE = 25;

	public static final int INVENTORY_DARKNESS_OFFSET = 6;

	public static final int IMBIBER_RANGE = 6;
	public static final double QUICKSAND_SPEED = 0.05;
	public static final int ALTAR_RANGE = 5;
	public static final int WARD_RANGE = 8;
	public static final double WARD_SPEED = 16;
	public static final double EBON_TORCH_RANGE = 3D;
	public static final int EBON_TORCH_SPEED_MOD = 12;
	public static final int EBON_STAFF_DAMAGE = 9;
	public static final int ORB_OF_SOULS_DAMAGE = 20;
	public static final int STAFF_OF_SOULS_DAMAGE = 9;
	public static final int ZERO_SCEPTER_TIME = 5;
	public static final float ZERO_SCEPTER_POWER = 4F;
	public static final int VOID_SCEPTER_INSECT_COUNT = 4;
	public static final int VOID_INSECT_DURATION = 200;
	public static final int ARMOR_REGEN_TIME = 60;
	public static final int ARMOR_REVITALIZAION_TIME = 100;
	public static final double ARMOR_METEOR_MIN_MOTION = -0.9;
	public static final float ARMOR_METEOR_MOTION_MOD = 1.125F;
	public static final float ARMOR_METEOR_TICK_ADD = 0.15F;
	public static final int ARMOR_DEBILITATION_TIME = 40;
	public static final int ARMOR_DEBIITATION_RANGE = 8;
	public static final int ARMOR_DEBILITATION_POTION_TIME = 200;
	public static final double SHADE_BOTTLE_MODIFIER = 4D;
	public static final int VASE_OF_SOULS_MIN_XP = 8;
	public static final int SPECTER_VISIBLE_TIME = 2400;

	public static final String MSG_DARK_UP = ColorCode.PURPLE + "Your feel a Strong Dark Energy pulsating in your veins.";
	public static final String MSG_DARK_TOO_LOW = ColorCode.RED + "Your Soul is too Pure.";

	public static final String[] SOUND_NAMES = new String[] { "absorb", "clearExhaustion", "fail", "minium", "retrieval", "smite", "spell", "spNormal", "spRare", "tome", "tool", "transmute", "tsDeath", "vase" };

	public static final String VERSION = "4.0";
	public static final String UPDATE_URL = "https://dl.dropbox.com/u/34938401/Mods/On%20Topic/Mods/Ebon/Version.txt";
	public static final String CHANGELOG_URL = "https://dl.dropbox.com/u/34938401/Mods/On%20Topic/Mods/Ebon/Changelog.txt";
}
