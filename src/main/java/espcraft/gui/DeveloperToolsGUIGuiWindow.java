
package espcraft.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import espcraft.EspcraftModVariables;

import espcraft.EspcraftMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class DeveloperToolsGUIGuiWindow extends ContainerScreen<DeveloperToolsGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = DeveloperToolsGUIGui.guistate;
	public DeveloperToolsGUIGuiWindow(DeveloperToolsGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 170;
		this.ySize = 200;
	}
	private static final ResourceLocation texture = new ResourceLocation("espcraft:textures/developer_tools_gui.png");
	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Developer Tools", 47, 6, -12829636);
		this.font.drawString(ms, "Player Modes", 55, 111, -12829636);
		this.font.drawString(ms, "ESPcraft " + (EspcraftModVariables.MapVariables.get(world).version) + "", 82, 184, -12829636);
		this.font.drawString(ms, "Current Location: " + x + " " + y + " " + z + "", 11, 22, -12829636);
		this.font.drawString(ms, "Hotbar Fillers", 50, 40, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 21, this.guiTop + 57, 60, 20, new StringTextComponent("Building"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIGui.ButtonPressedMessage(0, x, y, z));
				DeveloperToolsGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 88, this.guiTop + 57, 60, 20, new StringTextComponent("Decoration"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIGui.ButtonPressedMessage(1, x, y, z));
				DeveloperToolsGUIGui.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 21, this.guiTop + 82, 60, 20, new StringTextComponent("Redstone"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIGui.ButtonPressedMessage(2, x, y, z));
				DeveloperToolsGUIGui.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 88, this.guiTop + 82, 60, 20, new StringTextComponent("Equipment"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIGui.ButtonPressedMessage(3, x, y, z));
				DeveloperToolsGUIGui.handleButtonAction(entity, 3, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 21, this.guiTop + 128, 60, 20, new StringTextComponent("Survival"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIGui.ButtonPressedMessage(4, x, y, z));
				DeveloperToolsGUIGui.handleButtonAction(entity, 4, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 88, this.guiTop + 128, 60, 20, new StringTextComponent("Creative"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIGui.ButtonPressedMessage(5, x, y, z));
				DeveloperToolsGUIGui.handleButtonAction(entity, 5, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 21, this.guiTop + 152, 60, 20, new StringTextComponent("Adventure"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIGui.ButtonPressedMessage(6, x, y, z));
				DeveloperToolsGUIGui.handleButtonAction(entity, 6, x, y, z);
			}
		}));
		this.addButton(new Button(this.guiLeft + 88, this.guiTop + 152, 60, 20, new StringTextComponent("Spectator"), e -> {
			if (true) {
				EspcraftMod.PACKET_HANDLER.sendToServer(new DeveloperToolsGUIGui.ButtonPressedMessage(7, x, y, z));
				DeveloperToolsGUIGui.handleButtonAction(entity, 7, x, y, z);
			}
		}));
	}
}
