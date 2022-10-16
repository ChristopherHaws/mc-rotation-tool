package dev.chaws.rotationtool;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class RotationToolPlugin extends JavaPlugin {
	public static Logger log;

	@Override
	public void onEnable() {
		log = getLogger();

//		try {
//			new Metrics(this, 16554);
//		} catch (Throwable ignored) { }
	}
}
