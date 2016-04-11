package com.cemgunduz.dopua.domain.scum.model;

import java.util.Date;

/**
 * Created by cgunduz on 3/23/2016.
 */
public class Dopua {

    private Integer amount;
    private Scum awarder;
    private Date creationDate;
    private String reason;

    public Dopua(Integer amount, Scum awarder, String reason) {
        this.amount = amount;
        this.awarder = awarder;
        this.reason = reason;
        this.creationDate = new Date();
    }

    public Integer getAmount() {
        return amount;
    }

    public Scum getAwarder() {
        return awarder;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getReason() {
        return reason;
    }

    public Boolean isAwardedBy(Integer scumId)
    {
        return awarder.getId().equals(scumId);
    }

}
