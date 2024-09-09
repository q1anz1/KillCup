
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package cn.qianzi.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import cn.qianzi.world.inventory.AutoAimSettingGUIMenu;
import cn.qianzi.KillcupMod;

public class KillcupModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, KillcupMod.MODID);
	public static final RegistryObject<MenuType<AutoAimSettingGUIMenu>> AUTO_AIM_SETTING_GUI = REGISTRY.register("auto_aim_setting_gui", () -> IForgeMenuType.create(AutoAimSettingGUIMenu::new));
}
