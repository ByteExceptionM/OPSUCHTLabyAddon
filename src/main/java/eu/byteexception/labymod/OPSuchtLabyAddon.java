package eu.byteexception.labymod;

import eu.byteexception.labymod.gui.icondata.DynamicIconData;
import eu.byteexception.labymod.gui.modules.FlyModule;
import eu.byteexception.labymod.gui.modules.GlowModule;
import eu.byteexception.labymod.gui.modules.VanishModule;
import eu.byteexception.labymod.listener.SettingsSynchronisationListener;
import eu.byteexception.labymod.server.OPSuchtLabyServer;
import lombok.Getter;
import net.labymod.api.LabyModAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.SettingsElement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OPSuchtLabyAddon extends LabyModAddon {

    @Getter
    private final Logger logger = Logger.getLogger("Minecraft");

    @Getter
    private final List<SimpleModule> modules = new LinkedList<>();

    @Getter
    private final ModuleCategory opSuchtModuleCategory = new ModuleCategory("OPSucht", true,
            new DynamicIconData("opsucht_logo", "https://files.byteexception.eu/img/v26EtegKIU.png"));

    @Override
    public void onEnable() {
        this.getApi().registerServerSupport(this, new OPSuchtLabyServer(this, "opsucht_network",
                "127.0.0.1", "localhost", "OPSuchtNET", "opsucht.net"));

        ModuleCategoryRegistry.loadCategory(this.opSuchtModuleCategory);

        this.loadModules();

        this.getApi().getEventService().registerListener(new SettingsSynchronisationListener(this));
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }

    private void loadModules() {
        this.getModules().addAll(Arrays.asList(
                new VanishModule(this, this.getOpSuchtModuleCategory()),
                new FlyModule(this, this.getOpSuchtModuleCategory()),
                new GlowModule(this, this.getOpSuchtModuleCategory())
        ));

        this.getModules().forEach(this.getApi()::registerModule);

        this.getLogger().info(String.format("Registered module(s): %s", this.getModules().stream().map(SimpleModule::getDisplayName).collect(Collectors.joining(", "))));
    }
}