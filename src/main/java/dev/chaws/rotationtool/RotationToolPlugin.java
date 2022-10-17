package dev.chaws.rotationtool;

import dev.chaws.rotationtool.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class RotationToolPlugin extends JavaPlugin {
	public static Logger log;
	public static RotationToolPlugin instance;

	@Override
	public void onEnable() {
		log = getLogger();
		instance = this;

		var pluginManager = this.getServer().getPluginManager();
		pluginManager.registerEvents(new PlayerListener(), this);

//		try {
//			new Metrics(this, 16554);
//		} catch (Throwable ignored) { }
	}
}
