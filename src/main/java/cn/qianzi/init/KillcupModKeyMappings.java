
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package cn.qianzi.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import cn.qianzi.network.AutoAimSettingMessage;
import cn.qianzi.network.AutoAimOnMessage;
import cn.qianzi.KillcupMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class KillcupModKeyMappings {
	public static final KeyMapping AUTO_AIM_ON = new KeyMapping("key.killcup.auto_aim_on", GLFW.GLFW_KEY_G, "key.categories.autoaim") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				KillcupMod.PACKET_HANDLER.sendToServer(new AutoAimOnMessage(0, 0));
				AutoAimOnMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping AUTO_AIM_SETTING = new KeyMapping("key.killcup.auto_aim_setting", GLFW.GLFW_KEY_O, "key.categories.autoaim") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				KillcupMod.PACKET_HANDLER.sendToServer(new AutoAimSettingMessage(0, 0));
				AutoAimSettingMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(AUTO_AIM_ON);
		event.register(AUTO_AIM_SETTING);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				AUTO_AIM_ON.consumeClick();
				AUTO_AIM_SETTING.consumeClick();
			}
		}
	}
}
