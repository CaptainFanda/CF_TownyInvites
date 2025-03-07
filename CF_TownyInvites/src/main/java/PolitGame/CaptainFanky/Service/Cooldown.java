package PolitGame.CaptainFanky.Service;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {

    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    public void setCooldown(UUID uuid) {
        cooldown.put(uuid, System.currentTimeMillis());
    }

    private Long getCooldown(UUID uuid) {
        return (System.currentTimeMillis() - cooldown.get(uuid))/1000;
    }

    public String getCooldownValue(UUID uuid, Integer timecd) {
        if(!cooldown.containsKey(uuid)) {
            return "Нет информации";
        }
        long cd = timecd-getCooldown(uuid);
        String time = cd+"сек.";
        return time;
    }

    public boolean isCooldown(UUID uuid, Integer time) {
        if(cooldown.containsKey(uuid)) {
            return getCooldown(uuid) <= time;
        }
        return false;
    }

}
