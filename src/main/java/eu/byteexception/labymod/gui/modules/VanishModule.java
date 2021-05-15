package eu.byteexception.labymod.gui.modules;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.gui.settings.PlayerSettings;
import lombok.AllArgsConstructor;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;

@AllArgsConstructor
public class VanishModule extends SimpleModule {

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
        return "Vanish";
    }

    @Override
    public String getDisplayValue() {
        return VanishSetting.vanishMode ? ModColor.cl('a') + "An" : ModColor.cl('c') + "Aus";
    }

    @Override
    public String getDefaultValue() {
        return "?";
    }

    @Override
    public IconData getIconData() {
        return new IconData(Material.SPLASH_POTION);
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getControlName() {
        return "Vanish-Status";
    }

    @Override
    public String getSettingName() {
        return "vanish_state";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int getSortingId() {
        return 1;
    }

    public static class VanishSetting {

        public static Boolean vanishMode = false;

    }

}
