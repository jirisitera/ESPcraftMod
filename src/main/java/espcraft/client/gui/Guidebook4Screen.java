
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

import espcraft.world.inventory.Guidebook4Menu;

import espcraft.network.Guidebook4ButtonMessage;

import espcraft.EspcraftMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class Guidebook4Screen extends AbstractContainerScreen<Guidebook4Menu> {
	private final static HashMap<String, Object> guistate = Guidebook4Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public Guidebook4Screen(Guidebook4Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 146;
		this.imageHeight = 220;
	}

	private static final ResourceLocation texture = new ResourceLocation("espcraft:textures/guidebook_4.png");

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

		RenderSystem.setShaderTexture(0, new ResourceLocation("espcraft:textures/espblock_receiver.png"));
		this.blit(ms, this.leftPos + 116, this.topPos + 10, 0, 0, 16, 16, 16, 16);

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
		this.font.draw(poseStack, "Acquiring signals:", 16, 14, -16724788);
		this.font.draw(poseStack, "The Receiver is a", 16, 46, -12829636);
		this.font.draw(poseStack, "mode of the ESPblock,", 16, 58, -12829636);
		this.font.draw(poseStack, "which allows you to", 16, 70, -12829636);
		this.font.draw(poseStack, "receive signals from", 16, 82, -12829636);
		this.font.draw(poseStack, "your ESP board. You", 16, 94, -12829636);
		this.font.draw(poseStack, "into a receiver by", 16, 118, -12829636);
		this.font.draw(poseStack, "Mode - Receiver", 16, 26, -16724788);
		this.font.draw(poseStack, "can turn your ESPblock", 16, 106, -12829636);
		this.font.draw(poseStack, "clicking on it and", 16, 130, -12829636);
		this.font.draw(poseStack, "pressing the", 16, 142, -12829636);
		this.font.draw(poseStack, "Receiver", 16, 154, -16737793);
		this.font.draw(poseStack, "Turn to", 84, 142, -16737793);
		this.font.draw(poseStack, "button.", 64, 154, -12829636);
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
		this.addRenderableWidget(new Button(this.leftPos + 76, this.topPos + 182, 56, 20, new TextComponent("Next"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new Guidebook4ButtonMessage(0, x, y, z));
				Guidebook4ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 12, this.topPos + 182, 56, 20, new TextComponent("Previous"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new Guidebook4ButtonMessage(1, x, y, z));
				Guidebook4ButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
	}
}
