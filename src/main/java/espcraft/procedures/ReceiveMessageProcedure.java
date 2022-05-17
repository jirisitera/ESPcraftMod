package espcraft.procedures;

import net.minecraft.world.level.LevelAccessor;

import java.util.HashMap;

import espcraft.network.EspcraftModVariables;

public class ReceiveMessageProcedure {
	public static void execute(LevelAccessor world, HashMap cmdparams) {
		if (cmdparams == null)
			return;
		EspcraftModVariables.MapVariables.get(world).UUID = cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "";
		EspcraftModVariables.MapVariables.get(world).syncData(world);
		EspcraftModVariables.MapVariables.get(world).Channel = cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "";
		EspcraftModVariables.MapVariables.get(world).syncData(world);
		EspcraftModVariables.MapVariables.get(world).Power = cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "";
		EspcraftModVariables.MapVariables.get(world).syncData(world);
	}
}
