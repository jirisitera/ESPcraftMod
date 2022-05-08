package espcraft.procedures;

import net.minecraftforge.server.ServerLifecycleHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.ChatType;
import net.minecraft.Util;

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
		if (!world.isClientSide()) {
			MinecraftServer _mcserv = ServerLifecycleHooks.getCurrentServer();
			if (_mcserv != null)
				_mcserv.getPlayerList().broadcastMessage(new TextComponent(EspcraftModVariables.MapVariables.get(world).Value), ChatType.SYSTEM,
						Util.NIL_UUID);
		}
	}
}
