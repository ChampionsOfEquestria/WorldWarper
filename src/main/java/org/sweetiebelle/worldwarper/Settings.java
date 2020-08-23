package org.sweetiebelle.worldwarper;

import java.util.HashMap;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class Settings {
    /**
     * key = world to be teleported from<br>
     * value = world to teleport to
     */
    HashMap<World, World> worlds;
    private WorldWarper plugin;

    public Settings(WorldWarper plugin) {
        this.plugin = plugin;
        this.worlds = new HashMap<World, World>(0);
        readSettings();
        plugin.saveDefaultConfig();
    }

    private void readSettings() {
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("worlds");
        if (section != null) {
            for (String teleportToWorldName : section.getValues(false).keySet()) {
                World teleportToWorld = getWorldFromString(teleportToWorldName);
                if(teleportToWorld == null)
                    continue;
                for (String teleportFromWorldName : section.getStringList(teleportToWorldName)) {
                    World teleportFromWorld = getWorldFromString(teleportFromWorldName);
                    if(teleportFromWorld == null)
                        continue;
                    worlds.put(teleportFromWorld, teleportToWorld);
                }
            }
        }
    }
    
    public void reloadConfig() {
        worlds.clear();
        readSettings();
    }

    private World getWorldFromString(String name) {
        Optional<World> world = Optional.ofNullable(Bukkit.getWorld(name));
        if (world.isPresent())
            return world.get();
        plugin.getLogger().warning("Tried to load world " + name + " but it wasn't loaded.");
        return null;

    }
}
