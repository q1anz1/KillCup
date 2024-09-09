
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package cn.qianzi.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import cn.qianzi.client.gui.AutoAimSettingGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KillcupModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(KillcupModMenus.AUTO_AIM_SETTING_GUI.get(), AutoAimSettingGUIScreen::new);
		});
	}
}
