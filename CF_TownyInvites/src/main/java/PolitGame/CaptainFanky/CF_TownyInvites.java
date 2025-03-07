package PolitGame.CaptainFanky;

import PolitGame.CaptainFanky.Commands.NationAsk;
import PolitGame.CaptainFanky.Commands.NationSelection;
import PolitGame.CaptainFanky.Commands.TownAsk;
import PolitGame.CaptainFanky.Commands.TownSelection;
import com.palmergames.bukkit.towny.TownyCommandAddonAPI;
import com.palmergames.bukkit.towny.TownyCommandAddonAPI.CommandType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class CF_TownyInvites extends JavaPlugin {
    private static Map<String, String> messages;

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigData.createData();
        messages = ConfigData.loadMsgList();
        TownyCommandAddonAPI.addSubCommand(CommandType.TOWN, "ask", new TownAsk());
        TownyCommandAddonAPI.addSubCommand(CommandType.TOWN, "selection", new TownSelection());
        TownyCommandAddonAPI.addSubCommand(CommandType.NATION, "ask", new NationAsk());
        TownyCommandAddonAPI.addSubCommand(CommandType.NATION, "selection", new NationSelection());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Map<String, String> getMassages() {
        return messages;
    }


}
