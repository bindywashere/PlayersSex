package org.bindywashere.PlayersSex;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SexAcceptCommand implements CommandExecutor {

    private final SexManager sexManager;

    public SexAcceptCommand(SexManager sexManager) {
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
        if (requester == null) {
            player.sendMessage("§4Игрок, отправивший запрос, вышел из игры.");
            sexManager.removeRequest(player);
            return true;
        }

        player.sendMessage("§aВы приняли предложение!");
        requester.sendMessage("§b" + player.getName() + " принял ваше предложение!");

        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count >= 5) {
                    cancel();
                    return;
                }
                requester.getWorld().spawnParticle(Particle.HEART, requester.getLocation().add(0, 2, 0), 10);
                player.getWorld().spawnParticle(Particle.HEART, player.getLocation().add(0, 2, 0), 10);
                count++;
            }
        }.runTaskTimer(PlayersSex.getPlugin(PlayersSex.class), 0L, 20L);

        sexManager.removeRequest(player);
        return true;
    }
}
