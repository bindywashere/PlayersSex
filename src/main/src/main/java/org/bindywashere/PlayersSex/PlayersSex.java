package org.bindywashere.PlayersSex;

import org.bukkit.plugin.java.JavaPlugin;

public class PlayersSex extends JavaPlugin {

    private SexManager sexManager;

    @Override
    public void onEnable() {
        this.sexManager = new SexManager();

        getCommand("sex").setExecutor(new SexCommand(sexManager));
        getCommand("sex_accept").setExecutor(new SexAcceptCommand(sexManager));
        getCommand("sex_decline").setExecutor(new SexDeclineCommand(sexManager));
        getCommand("sex_reset").setExecutor(new SexResetCommand(sexManager));

        getLogger().info("PlayersSex успешно загружен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PlayersSex отключён.");
    }
}
