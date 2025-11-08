package fr.democraft.rcs.template;

import fr.democraft.rcs.template.configs.MainConfig;
import group.aelysium.rustyconnector.common.events.EventManager;
import group.aelysium.rustyconnector.common.modules.ExternalModuleBuilder;
import group.aelysium.rustyconnector.common.modules.Module;
import group.aelysium.rustyconnector.proxy.ProxyKernel;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
WELCOME ON BOARD FANCY DEVELOPER!

You are looking at the Smart's Provider template. This code sample should help you to get started with Smart's Provider logic.
All classes/methods are commented, to give you a better idea of what is needed, and why.

Happy Codding!
 */

public class ExampleServerProvider implements Module {
    // This is the ID of your provider, used inside smart's manager?
    public static final String ID = "template";

    @Override
    public void close() throws Exception {
    }

    @Override
    public @Nullable Component details() {
        return Component.text("Pterodactyl Smart Connector");
    }

    public static class Builder extends ExternalModuleBuilder<ExampleServerProvider> {
        public void bind(@NotNull ProxyKernel kernel, @NotNull ExampleServerProvider instance) {
            MainConfig config = MainConfig.New();
            // You can also use System.getenv("NAME"); to override env values with environment variables
            System.out.println("Pterodactyl Smart Provider is registered!");
            // This line link your event listener to rusty.
            // If you create multiple files, you should add them after the ExampleEventListener
            kernel.<EventManager>fetchModule("EventManager").onStart(m -> {
                m.listen(ExampleEventListener.class);
            });
        }
        
        @NotNull
        @Override
        public ExampleServerProvider onStart(@NotNull Context context) throws Exception {
            return new ExampleServerProvider();
        }
    }
}