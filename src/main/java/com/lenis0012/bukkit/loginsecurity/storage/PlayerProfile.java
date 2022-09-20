package com.lenis0012.bukkit.loginsecurity.storage;

import com.lenis0012.bukkit.loginsecurity.util.UserIdMode;
import java.sql.Date;
import java.sql.Timestamp;

public class PlayerProfile {

    private int id;
    private String uniqueUserId;
    private UserIdMode uniqueIdMode = UserIdMode.UNKNOWN;
    private String lastName;
    private String ipAddress;
    private String password;
    private int hashingAlgorithm;
    private Integer inventoryId;
    private Integer loginLocationId;
    private Timestamp lastLogin = new Timestamp(System.currentTimeMillis());
    private Date registrationDate = new Date(System.currentTimeMillis());
    private long version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniqueUserId() {
        return uniqueUserId;
    }

    public void setUniqueUserId(String uniqueUserId) {
        this.uniqueUserId = uniqueUserId;
    }

    public UserIdMode getUniqueIdMode() {
        return uniqueIdMode;
    }

    public void setUniqueIdMode(UserIdMode uniqueIdMode) {
        this.uniqueIdMode = uniqueIdMode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHashingAlgorithm() {
        return hashingAlgorithm;
    }

    public void setHashingAlgorithm(int hashingAlgorithm) {
        this.hashingAlgorithm = hashingAlgorithm;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getLoginLocationId() {
        return loginLocationId;
    }

    public void setLoginLocationId(Integer loginLocationId) {
        this.loginLocationId = loginLocationId;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}
