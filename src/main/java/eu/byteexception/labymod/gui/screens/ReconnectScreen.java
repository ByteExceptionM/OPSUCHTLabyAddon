package eu.byteexception.labymod.gui.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import eu.byteexception.labymod.listener.labymod.ScreenOpenListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IBidiRenderer;
import net.minecraft.client.gui.screen.ConnectingScreen;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class ReconnectScreen extends Screen {

    private final Screen parentScreen;

    private final ITextComponent message;

    private IBidiRenderer renderer = IBidiRenderer.field_243257_a;

    private Button reconnectButton;

    private Timer timer;

    private int secondsLeft = 5;

    private int textHeight;

    public ReconnectScreen(DisconnectedScreen disconnectedScreen, ITextComponent title) throws IllegalAccessException {
        super(title);

        Optional<Field> parentScreenField = Arrays.stream(DisconnectedScreen.class.getDeclaredFields())
                .filter(field -> Modifier.isPrivate(field.getModifiers()))
                .filter(field -> Modifier.isFinal(field.getModifiers()))
                .filter(field -> field.getName().equals("c") || field.getName().equals("field_146307_h") || field.getName().equals("nextScreen"))
                .findFirst();

        if (!parentScreenField.isPresent())
            throw new NullPointerException("Could not find parent screen field for reconnect screen");

        this.parentScreen = ((DisconnectedScreen) parentScreenField.get().get(disconnectedScreen));

        Optional<Field> messageField = Arrays.stream(DisconnectedScreen.class.getDeclaredFields())
                .filter(field -> Modifier.isPrivate(field.getModifiers()))
                .filter(field -> Modifier.isFinal(field.getModifiers()))
                .filter(field -> field.getName().equals("a") || field.getName().equals("field_146304_f") || field.getName().equals("message"))
                .findFirst();

        if (!messageField.isPresent())
            throw new NullPointerException("Could not find message field for reconnect screen");

        this.message = (ITextComponent) messageField.get().get(disconnectedScreen);

        if (this.message.getUnformattedComponentText().equals(I18n.format("disconnect.loginFailedInfo",
                I18n.format("disconnect.loginFailedInfo.invalidSession")))) {
            throw new IllegalStateException();
        }
    }

    @Override
    protected void init() {
        this.renderer = IBidiRenderer.func_243258_a(this.font, this.message, this.width - 50);

        this.textHeight = this.renderer.func_241862_a() * 9;

        this.addButton(new Button(this.width / 2 - 10,
                Math.min(this.height / 2 + this.textHeight / 2 + 9, this.height - 30), 125, 20,
                new TranslationTextComponent("gui.toMenu"), onClick -> {
            this.timer.cancel();
            Minecraft.getInstance().displayGuiScreen(this.parentScreen);
        }));

        this.reconnectButton = new Button(this.width / 2 - 115,
                Math.min(this.height / 2 + this.textHeight / 2 + 9, this.height - 30), 100, 20,
                new StringTextComponent("Reconnect in: §a" + this.secondsLeft + "s"), onClick -> {
            this.timer.cancel();

            Minecraft.getInstance().displayGuiScreen(
                    new ConnectingScreen(this.parentScreen, Minecraft.getInstance(), ScreenOpenListener.getLatestServer()));
        });

        this.addButton(this.reconnectButton);

        (this.timer = new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                ReconnectScreen.this.secondsLeft--;

                if (ReconnectScreen.this.secondsLeft == 0) {
                    ReconnectScreen.this.timer.cancel();
                    return;
                }

                String color = ReconnectScreen.this.secondsLeft > 3 ? "a" : ReconnectScreen.this.secondsLeft > 1 ? "e" : "c";

                ReconnectScreen.this.reconnectButton.setMessage(
                        new StringTextComponent("Reconnect in: §" + color + ReconnectScreen.this.secondsLeft + "s"));
            }
        }, 1000L, 1000L);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);

        drawCenteredString(matrixStack, this.font, this.title, this.width / 2, this.height / 2 - this.textHeight / 2 - 9 * 2, 11184810);

        this.renderer.func_241863_a(matrixStack, this.width / 2, this.height / 2 - this.textHeight / 2);

        super.render(matrixStack, mouseX, mouseY, partialTicks);

        if (this.secondsLeft != 0) return;

        this.reconnectButton.onPress();
    }

    @Override
    public void onClose() {
        super.onClose();
        this.timer.cancel();
    }
}