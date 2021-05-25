package eu.byteexception.labymod;

import eu.byteexception.labymod.gui.icondata.DynamicIconData;
import eu.byteexception.labymod.gui.modules.*;
import eu.byteexception.labymod.internal.listener.FlyModeUpdateListener;
import eu.byteexception.labymod.internal.listener.GodModeUpdateListener;
import eu.byteexception.labymod.internal.listener.PlayerSettingsSynchronizeListener;
import eu.byteexception.labymod.internal.listener.VanishModeUpdateListener;
import eu.byteexception.labymod.listener.labymod.*;
import eu.byteexception.labymod.server.OPSuchtLabyServer;
import eu.byteexception.labymod.settings.AddonSettings;
import lombok.Getter;
import net.labymod.api.LabyModAddon;
import net.labymod.api.event.Event;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.NumberElement;
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
    private AddonSettings addonSettings;

    @Getter
    private OPSuchtLabyServer opSuchtLabyServer;

    @Getter
    private final Logger logger = Logger.getLogger("Minecraft");

    @Getter
    private final List<LabyModule> modules = new LinkedList<>();

    @Getter
    private final List<String> moduleListener = new LinkedList<>();

    @Getter
    private final ModuleCategory opSuchtModuleCategory = new ModuleCategory("OPSUCHT", true,
            new DynamicIconData("opsucht_logo",
                    "data:image/png;base64,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"));

    @Override
    public void onEnable() {
        instance = this;

        // ---

        ModuleCategoryRegistry.loadCategory(this.opSuchtModuleCategory);

        this.loadModules();

        this.opSuchtLabyServer = new OPSuchtLabyServer(this, "opsucht_network",
                "test.byteexception.eu", "localhost", "opsucht.net", "play.opsucht.net", "mc.opsucht.net");

        this.getApi().registerServerSupport(this, this.getOpSuchtLabyServer());

        Stream.of(
                new ServerMessageListener(this),
                new DisconnectServerListener(this),
                new PlayerSettingsSynchronizeListener(this),
                new MessageModifyListener(this),
                new ServerSwitchListener(this),
                new ScreenOpenListener(this),
                new LoginServerListener()
        ).forEach(this.getApi().getEventService()::registerListener);
    }

    @Override
    public void loadConfig() {
        this.addonSettings = new AddonSettings(
                this.getConfig().has("autoReconnect") && this.getConfig().get("autoReconnect").getAsBoolean(),
                this.getConfig().has("autoReconnectDelay") ? this.getConfig().get("autoReconnectDelay").getAsInt() : 5,
                this.getConfig().has("clickableNicks") && this.getConfig().get("clickableNicks").getAsBoolean()
        );
    }

    @Override
    protected void fillSettings(List<SettingsElement> settingsElements) {

        // ---

        settingsElements.add(new HeaderElement("§eAuto-Reconnect"));
        settingsElements.add(
                new BooleanElement("§6Aktiviert", new IconData(Material.LEVER), value -> {
                    this.addonSettings.setAutoReconnectEnabled(value);
                    this.getConfig().addProperty("autoReconnect", value);
                    this.saveConfig();
                }, this.addonSettings.getAutoReconnectEnabled())
        );
        settingsElements.add(new NumberElement("§6Verzögerung", new IconData(Material.REDSTONE_LAMP), this.addonSettings.getAutoReconnectDelay())
                .setRange(5, 60)
                .addCallback(value -> {
                    this.addonSettings.setAutoReconnectDelay(value);
                    this.getConfig().addProperty("autoReconnectDelay", value);
                    this.saveConfig();
                }));

        // ---

        settingsElements.add(new HeaderElement(" "));
        settingsElements.add(new HeaderElement("§eKlickbare Nicknamen"));
        settingsElements.add(
                new BooleanElement("§6Aktiviert", new IconData(Material.LEVER), value -> {
                    this.addonSettings.setClickableNicks(value);
                    this.getConfig().addProperty("clickableNicks", value);
                    this.saveConfig();
                }, this.addonSettings.getClickableNicks())
        );
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