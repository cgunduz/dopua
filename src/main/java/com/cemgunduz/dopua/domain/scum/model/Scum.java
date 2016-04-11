package com.cemgunduz.dopua.domain.scum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by cgunduz on 3/23/2016.
 */
public class Scum {

    private Integer id;
    private String username;
    @JsonIgnore
    private String password;

    private DopuaContainer container;

    public Scum(Integer id, String username, String password, DopuaContainer container) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.container = container;
    }

    public void awardDopua(Integer amount, Scum reciever, String reason)
    {
        Dopua newDopua = new Dopua(amount, this, reason);
        reciever.recieveDopua(newDopua);
    }

    public void recieveDopua(Dopua dopua)
    {
        synchronized (id)
        {
            Integer awarderId = dopua.getAwarder().getId();

            container.addToTaken(dopua);
            container.incrementAwardMap(awarderId);
        }
    }

    public List<Dopua> getDopuaListAwardedBy(Integer scumId)
    {
        return container.getDopuaListAwardedBy(scumId);
    }

    public Integer getTotalDopuaRecieved()
    {
        return container.getTotalAmountOfDopua();
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public DopuaContainer getContainer() {
        return container;
    }
}
