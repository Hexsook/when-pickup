package me.hexsook.whenpickup;

import hexsook.originext.config.ConfigurationException;
import hexsook.originext.config.file.FileConfiguration;
import hexsook.originext.config.file.adapter.YamlFileConfiguration;

import java.io.File;

public class Config {

    private static FileConfiguration config;

    public static void load() throws ConfigurationException {
        File dataFolder = WhenPickup.getInstance().getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        config = new FileConfiguration(new File(dataFolder, "config.yml"),
                Config.class.getResourceAsStream("/config.yml"), YamlFileConfiguration.class);
    }

    public static FileConfiguration getConfig() {
        return config;
    }
}
