package eu.byteexception.labymod.gui.modules;

import eu.byteexception.labymod.internal.listener.FlyModeUpdateListener;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;

public class FlyModule extends ILabyModule {

    @Override
    public String getDisplayName() {
        return "Fly";
    }

    @Override
    public String getDisplayValue() {
        return FlySetting.flyMode ? ModColor.cl('a') + "An" : ModColor.cl('c') + "Aus";
    }

    @Override
    public IconData getIconData() {
        return new ControlElement.IconData(Material.ELYTRA);
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
    public int getSortingId() {
        return 0;
    }

    @Override
    public String getListenerName() {
        return FlyModeUpdateListener.class.getCanonicalName();
    }

    public static class FlySetting {

        public static Boolean flyMode = false;

    }

}
