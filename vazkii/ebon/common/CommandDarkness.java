package vazkii.ebon.common;

import vazkii.codebase.common.CommonUtils;
import net.minecraft.server.MinecraftServer;

import net.minecraft.src.CommandBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.PlayerNotFoundException;
import net.minecraft.src.ServerCommandManager;
import net.minecraft.src.WrongUsageException;

public class CommandDarkness extends CommandBase {

	@Override
	public String getCommandName() {
		return "darkness";
	}

	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender) {
		return "/darkness <level> [player]";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (var2.length > 0) {
			int var4 = parseIntBounded(var1, var2[0], 0, 5000);
			EntityPlayer var3;

			if (var2.length > 1) var3 = getPlayer(var2[1]);

			else var3 = getCommandSenderAsPlayer(var1);

			EbonModHelper.setDarkness(var3, var4);
			notifyAdmins(var1, String.format("Set %s's Darkness to %s.", var3.username, var4));
		} else throw new WrongUsageException("/darkness <level> [player]", new Object[0]);
	}

	private EntityPlayer getPlayer(String par1Str) {
		EntityPlayerMP var2 = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(par1Str);

		if (var2 == null) throw new PlayerNotFoundException();
		return var2;
	}

	protected void register() {
		ServerCommandManager manager = (ServerCommandManager) CommonUtils.getServer().getCommandManager();
		manager.registerCommand(this);
	}

}
