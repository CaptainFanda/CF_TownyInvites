package PolitGame.CaptainFanky;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConfigData {
    private static File config = new File("plugins/CF_TownyInvites/config.yml");
    private static File message = new File("plugins/CF_TownyInvites/message.yml");

    public static void createData() {
        YamlConfiguration ymlPut;
        if(!config.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(config);
            Map<String, Object> configMap = new HashMap<>();
            int cooldown = 300;
            ymlPut.addDefaults(configMap);
            ymlPut.addDefault("cooldown", cooldown);
            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(config);
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("Error to create Config.yml");
            }
        }
        if(!message.exists()) {
            ymlPut = YamlConfiguration.loadConfiguration(message);
            LinkedHashMap<String, Object> messages = new LinkedHashMap<>();
            messages.put("prefix", "[&eTowny&f] ");
            messages.put("have-town", "&cВы уже имеете город");
            messages.put("not-town", "&cУ вас нету Города");
            messages.put("not-mayor", "&cВы не мер города");
            messages.put("have-nation", "&cВы уже имеете нацию!");
            messages.put("not-king", "&cВы не король Нации");
            messages.put("not-nation", "&cУ вас нету нации");
            messages.put("cooldown", "Подождите Пожалуйста перед использование команды");
            List<String> townAsk = new ArrayList<>();
            townAsk.add("&aИгрок %player% ищет себе город!");
            townAsk.add("&a/t invite %player%");
            List<String> townSelection = new ArrayList<>();
            townSelection.add("&aГород %town% ищет людей!");
            townSelection.add("&a/t join %town%");
            List<String> nationAsk = new ArrayList<>();
            nationAsk.add("&aГород %town% ищет Нацию!");
            nationAsk.add("&a/n invite %town%");
            List<String> nationSelection = new ArrayList<>();
            nationSelection.add("&aНация %nation% Ищет города!");
            nationSelection.add("&a/n join %nation%");

            ymlPut.addDefaults(messages);
            ymlPut.addDefault("townAsk", townAsk);
            ymlPut.addDefault("townSelection", townSelection);
            ymlPut.addDefault("nationAsk", nationAsk);
            ymlPut.addDefault("nationSelection", nationSelection);

            ymlPut.options().copyDefaults(true);
            try {
                ymlPut.save(message);
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("Error to create Messagee.yml");
            }

        }
    }

    public static Map<String, String> loadMsgList() {
        Map<String, String> map = new HashMap<>();
        YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(message);
        Set<String> set = ymlPut.getKeys(true);
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String msg = ymlPut.getString(key);
            String msg1 = msg.replace('&', '§');
            map.put(key, msg1);
        }
        return map;
    }
    public static File getConfig() {
        return config;
    }

}
