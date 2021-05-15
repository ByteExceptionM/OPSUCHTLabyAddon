package eu.byteexception.labymod.server;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import net.labymod.api.event.events.client.gui.screen.overlay.PlayerTabListOverlayEvent.Type;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;

import java.util.List;

public class OPSuchtLabyServer extends Server {

    private final OPSuchtLabyAddon addon;

    public OPSuchtLabyServer(OPSuchtLabyAddon addon, String name, String... addressNames) {
        super(name, addressNames);

        this.addon = addon;
    }

    @Override
    public void onJoin(ServerData serverData) {
        this.addon.getLogger().info("Joined server with labymod addon support " + serverData.serverIP);
    }

    @Override
    public ChatDisplayAction handleChatMessage(String rawMessage, String formattedMessage) {
        return ChatDisplayAction.NORMAL;
    }

    @Override
    public void handlePayloadMessage(String channel, PacketBuffer packetBuffer) {
        this.addon.getLogger().info("Incoming payload message over channel " + channel);
    }

    @Override
    public void handleTabInfoMessage(Type type, String formattedMessage, String rawMessage) throws Exception {

    }

    @Override
    public void fillSubSettings(List<SettingsElement> list) {

    }
}
