package vazkii.ebon.client;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import vazkii.ebon.common.EbonModPacketHandler;

import net.minecraft.src.KeyBinding;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class EbonModKeyHandler extends KeyHandler {

	public static KeyBinding key = new KeyBinding("Lexicon", Keyboard.KEY_L);

	public EbonModKeyHandler() {
		super(new KeyBinding[] { key }, new boolean[] { false });
	}

	@Override
	public String getLabel() {
		return "Ebon Mod";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		EbonModPacketHandler.sendKeyPacket();
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
