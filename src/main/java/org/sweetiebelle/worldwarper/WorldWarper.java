package org.sweetiebelle.worldwarper;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldWarper extends JavaPlugin {

    private Settings settings;
    private WarperListener listener;

    @Override
    public void onEnable() {
        settings = new Settings(this);
        listener = new WarperListener(this, settings);
        getServer().getPluginManager().registerEvents(listener, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("worldwarper.reload")) {
            settings.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Reloaded.");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Permission denied.");
        return true;

    }

}
