package com.cemgunduz.dopua.domain.scum.model;

import com.cemgunduz.dopua.domain.scum.controller.input.DopuaCreationInput;

import java.util.List;

/**
 * Created by cgunduz on 3/24/2016.
 */
public interface ScumApi {

    /**
     * Finds a scum given the parameters
     *
     * @param id
     * @return
     */
    public Scum findScum(Integer id);
    public Scum findScum(String username);
    public List<Scum> findAll();

    /**
     * Main entrypoint for awarding dopuas
     * Triggers a pipeline of services that is responsible for awarding, keeping history and notification
     *
     * @param dopuaCreationInput
     */
    public void giveDopua(DopuaCreationInput dopuaCreationInput);

}
