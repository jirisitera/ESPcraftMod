
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package espcraft.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import espcraft.client.gui.EspguinormalScreen;
import espcraft.client.gui.DeveloperToolsGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EspcraftModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(EspcraftModMenus.ESPGUINORMAL, EspguinormalScreen::new);
			MenuScreens.register(EspcraftModMenus.DEVELOPER_TOOLS_GUI, DeveloperToolsGUIScreen::new);
		});
	}
}
