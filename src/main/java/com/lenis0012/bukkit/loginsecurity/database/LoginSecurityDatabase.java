package com.lenis0012.bukkit.loginsecurity.database;

import com.lenis0012.bukkit.loginsecurity.LoginSecurity;
import javax.sql.DataSource;

public class LoginSecurityDatabase {

    public static final int BATCH_SIZE = 1000;
    private final ProfileRepository profileRepository;
    private final InventoryRepository inventoryRepository;
    private final LocationRepository locationRepository;

    public LoginSecurityDatabase(LoginSecurity plugin, DataSource dataSource) {
        this.profileRepository = new ProfileRepository(plugin, dataSource);
        this.inventoryRepository = new InventoryRepository(plugin, dataSource);
        this.locationRepository = new LocationRepository(plugin, dataSource);
    }

    public ProfileRepository getProfileRepository() {
        return profileRepository;
    }

    public InventoryRepository getInventoryRepository() {
        return inventoryRepository;
    }

    public LocationRepository getLocationRepository() {
        return locationRepository;
    }

}
