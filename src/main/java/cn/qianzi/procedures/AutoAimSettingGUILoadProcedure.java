package cn.qianzi.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

import cn.qianzi.network.KillcupModVariables;

public class AutoAimSettingGUILoadProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (guistate.get("text:radius") instanceof EditBox _tf)
			_tf.setValue(((entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KillcupModVariables.PlayerVariables())).radius));
	}
}
