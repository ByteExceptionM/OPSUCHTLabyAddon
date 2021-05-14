package eu.byteexception.labymod.listener;

import com.google.gson.JsonObject;
import eu.byteexception.labymod.OPSuchtLabyModAddon;
import eu.byteexception.labymod.gui.modules.FlyModule;
import eu.byteexception.labymod.gui.modules.VanishModule;
import eu.byteexception.labymod.listener.labymod.FlyModuleUpdateListener;
import eu.byteexception.labymod.listener.labymod.VanishModuleUpdateListener;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.ServerMessageEvent;

public class SettingsSynchronisationListener {

    private final OPSuchtLabyModAddon addon;

    public SettingsSynchronisationListener(OPSuchtLabyModAddon addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onServerMessage(ServerMessageEvent event) {
        if (!event.getMessageKey().equals("opsucht_settings") && !PlayerSettings.settingsInitialized) return;

        JsonObject settingsObject = event.getServerMessage().getAsJsonObject();

        if (settingsObject.get("vanish").getAsBoolean()) {
            this.addon.getApi().registerModule(new VanishModule(this.addon, this.addon.opSuchtModuleCategory));
            this.addon.getApi().getEventService().registerListener(new VanishModuleUpdateListener(this.addon));
        }

        if (settingsObject.get("fly").getAsBoolean()) {
            this.addon.getApi().registerModule(new FlyModule(this.addon, this.addon.opSuchtModuleCategory));
            this.addon.getApi().getEventService().registerListener(new FlyModuleUpdateListener(this.addon));
        }

        PlayerSettings.settingsInitialized = true;
    }

    public static class PlayerSettings {

        private static Boolean settingsInitialized;

    }
}