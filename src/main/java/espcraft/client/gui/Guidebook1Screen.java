
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

import espcraft.world.inventory.Guidebook1Menu;

import espcraft.network.Guidebook1ButtonMessage;

import espcraft.EspcraftMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class Guidebook1Screen extends AbstractContainerScreen<Guidebook1Menu> {
	private final static HashMap<String, Object> guistate = Guidebook1Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public Guidebook1Screen(Guidebook1Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 146;
		this.imageHeight = 220;
	}

	private static final ResourceLocation texture = new ResourceLocation("espcraft:textures/guidebook_1.png");

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
		this.font.draw(poseStack, "Welcome to ESPcraft", 20, 14, -16724788);
		this.font.draw(poseStack, "Hi player!", 16, 30, -12829636);
		this.font.draw(poseStack, "This guide will teach", 16, 42, -12829636);
		this.font.draw(poseStack, "you about your very", 16, 54, -12829636);
		this.font.draw(poseStack, "Now then, lets begin.", 16, 130, -12829636);
		this.font.draw(poseStack, "own ESPblock!", 16, 66, -12829636);
		this.font.draw(poseStack, "Turn over to page 2", 16, 142, -12829636);
		this.font.draw(poseStack, "to continue.", 16, 154, -12829636);
		this.font.draw(poseStack, "But first, make sure ", 16, 86, -3407872);
		this.font.draw(poseStack, "that you have your", 16, 98, -3407872);
		this.font.draw(poseStack, "ESP board all set up.", 16, 110, -3407872);
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
		this.addRenderableWidget(new Button(this.leftPos + 24, this.topPos + 182, 97, 20, new TextComponent("Turn to page 2"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new Guidebook1ButtonMessage(0, x, y, z));
				Guidebook1ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
