package espcraft.procedures;

import net.minecraft.world.level.LevelAccessor;

public class ReceiverOnHandlerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		RedstoneProcedure.execute(world, x, y, z);
		UpdateBlockProcedure.execute(world, x, y, z);
	}
}
