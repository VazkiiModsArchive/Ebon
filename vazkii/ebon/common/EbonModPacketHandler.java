package vazkii.ebon.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import vazkii.codebase.client.ClientUtils;
import vazkii.codebase.common.CommonUtils;
import vazkii.ebon.client.EbonModClientTickHandler;
import vazkii.ebon.client.ParticleHelper;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class EbonModPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		if (packet.channel.equals("ebon_Vz")) onParticlePacket(manager, packet, player);
		else if (packet.channel.equals("ebon1_Vz")) onDarknessPacket(manager, packet, player);
		else if (packet.channel.equals("ebon2_Vz")) onDarknessRequestPacket(manager, packet, player);
		else if (packet.channel.equals("ebon3_Vz")) onKeyPacket(manager, packet, player);
		else if (packet.channel.equals("ebon4_Vz")) onLexiconUpdatePacket(manager, packet, player);
	}

	public void onParticlePacket(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			EntityPlayer entityPlayer = ClientUtils.getClientPlayer();

			double x = dataStream.readDouble();
			double y = dataStream.readDouble();
			double z = dataStream.readDouble();
			double velX = dataStream.readDouble();
			double velY = dataStream.readDouble();
			double velZ = dataStream.readDouble();
			String name = dataStream.readUTF();
			boolean vanilla = dataStream.readBoolean();

			if (vanilla) entityPlayer.worldObj.spawnParticle(name, x, y, z, velX, velY, velZ);
			else ParticleHelper.constructParticle(name, entityPlayer.worldObj, x, y, z, velX, velY, velZ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void onDarknessPacket(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			EbonModHelper.clientDarkness = dataStream.readInt();
			boolean warn = dataStream.readBoolean();
			if (EbonModClientTickHandler.lastDarknessPacket == 0) {
				if (EbonModHelper.clientDarkness != 0 && warn) ClientUtils.getClientPlayer().addChatMessage(EbonModReference.MSG_DARK_UP);
				EbonModClientTickHandler.lastDarknessPacket = 10;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void onDarknessRequestPacket(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		EntityPlayerMP mpPlayer = (EntityPlayerMP) player;
		sendDarknessPacket(mpPlayer, false);
	}

	public static void onKeyPacket(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		boolean has;
		EntityPlayerMP mpPlayer = (EntityPlayerMP) player;
		if (!EbonModHelper.isDarknessEnough(mpPlayer, EbonModReference.DARKNESS_MIN_LEXICON)) return;

		mpPlayer.worldObj.playSoundAtEntity(mpPlayer, "ebonmod.tome", 1.0F, 1.0F);
		if (EbonModHelper.doesPlayerHaveLexicon(mpPlayer)) {
			ItemStack stack = new ItemStack(mod_Ebon.necromancerLexicon);
			if (!mpPlayer.inventory.addItemStackToInventory(stack)) mpPlayer.dropPlayerItem(stack);
			EbonModHelper.setLexiconForPlayer(mpPlayer, false);
			has = false;
		} else {
			has = mpPlayer.inventory.consumeInventoryItem(mod_Ebon.necromancerLexicon.shiftedIndex);
			EbonModHelper.setLexiconForPlayer(mpPlayer, has);
		}

		sendLexiconUpdatePacket(mpPlayer.username, has);
	}

	public static void onLexiconUpdatePacket(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			EbonModHelper.clientLexicon = dataStream.readBoolean() ? 1 : 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendParticlePacket(String name, double x, double y, double z, double velX, double velY, double velZ, boolean vanilla) {
		Packet250CustomPayload packet = new Packet250CustomPayload();
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(byteStream);

		try {
			data.writeDouble(x);
			data.writeDouble(y);
			data.writeDouble(z);
			data.writeDouble(velX);
			data.writeDouble(velY);
			data.writeDouble(velZ);
			data.writeUTF(name);
			data.writeBoolean(vanilla);
		} catch (IOException e) {
			e.printStackTrace();
		}

		packet.channel = "ebon_Vz";
		packet.data = byteStream.toByteArray();
		packet.length = packet.data.length;

		CommonUtils.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
	}

	public static void sendDarknessPacket(EntityPlayer player) {
		sendDarknessPacket(player, true);
	}

	public static void sendDarknessPacket(EntityPlayer player, boolean warn) {
		Packet250CustomPayload packet = new Packet250CustomPayload();
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(byteStream);

		EntityPlayerMP mpPlayer = CommonUtils.getServer().getConfigurationManager().getPlayerForUsername(player.username);

		try {
			data.writeInt(EbonModHelper.getDarknessForPlayer(player));
			data.writeBoolean(warn);
		} catch (IOException e) {
			e.printStackTrace();
		}

		packet.channel = "ebon1_Vz";
		packet.data = byteStream.toByteArray();
		packet.length = packet.data.length;

		mpPlayer.serverForThisPlayer.sendPacketToPlayer(packet);
	}

	public static void sendRequestDarknessPacket() {
		sendEmptyPacket("ebon2_Vz");
	}

	public static void sendKeyPacket() {
		sendEmptyPacket("ebon3_Vz");
	}

	public static void sendLexiconUpdatePacket(String name, boolean lex) {
		Packet250CustomPayload packet = new Packet250CustomPayload();
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(byteStream);

		EntityPlayerMP mpPlayer = CommonUtils.getServer().getConfigurationManager().getPlayerForUsername(name);

		try {
			data.writeBoolean(lex);
		} catch (IOException e) {
			e.printStackTrace();
		}

		packet.channel = "ebon4_Vz";
		packet.data = byteStream.toByteArray();
		packet.length = packet.data.length;

		mpPlayer.serverForThisPlayer.sendPacketToPlayer(packet);
	}

	public static void sendEmptyPacket(String channel) {
		if (CommonUtils.getMc().thePlayer == null) return;

		Packet250CustomPayload packet = new Packet250CustomPayload();
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		new DataOutputStream(byteStream);

		packet.channel = channel;
		packet.data = byteStream.toByteArray();
		packet.length = packet.data.length;

		PacketDispatcher.sendPacketToServer(packet);
	}
}
