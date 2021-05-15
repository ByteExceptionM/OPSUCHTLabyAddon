package eu.byteexception.labymod.gui.modules;

import eu.byteexception.labymod.OPSuchtLabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;

import java.util.Objects;

public class GlowModule extends SimpleModule {

    private final OPSuchtLabyModAddon addon;
    private final ModuleCategory moduleCategory;

    public GlowModule(OPSuchtLabyModAddon addon, ModuleCategory moduleCategory) {
        this.addon = addon;
        this.moduleCategory = moduleCategory;
    }

    @Override
    public ModuleCategory getCategory() {
        return this.moduleCategory;
    }

    @Override
    public String getDisplayName() {
        return "Glow";
    }

    @Override
    public String getDisplayValue() {
        ClientPlayerEntity clientPlayerEntity = Minecraft.getInstance().player;

        return Objects.nonNull(clientPlayerEntity) && clientPlayerEntity.isGlowing() ? ModColor.cl('a') + "An" : ModColor.cl('c') + "Aus";
    }

    @Override
    public String getDefaultValue() {
        return "?";
    }

    @Override
    public IconData getIconData() {
        return new ControlElement.IconData(Material.BEACON);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getControlName() {
        return "Glow-Status";
    }

    @Override
    public String getSettingName() {
        return "glow_state";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

}