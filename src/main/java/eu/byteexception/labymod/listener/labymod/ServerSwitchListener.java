package eu.byteexception.labymod.listener.labymod;

import com.google.gson.JsonObject;
import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.settings.PlayerSettings;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.server.ServerSwitchEvent;

@AllArgsConstructor
public class ServerSwitchListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onServerSwitch(ServerSwitchEvent event) {
        if (!this.addon.getOpSuchtLabyServer().getConnected()) return;

        JsonObject jsonObject = new JsonObject();

        PlayerSettings.setPlayerSettings(jsonObject);

        jsonObject.addProperty("bank-visibility", this.addon.getAddonSettings().getBankVisibility());

        this.addon.getApi().sendJsonMessageToServer("opsucht_bank", jsonObject);
    }
}