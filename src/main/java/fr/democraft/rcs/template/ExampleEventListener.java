package fr.democraft.rcs.template;

import fr.democraft.rcm.smart.events.DeletePhysicalServer;
import fr.democraft.rcm.smart.events.CreatePhysicalServer;
import group.aelysium.rustyconnector.common.events.EventListener;
import group.aelysium.rustyconnector.proxy.events.ServerLeaveEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import static fr.democraft.rcs.template.ExampleServerProvider.ID;

public class ExampleEventListener {
    private static final HashMap<String, Integer> serverCache = new HashMap<>();
    private static final HashMap<String, Integer> serverDeletitionWaitlist = new HashMap<>();

    @EventListener
    public static void createHandler(CreatePhysicalServer e) {
        // The Smart's manager call the same event for all the providers.
        // You need to check (if you want to) that YOU are the one in charge of creating this server.
        // Reminder: ID is your actual Smart Provider ID
        if (e.providerId != ID) return;

        // You now need to do an amazing magic trick to create the actual server.
        // I advise you to set the server ID "by force" and let the user choose the logic they want (nanoID/UUID)
        // This way, you can store this ID here and use it later
        // serverId = MyIDLogic()

        // You can use e.family.fetchMetadata() to get family's metadata, such as ram, or custom one you need!
        // realCloudProviderID = doMagicTrick(serverId, e.family)

        // To better manage server deletion, it's recommended to save the real server ID with the rusty's server ID.
        //serverCache.put(serverId, realCloudProviderID);
    }

    @EventListener
    public static void deleteHandler(DeletePhysicalServer e) {
        if (!Objects.equals(e.providerId, ID)) return;
        // You can't actually delete a server at soon as rusty/smart is ok with it. As players are playing on it. (Or not? You choose).
        // You should keep a track of servers needed to be deleted, and listen for player leave event to do so when it's convenient.
        serverDeletitionWaitlist.compute(e.family.id(), (k, waitlist) -> (waitlist == null) ? 1 : waitlist + 1);
    }
    
    @EventListener
    public static void playerLeaveHandler(ServerLeaveEvent e) throws IOException, InterruptedException {
        // If you decided to not delete servers when players are playing on it, then you need to delete them there when the player counts hit 0.
        if (serverDeletitionWaitlist.getOrDefault(e.server.family().get().id(), 0) > 0 && e.server.players() == 0) {
            // Do a magick trick!
        }
    }

}