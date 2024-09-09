package cn.qianzi.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

import cn.qianzi.world.inventory.AutoAimSettingGUIMenu;
import cn.qianzi.procedures.IsTargetVillagerProcedure;
import cn.qianzi.procedures.IsTargetPlayerProcedure;
import cn.qianzi.network.AutoAimSettingGUIButtonMessage;
import cn.qianzi.KillcupMod;

public class AutoAimSettingGUIScreen extends AbstractContainerScreen<AutoAimSettingGUIMenu> {
	private final static HashMap<String, Object> guistate = AutoAimSettingGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox radius;
	Checkbox targetPlayer;
	Checkbox targetVillager;
	Button button_ok;

	public AutoAimSettingGUIScreen(AutoAimSettingGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		radius.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (radius.isFocused())
			return radius.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		radius.tick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.killcup.auto_aim_setting_gui.label_radius"), 33, 88, -1, false);
	}

	@Override
	public void init() {
		super.init();
		radius = new EditBox(this.font, this.leftPos + 79, this.topPos + 80, 26, 18, Component.translatable("gui.killcup.auto_aim_setting_gui.radius")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.killcup.auto_aim_setting_gui.radius").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.killcup.auto_aim_setting_gui.radius").getString());
				else
					setSuggestion(null);
			}
		};
		radius.setSuggestion(Component.translatable("gui.killcup.auto_aim_setting_gui.radius").getString());
		radius.setMaxLength(32767);
		guistate.put("text:radius", radius);
		this.addWidget(this.radius);
		button_ok = Button.builder(Component.translatable("gui.killcup.auto_aim_setting_gui.button_ok"), e -> {
			if (true) {
				KillcupMod.PACKET_HANDLER.sendToServer(new AutoAimSettingGUIButtonMessage(0, x, y, z));
				AutoAimSettingGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 78, this.topPos + 115, 35, 20).build();
		guistate.put("button:button_ok", button_ok);
		this.addRenderableWidget(button_ok);
		targetPlayer = new Checkbox(this.leftPos + 132, this.topPos + 115, 20, 20, Component.translatable("gui.killcup.auto_aim_setting_gui.targetPlayer"),

				IsTargetPlayerProcedure.execute(entity));
		guistate.put("checkbox:targetPlayer", targetPlayer);
		this.addRenderableWidget(targetPlayer);
		targetVillager = new Checkbox(this.leftPos + 132, this.topPos + 79, 20, 20, Component.translatable("gui.killcup.auto_aim_setting_gui.targetVillager"),

				IsTargetVillagerProcedure.execute(entity));
		guistate.put("checkbox:targetVillager", targetVillager);
		this.addRenderableWidget(targetVillager);
	}
}
