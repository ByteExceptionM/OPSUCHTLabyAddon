package eu.byteexception.labymod.server;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import lombok.Getter;
import lombok.Setter;
import net.labymod.api.event.events.client.gui.screen.overlay.PlayerTabListOverlayEvent.Type;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;

import java.util.List;

public class OPSuchtLabyServer extends Server {

    private final OPSuchtLabyAddon addon;

    @Getter
    @Setter
    private Boolean connected = false;

    public OPSuchtLabyServer(OPSuchtLabyAddon addon, String name, String... addressNames) {
        super(name, addressNames);

        this.addon = addon;
    }

    @Override
    public void onJoin(ServerData serverData) {
        this.setConnected(true);
    }

    @Override
    public ChatDisplayAction handleChatMessage(String rawMessage, String formattedMessage) {
        return ChatDisplayAction.NORMAL;
    }

    @Override
    public void handlePayloadMessage(String channel, PacketBuffer packetBuffer) {

    }

    @Override
    public void handleTabInfoMessage(Type type, String formattedMessage, String rawMessage) {

    }

    @Override
    public void fillSubSettings(List<SettingsElement> list) {

    }
}