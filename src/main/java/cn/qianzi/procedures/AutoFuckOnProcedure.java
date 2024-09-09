package cn.qianzi.procedures;

import net.minecraft.world.entity.Entity;

import cn.qianzi.network.KillcupModVariables;

public class AutoFuckOnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KillcupModVariables.PlayerVariables())).isOn) {
			{
				boolean _setval = false;
				entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isOn = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				boolean _setval = true;
				entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.isOn = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
