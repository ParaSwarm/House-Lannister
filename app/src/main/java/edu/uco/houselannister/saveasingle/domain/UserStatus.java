package edu.uco.houselannister.saveasingle.domain;

import java.io.Serializable;
import java.util.*;

public class UserStatus  implements Serializable {

    private Integer AccountBalance;

    private Boolean isOnline;

    private Boolean isActive;

    private Boolean isHidden;

    private Date lastOnlineDate;

    public Integer getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        AccountBalance = accountBalance;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }

    public Date getLastOnlineDate() {
        return lastOnlineDate;
    }

    public void setLastOnlineDate(Date lastOnlineDate) {
        this.lastOnlineDate = lastOnlineDate;
    }
}