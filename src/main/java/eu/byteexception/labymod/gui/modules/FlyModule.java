package eu.byteexception.labymod.gui.modules;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.gui.settings.PlayerSettings;
import lombok.AllArgsConstructor;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;

@AllArgsConstructor
public class FlyModule extends SimpleModule {

    private final OPSuchtLabyAddon addon;
    private final ModuleCategory moduleCategory;

    @Override
    public ModuleCategory getCategory() {
        return this.moduleCategory;
    }

    @Override
    public boolean isShown() {
        return PlayerSettings.getBooleanValue(this.getSettingName());
    }

    @Override
    public String getDisplayName() {
        return "Fly";
    }

    @Override
    public String getDisplayValue() {
        return FlySetting.flyMode ? ModColor.cl('a') + "An" : ModColor.cl('c') + "Aus";
    }

    @Override
    public String getDefaultValue() {
        return "?";
    }

    @Override
    public IconData getIconData() {
        return new ControlElement.IconData(Material.ELYTRA);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getControlName() {
        return "Fly-Status";
    }

    @Override
    public String getSettingName() {
        return "fly_state";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    public static class FlySetting {

        public static Boolean flyMode = false;

    }

}
