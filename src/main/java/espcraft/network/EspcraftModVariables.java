package espcraft.network;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EspcraftModVariables {
	public static String version = "v.2.3.0";
	public static String UUID = "";
	public static String Channel = "";
	public static String Power = "";

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
	}
}
