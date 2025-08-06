package org.bindywashere.playersSex;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SexCommand implements CommandExecutor {

    private final SexManager sexManager;

    public SexCommand(SexManager sexManager) {
        this.sexManager = sexManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cЭту команду могут использовать только игроки!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("§2Использование: /sex <игрок>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            player.sendMessage("§4Игрок не найден!");
            return true;
        }

        if (player.getUniqueId().equals(target.getUniqueId())) {
            player.sendMessage("§4Вы не можете предложить секс самому себе!");
            return true;
        }

        if (player.getLocation().distance(target.getLocation()) > 4) {
            player.sendMessage("§4Игрок слишком далеко! Подойдите ближе (не далее 4 блоков).");
            return true;
        }

        if (!sexManager.canSendRequest(player)) {
            player.sendMessage("§4Вы уже отправили предложение! Подождите 1 час.");
            return true;
        }

        sexManager.sendRequest(player, target);

        player.sendMessage("§bВы предложили заняться сексом " + target.getName());
        target.sendMessage("§eИгрок " + player.getName() + " предложил вам заняться сексом.\n"
                + "§2Чтобы согласиться, напишите /sex_accept.\n"
                + "§4Чтобы отказаться, напишите /sex_decline.");

        return true;
    }
}
