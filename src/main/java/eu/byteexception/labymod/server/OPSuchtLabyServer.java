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
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        this.connected = true;
        Executors.newSingleThreadScheduledExecutor().schedule(() ->
                this.addon.getModuleListener().stream().filter(listenerClazz -> this.addon.getModules().stream().filter(labyModule -> Objects.nonNull(labyModule.getListenerName())).anyMatch(labyModule ->
                        labyModule.getListenerName().equals(listenerClazz) && labyModule.isShown())).map(clazz -> {
                    try {
                        return Class.forName(clazz).newInstance();
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList()).forEach(listener -> {
                    this.addon.getModuleListener().remove(listener.getClass().getCanonicalName());
                    this.addon.getApi().getEventService().registerListener(listener);
                    this.addon.getLogger().info("Registering listener " + listener.getClass().getSimpleName());
                }), 500, TimeUnit.MILLISECONDS);
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
