package espcraft.procedures;

import java.util.HashMap;

import espcraft.network.EspcraftModVariables;

public class ReceiveMessageProcedure {
	public static void execute(HashMap cmdparams) {
		if (cmdparams == null)
			return;
		EspcraftModVariables.UUID = cmdparams.containsKey("0") ? cmdparams.get("0").toString() : "";
		EspcraftModVariables.Channel = cmdparams.containsKey("1") ? cmdparams.get("1").toString() : "";
		EspcraftModVariables.Power = cmdparams.containsKey("2") ? cmdparams.get("2").toString() : "";
	}
}
