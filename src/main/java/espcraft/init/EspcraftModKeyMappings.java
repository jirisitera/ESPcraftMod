
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package espcraft.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import espcraft.network.DeveloperToolsMessage;

import espcraft.EspcraftMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class EspcraftModKeyMappings {
	public static final KeyMapping DEVELOPER_TOOLS = new KeyMapping("key.espcraft.developer_tools", GLFW.GLFW_KEY_F4, "key.categories.creative");

	@SubscribeEvent
	public static void registerKeyBindings(FMLClientSetupEvent event) {
		ClientRegistry.registerKeyBinding(DEVELOPER_TOOLS);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onKeyInput(InputEvent.KeyInputEvent event) {
			if (Minecraft.getInstance().screen == null) {
				if (event.getKey() == DEVELOPER_TOOLS.getKey().getValue()) {
					if (event.getAction() == GLFW.GLFW_PRESS) {
						EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsMessage(0, 0));
						DeveloperToolsMessage.pressAction(Minecraft.getInstance().player, 0, 0);
					}
				}
			}
		}
	}
}
