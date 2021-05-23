package eu.byteexception.labymod.listener.labymod;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.gui.screens.ReconnectScreen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.screen.ScreenOpenEvent;
import net.labymod.gui.GuiRefreshSession;
import net.labymod.gui.ModGuiMultiplayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ConnectingScreen;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.text.ITextComponent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public class ScreenOpenListener {

    private final OPSuchtLabyAddon addon;

    @Getter
    private static ServerData latestServer;

    @Subscribe
    public void onScreenOpen(ScreenOpenEvent event) {
        if (!this.addon.getAddonSettings().getAutoReconnectEnabled()) return;

        Screen screen = event.getScreen();

        if (screen instanceof ConnectingScreen) {
            latestServer = Minecraft.getInstance().getCurrentServerData();
            return;
        }

        if (!(screen instanceof DisconnectedScreen)) return;

        DisconnectedScreen disconnectedScreen = ((DisconnectedScreen) screen);

        try {
            Optional<Field> titleField = Arrays.stream(Screen.class.getDeclaredFields())
                    .filter(field -> Modifier.isProtected(field.getModifiers()))
                    .filter(field -> Modifier.isFinal(field.getModifiers()))
                    .filter(field -> field.getName().equals("d") || field.getName().equals("title"))
                    .findFirst();

            if (!titleField.isPresent()) return;

            screen = new ReconnectScreen(disconnectedScreen, ((ITextComponent) titleField.get().get(disconnectedScreen)));
        } catch (IllegalStateException ignored) {
            screen = new GuiRefreshSession(new ModGuiMultiplayer(null));
        } catch (IllegalAccessException exc) {
            exc.printStackTrace();
        }

        event.setScreen(screen);
    }
}