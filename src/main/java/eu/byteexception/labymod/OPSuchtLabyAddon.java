package eu.byteexception.labymod;

import eu.byteexception.labymod.gui.modules.*;
import eu.byteexception.labymod.internal.listener.FlyModeUpdateListener;
import eu.byteexception.labymod.internal.listener.GodModeUpdateListener;
import eu.byteexception.labymod.internal.listener.PlayerSettingsSynchronizeListener;
import eu.byteexception.labymod.internal.listener.VanishModeUpdateListener;
import eu.byteexception.labymod.listener.labymod.DisconnectServerListener;
import eu.byteexception.labymod.listener.labymod.ServerMessageListener;
import eu.byteexception.labymod.server.OPSuchtLabyServer;
import lombok.Getter;
import net.labymod.api.LabyModAddon;
import net.labymod.api.event.Event;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OPSuchtLabyAddon extends LabyModAddon {

    @Getter
    private static OPSuchtLabyAddon instance;

    @Getter
    private OPSuchtLabyServer opSuchtLabyServer;

    @Getter
    private final Logger logger = Logger.getLogger("Minecraft");

    @Getter
    private final List<ILabyModule> modules = new LinkedList<>();

    @Getter
    private final List<String> moduleListener = new LinkedList<>();

    @Getter
    private final ModuleCategory opSuchtModuleCategory = new ModuleCategory("OPSucht", true, new IconData(Material.PAPER));

    @Override
    public void onEnable() {
        instance = this;

        // ---

        ModuleCategoryRegistry.loadCategory(this.opSuchtModuleCategory);

        this.loadModules();

        this.opSuchtLabyServer = new OPSuchtLabyServer(this, "opsucht_network",
                "127.0.0.1", "localhost", "OPSuchtNET", "opsucht.net", "test.byteexception.eu");

        this.getApi().registerServerSupport(this, this.getOpSuchtLabyServer());

        this.getApi().getEventService().registerListener(new ServerMessageListener(this));
        this.getApi().getEventService().registerListener(new DisconnectServerListener(this));
        this.getApi().getEventService().registerListener(new PlayerSettingsSynchronizeListener(this));
    }

    @Override
    public void loadConfig() {

    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {

    }

    private void loadModules() {
        this.getModules().addAll(Arrays.asList(
                new VanishModule(), new FlyModule(), new GodModule(),
                new GlowModule()
        ));

        this.getModules().forEach(this.getApi()::registerModule);

        this.getModuleListener().addAll(Stream.of(
                new VanishModeUpdateListener(),
                new FlyModeUpdateListener(),
                new GodModeUpdateListener()
        ).map(Object::getClass).map(Class::getCanonicalName).collect(Collectors.toList()));

        this.getLogger().info(String.format("Registered module(s): %s", this.getModules().stream().map(SimpleModule::getDisplayName).collect(Collectors.joining(", "))));
    }

    public void fireEvent(Event event) {
        this.getApi().getEventService().fireEvent(event);
    }

}