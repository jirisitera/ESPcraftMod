
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package espcraft.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import espcraft.client.gui.Guidebook5Screen;
import espcraft.client.gui.Guidebook4Screen;
import espcraft.client.gui.Guidebook3Screen;
import espcraft.client.gui.Guidebook2Screen;
import espcraft.client.gui.Guidebook1Screen;
import espcraft.client.gui.EspguinormalScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EspcraftModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(EspcraftModMenus.ESPGUINORMAL, EspguinormalScreen::new);
			MenuScreens.register(EspcraftModMenus.GUIDEBOOK_1, Guidebook1Screen::new);
			MenuScreens.register(EspcraftModMenus.GUIDEBOOK_2, Guidebook2Screen::new);
			MenuScreens.register(EspcraftModMenus.GUIDEBOOK_3, Guidebook3Screen::new);
			MenuScreens.register(EspcraftModMenus.GUIDEBOOK_4, Guidebook4Screen::new);
			MenuScreens.register(EspcraftModMenus.GUIDEBOOK_5, Guidebook5Screen::new);
		});
	}
}
