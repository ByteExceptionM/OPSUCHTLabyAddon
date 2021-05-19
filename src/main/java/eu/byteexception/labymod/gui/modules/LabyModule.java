package eu.byteexception.labymod.gui.modules;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.gui.settings.PlayerSettings;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;

public abstract class LabyModule extends SimpleModule {

    public abstract String getListenerName();

    @Override
    public ModuleCategory getCategory() {
        return OPSuchtLabyAddon.getInstance().getOpSuchtModuleCategory();
    }

    @Override
    public boolean isShown() {
        return PlayerSettings.getBooleanValue(this.getSettingName()) && OPSuchtLabyAddon.getInstance().getOpSuchtLabyServer().getConnected();
    }

    @Override
    public String getDefaultValue() {
        return "?";
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getDescription() {
        return "";
    }
}
