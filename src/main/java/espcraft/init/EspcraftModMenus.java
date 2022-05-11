
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package espcraft.init;

import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.List;
import java.util.ArrayList;

import espcraft.world.inventory.Guidebook2Menu;
import espcraft.world.inventory.Guidebook1Menu;
import espcraft.world.inventory.EspguinormalMenu;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EspcraftModMenus {
	private static final List<MenuType<?>> REGISTRY = new ArrayList<>();
	public static final MenuType<EspguinormalMenu> ESPGUINORMAL = register("espguinormal",
			(id, inv, extraData) -> new EspguinormalMenu(id, inv, extraData));
	public static final MenuType<Guidebook1Menu> GUIDEBOOK_1 = register("guidebook_1",
			(id, inv, extraData) -> new Guidebook1Menu(id, inv, extraData));
	public static final MenuType<Guidebook2Menu> GUIDEBOOK_2 = register("guidebook_2",
			(id, inv, extraData) -> new Guidebook2Menu(id, inv, extraData));

	private static <T extends AbstractContainerMenu> MenuType<T> register(String registryname, IContainerFactory<T> containerFactory) {
		MenuType<T> menuType = new MenuType<T>(containerFactory);
		menuType.setRegistryName(registryname);
		REGISTRY.add(menuType);
		return menuType;
	}

	@SubscribeEvent
	public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new MenuType[0]));
	}
}
