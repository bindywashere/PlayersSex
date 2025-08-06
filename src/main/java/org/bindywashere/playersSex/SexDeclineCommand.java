package org.bindywashere.playersSex;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SexDeclineCommand implements CommandExecutor {

    private final SexManager sexManager;

    public SexDeclineCommand(SexManager sexManager) {
        this.sexManager = sexManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§4Эту команду могут использовать только игроки!");
            return true;
        }

        Player player = (Player) sender;
        if (!sexManager.hasRequest(player)) {
            player.sendMessage("§4У вас нет активных предложений!");
            return true;
        }

        Player requester = Bukkit.getPlayer(sexManager.getRequester(player));
        player.sendMessage("§4Вы отклонили предложение.");
        requester.sendMessage("§4" + player.getName() + " отклонил ваше предложение.");

        player.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, player.getLocation().add(0, 1, 0), 10);
        sexManager.removeRequest(player);
        return true;
    }
}
