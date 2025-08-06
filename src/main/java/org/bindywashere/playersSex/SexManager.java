package org.bindywashere.playersSex;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SexManager {
    private final HashMap<UUID, UUID> sexRequests = new HashMap<>();
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public boolean canSendRequest(Player sender) {
        return !cooldowns.containsKey(sender.getUniqueId()) || (System.currentTimeMillis() - cooldowns.get(sender.getUniqueId())) > 10000;
    }

    public void sendRequest(Player sender, Player target) {
        sexRequests.put(target.getUniqueId(), sender.getUniqueId());
        cooldowns.put(sender.getUniqueId(), System.currentTimeMillis());
    }

    public boolean hasRequest(Player target) {
        return sexRequests.containsKey(target.getUniqueId());
    }

    public UUID getRequester(Player target) {
        return sexRequests.get(target.getUniqueId());
    }

    public void removeRequest(Player target) {
        sexRequests.remove(target.getUniqueId());
    }
}
