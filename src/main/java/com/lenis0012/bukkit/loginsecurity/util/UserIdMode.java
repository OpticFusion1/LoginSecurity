package com.lenis0012.bukkit.loginsecurity.util;

import com.lenis0012.bukkit.loginsecurity.storage.PlayerProfile;
import java.util.UUID;

public enum UserIdMode {

    UNKNOWN("U"),
    MOJANG("M"),
    OFFLINE("O");

    private final String id;

    UserIdMode(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static UserIdMode fromId(String id) {
        for (UserIdMode mode : values()) {
            if (mode.id.equalsIgnoreCase(id)) {
                return mode;
            }
        }
        return null;
    }

    public String getUserId(final PlayerProfile profile) {
        if (profile.getUniqueIdMode() == this) {
            return profile.getUniqueUserId();
        }

        switch (this) {
            case OFFLINE:
                return profile.getLastName() == null ? profile.getUniqueUserId() : UUID.nameUUIDFromBytes(("OfflinePlayer:" + profile.getLastName().toLowerCase()).getBytes()).toString();
            case MOJANG:
                return profile.getUniqueUserId();
            default:
                throw new IllegalStateException("Invalid uuid mode: " + toString());
        }
    }
}
