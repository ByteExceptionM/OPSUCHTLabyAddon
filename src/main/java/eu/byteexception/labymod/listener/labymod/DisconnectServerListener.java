package eu.byteexception.labymod.listener.labymod;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.server.DisconnectServerEvent;

@AllArgsConstructor
public class DisconnectServerListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onDisconnectServer(DisconnectServerEvent event) {
        if (!this.addon.getOpSuchtLabyServer().getConnected()) return;

        this.addon.getOpSuchtLabyServer().setConnected(false);
    }
}