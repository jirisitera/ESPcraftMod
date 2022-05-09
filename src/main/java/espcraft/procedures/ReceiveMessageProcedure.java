package espcraft.procedures;

import net.minecraft.world.level.LevelAccessor;

import java.util.HashMap;

import espcraft.network.EspcraftModVariables;

public class ReceiveMessageProcedure {
	public static void execute(LevelAccessor world, HashMap cmdparams) {
		if (cmdparams == null)
			return;
		EspcraftModVariables.MapVariables.get(world).Value = (cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "") + ""
				+ (cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "")
				+ (cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "");
		EspcraftModVariables.MapVariables.get(world).syncData(world);
	}
}
