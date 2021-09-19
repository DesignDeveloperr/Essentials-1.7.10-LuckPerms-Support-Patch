package com.earth2me.essentials.perm;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LuckPermsHandler implements IPermissionsHandler {

    private final LuckPerms luckPerms;

    public LuckPermsHandler() {
        luckPerms = Bukkit.getServicesManager().getRegistration(LuckPerms.class).getProvider();
    }

    @Override
    public String getGroup(Player base) {
        return luckPerms.getPlayerAdapter(Player.class).getUser(base).getPrimaryGroup();
    }

    @Override
    public List<String> getGroups(Player base) {
        List<String> groups = new ArrayList<String>();
        for (Group group : luckPerms.getGroupManager().getLoadedGroups()) {
            if (base.hasPermission("group." + group.getName())) {
                groups.add(group.getName());
            }
        }
        return groups;
    }

    @Override
    public boolean canBuild(Player base, String group) {
        return true;
    }

    @Override
    public boolean inGroup(Player base, String group) {
        for (String groupName : getGroups(base)) {
            if (group.equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Player base, String node) {
        return base.hasPermission(node);
    }

    @Override
    public String getPrefix(Player base) {
        return luckPerms.getPlayerAdapter(Player.class).getUser(base).getCachedData().getMetaData().getPrefix();
    }

    @Override
    public String getSuffix(Player base) {
        return luckPerms.getPlayerAdapter(Player.class).getUser(base).getCachedData().getMetaData().getSuffix();
    }

}
