package net.minecraft.src;
import net.minecraft.src.forge.ITextureProvider;

public class BlockOreStorageEbon extends BlockOreStorage implements ITextureProvider{

	public BlockOreStorageEbon(int i, int j) {
		super(i, j);
	}
	
	public String getTextureFile() {
		return "/vazkii/ebonmod/sprites.png";
	}

}
