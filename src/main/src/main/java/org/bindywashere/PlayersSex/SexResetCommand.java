package org.bindywashere.PlayersSex;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SexResetCommand implements CommandExecutor {

    private final SexManager sexManager;

    public SexResetCommand(SexManager sexManager) {
        this.sexManager = sexManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("playerssex.others")) {
            sender.sendMessage("§cУ вас нет прав на сброс КД!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§cИспользование: /sex_reset <игрок>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            sender.sendMessage("§cИгрок не найден!");
            return true;
        }

        sexManager.resetCooldown(target);
        sender.sendMessage("§aКД игрока " + target.getName() + " сброшено!");
        return true;
    }
}
