package cn.qianzi.procedures;

import net.minecraft.world.entity.Entity;

import cn.qianzi.network.KillcupModVariables;

public class IsTargetPlayerProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KillcupModVariables.PlayerVariables())).isTargetPlayer) {
			return true;
		}
		return false;
	}
}
