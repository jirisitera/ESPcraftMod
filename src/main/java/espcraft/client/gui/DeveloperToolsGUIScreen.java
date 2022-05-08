
package espcraft.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import espcraft.world.inventory.DeveloperToolsGUIMenu;

import espcraft.network.EspcraftModVariables;
import espcraft.network.DeveloperToolsGUIButtonMessage;

import espcraft.EspcraftMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class DeveloperToolsGUIScreen extends AbstractContainerScreen<DeveloperToolsGUIMenu> {
	private final static HashMap<String, Object> guistate = DeveloperToolsGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public DeveloperToolsGUIScreen(DeveloperToolsGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 170;
		this.imageHeight = 200;
	}

	private static final ResourceLocation texture = new ResourceLocation("espcraft:textures/developer_tools_gui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "Developer Tools", 47, 6, -12829636);
		this.font.draw(poseStack, "Player Modes", 55, 111, -12829636);
		this.font.draw(poseStack, "ESPcraft " + (EspcraftModVariables.MapVariables.get(world).version) + "", 82, 184, -12829636);
		this.font.draw(poseStack, "Current Location: " + x + " " + y + " " + z + "", 11, 22, -12829636);
		this.font.draw(poseStack, "Hotbar Fillers", 50, 40, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		this.addRenderableWidget(new Button(this.leftPos + 21, this.topPos + 57, 60, 20, new TextComponent("Building"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIButtonMessage(0, x, y, z));
				DeveloperToolsGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 88, this.topPos + 57, 60, 20, new TextComponent("Decoration"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIButtonMessage(1, x, y, z));
				DeveloperToolsGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 21, this.topPos + 82, 60, 20, new TextComponent("Redstone"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIButtonMessage(2, x, y, z));
				DeveloperToolsGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 88, this.topPos + 82, 60, 20, new TextComponent("Equipment"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIButtonMessage(3, x, y, z));
				DeveloperToolsGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 21, this.topPos + 128, 60, 20, new TextComponent("Survival"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIButtonMessage(4, x, y, z));
				DeveloperToolsGUIButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 88, this.topPos + 128, 60, 20, new TextComponent("Creative"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIButtonMessage(5, x, y, z));
				DeveloperToolsGUIButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 21, this.topPos + 152, 60, 20, new TextComponent("Adventure"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIButtonMessage(6, x, y, z));
				DeveloperToolsGUIButtonMessage.handleButtonAction(entity, 6, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 88, this.topPos + 152, 60, 20, new TextComponent("Spectator"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIButtonMessage(7, x, y, z));
				DeveloperToolsGUIButtonMessage.handleButtonAction(entity, 7, x, y, z);
			}
		}));
	}
}
