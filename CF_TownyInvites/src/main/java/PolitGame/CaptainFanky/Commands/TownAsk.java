package PolitGame.CaptainFanky.Commands;

import PolitGame.CaptainFanky.CF_TownyInvites;
import PolitGame.CaptainFanky.ConfigData;
import PolitGame.CaptainFanky.Service.Cooldown;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public class TownAsk extends Cooldown implements CommandExecutor {
    private static String prefix = CF_TownyInvites.getMassages().get("prefix");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player p) {
            File config = ConfigData.getConfig();
            YamlConfiguration ymlPut = YamlConfiguration.loadConfiguration(config);
            int cooldown = ymlPut.getInt("cooldown");
            Town town = TownyAPI.getInstance().getTown(p);
            if(town != null) {
                String message = CF_TownyInvites.getMassages().get("have-town");
                p.sendMessage(message);
            } else {
                if(isCooldown(p.getUniqueId(), cooldown)) {
                    String message = CF_TownyInvites.getMassages().get("cooldown");
                    p.sendMessage(message);
                    return true;
                } else {
                    setCooldown(p.getUniqueId());
                    String message = prefix + CF_TownyInvites.getMassages().get("townAsk");
                    message = message.replace("%player%", p.getName());
                    Bukkit.broadcastMessage(message);
                }

            }
        }
        return true;
    }
}
