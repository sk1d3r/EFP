package com.efp;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public class EFPClient implements ClientModInitializer {

    public static final String MOD_ID = "efp";

    private static boolean enabled = true;
    private static KeyMapping toggleKey;

    public static boolean isEnabled() {
        return enabled;
    }

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath(MOD_ID, "main")
        );

        toggleKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.efp.toggle",
                InputConstants.Type.KEYBOARD,
                InputConstants.KEY_J,
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.consumeClick()) {
                enabled = !enabled;
            }
        });
    }
}
