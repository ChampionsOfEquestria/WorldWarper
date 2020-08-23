package org.sweetiebelle.worldwarper;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WarperListener implements Listener {

    private Settings settings;
    private WorldWarper plugin;

    public WarperListener(WorldWarper plugin, Settings settings) {
        this.plugin = plugin;
        this.settings = settings;
    }


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Location to = event.getTo();
        World toWorld = settings.worlds.get(to.getWorld());
        if(toWorld == null)
            return;
        Player player = event.getPlayer();
        if(player.hasPermission("worldwarper.bypass"))
            return;
        event.setTo(toWorld.getSpawnLocation());
        event.getPlayer().sendMessage(ChatColor.YELLOW + "You were teleported for being in an illegal world. You are not in trouble, this is an automated procedure due to a known bug.");
    }
    

}
