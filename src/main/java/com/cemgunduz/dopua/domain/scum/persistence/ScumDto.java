package com.cemgunduz.dopua.domain.scum.persistence;

import com.cemgunduz.dopua.domain.scum.model.DopuaContainer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by cgunduz on 3/29/2016.
 */

@Document(collection = "Scum")
public class ScumDto {

    @Id
    private Integer id;
    private String username;
    private String password;
    //..
    private DopuaContainer container;

    public ScumDto() {
        container = new DopuaContainer();
    }

    public ScumDto(Integer id, String username, String password, DopuaContainer container) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.container = container;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DopuaContainer getContainer() {
        return container;
    }

    public void setContainer(DopuaContainer container) {
        this.container = container;
    }
}
