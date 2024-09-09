package cn.qianzi.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Comparator;

import cn.qianzi.network.KillcupModVariables;

@Mod.EventBusSubscriber
public class AutoFuckProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity AimTarget = null;
		double MinDistance = 0;
		double OwnDistance = 0;
		double fanWei = 0;
		if ((entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KillcupModVariables.PlayerVariables())).isTargetVillager) {
			if ((entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KillcupModVariables.PlayerVariables())).isOn) {
				fanWei = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KillcupModVariables.PlayerVariables())).radius) * 2;
				MinDistance = fanWei;
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(fanWei / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entity == entityiterator)) {
							if (entityiterator instanceof Villager && entityiterator.isAlive()) {
								OwnDistance = Math.sqrt(Math.pow(x - entityiterator.getX(), 2) + Math.pow(y - entityiterator.getY(), 2) + Math.pow(z - entityiterator.getZ(), 2));
								if (OwnDistance < MinDistance && OwnDistance > 0.5) {
									MinDistance = OwnDistance;
								}
							}
						}
					}
				}
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(fanWei / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entity == entityiterator)) {
							if (entityiterator instanceof Villager && entityiterator.isAlive()) {
								OwnDistance = Math.sqrt(Math.pow(x - entityiterator.getX(), 2) + Math.pow(y - entityiterator.getY(), 2) + Math.pow(z - entityiterator.getZ(), 2));
								if (OwnDistance - 0.5 <= MinDistance) {
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() * 0.8 + 0.1), (entityiterator.getZ())));
								}
							}
						}
					}
				}
			}
		}
		if ((entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KillcupModVariables.PlayerVariables())).isTargetPlayer) {
			if ((entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KillcupModVariables.PlayerVariables())).isOn) {
				fanWei = new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((entity.getCapability(KillcupModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new KillcupModVariables.PlayerVariables())).radius) * 2;
				MinDistance = fanWei;
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(fanWei / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entity == entityiterator)) {
							if (entityiterator instanceof Player && entityiterator.isAlive()) {
								OwnDistance = Math.sqrt(Math.pow(x - entityiterator.getX(), 2) + Math.pow(y - entityiterator.getY(), 2) + Math.pow(z - entityiterator.getZ(), 2));
								if (OwnDistance < MinDistance && OwnDistance > 0.5) {
									MinDistance = OwnDistance;
								}
							}
						}
					}
				}
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(fanWei / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entity == entityiterator)) {
							if (entityiterator instanceof Player && entityiterator.isAlive()) {
								OwnDistance = Math.sqrt(Math.pow(x - entityiterator.getX(), 2) + Math.pow(y - entityiterator.getY(), 2) + Math.pow(z - entityiterator.getZ(), 2));
								if (OwnDistance - 0.5 <= MinDistance) {
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() * 0.8 + 0.1), (entityiterator.getZ())));
								}
							}
						}
					}
				}
			}
		}
	}
}
