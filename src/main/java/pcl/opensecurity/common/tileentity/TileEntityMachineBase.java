package pcl.opensecurity.common.tileentity;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Environment;
import li.cil.oc.api.network.Message;
import li.cil.oc.api.network.Node;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pcl.opensecurity.client.sounds.ISoundTile;
import pcl.opensecurity.client.sounds.MachineSound;

public class TileEntityMachineBase extends TileEntity implements ITickable, Environment {
	
	public Boolean shouldPlay = false;
	
	public TileEntityMachineBase() {
		super();
	}

	@Override
	public void update() {
		if (worldObj.isRemote && hasSound()) {
			updateSound();
		}
	}

	// Sound related, thanks to EnderIO code for this!

	@SideOnly(Side.CLIENT)
	private MachineSound sound;

	private ResourceLocation soundRes;

	public String getSoundName() {
		return null;
	}

	public ResourceLocation setSound(String sound) {
		return null;
	}

	public ResourceLocation getSoundRes() {
		return soundRes;
	}

	public boolean getShouldPlay() {
		return shouldPlay;
	}

	public void setShouldPlay(boolean b) {
		shouldPlay = b;
	}
	
	public boolean hasSound() {
		return getSoundName() != null;
	}

	public float getVolume() {
		return 1.0f;
	}

	public float getPitch() {
		return 1.0f;
	}

	public boolean shouldRepeat() {
		return getShouldPlay();
	}

	@SideOnly(Side.CLIENT)
	public void updateSound() {
		if (hasSound()) {
			if ((getShouldPlay()) && !isInvalid()) {
				if (sound == null && this instanceof ISoundTile) {
						ISoundTile tile = (ISoundTile) this;
						soundRes = new ResourceLocation("opensecurity:" + tile.getSoundName());
						sound = new MachineSound(soundRes, this.getPos(), getVolume(), getPitch(), shouldRepeat());
						FMLClientHandler.instance().getClient().getSoundHandler().playSound(sound);
				}
			} else if (sound != null) {
				sound.endPlaying();
				sound = null;
			}
		}
	}

	public void setSoundRes(ResourceLocation soundRes) {
		this.soundRes = soundRes;
	}
	
	@Callback
	public Object[] greet(Context context, Arguments args) {
		return new Object[] { "Lasciate ogne speranza, voi ch'entrate" };
	}

	@Override
	public Node node() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onConnect(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnect(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

}