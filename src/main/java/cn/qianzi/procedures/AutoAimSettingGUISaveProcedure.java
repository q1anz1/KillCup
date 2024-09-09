package cn.qianzi.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;

import java.util.HashMap;

import cn.qianzi.network.KillcupModVariables;

public class AutoAimSettingGUISaveProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (!(guistate.containsKey("text:radius") ? ((EditBox) guistate.get("text:radius")).getValue() : "").isEmpty()) {
			{
				String _setval = guistate.containsKey("text:radius") ? ((EditBox) guistate.get("text:radius")).getValue() : "";
				entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.radius = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if (guistate.containsKey("checkbox:targetPlayer") && ((Checkbox) guistate.get("checkbox:targetPlayer")).selected()) {
			{
				boolean _setval = true;
				entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isTargetPlayer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				boolean _setval = false;
				entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isTargetPlayer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if (guistate.containsKey("checkbox:targetVillager") && ((Checkbox) guistate.get("checkbox:targetVillager")).selected()) {
			{
				boolean _setval = true;
				entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isTargetVillager = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				boolean _setval = false;
				entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isTargetVillager = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
