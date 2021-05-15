package eu.byteexception.labymod;

import eu.byteexception.labymod.gui.icondata.DynamicIconData;
import eu.byteexception.labymod.listener.SettingsSynchronisationListener;
import eu.byteexception.labymod.server.OPSuchtLabyModServer;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.settings.elements.SettingsElement;

import java.util.List;
import java.util.logging.Logger;

public class OPSuchtLabyModAddon extends LabyModAddon {

    public final Logger logger = Logger.getLogger("Minecraft");

    public final ModuleCategory opSuchtModuleCategory = new ModuleCategory("OPSucht", true,
            new DynamicIconData("opsucht_logo", "https://files.byteexception.eu/img/v26EtegKIU.png"));

    @Override
    public void onEnable() {
        try {
            this.getApi().registerServerSupport(this, new OPSuchtLabyModServer(this, "opsucht_network",
                    "127.0.0.1", "localhost", "opsucht.net"));

            ModuleCategoryRegistry.loadCategory(this.opSuchtModuleCategory);

            this.getApi().getEventService().registerListener(new SettingsSynchronisationListener(this));
        } catch (Throwable exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }

}