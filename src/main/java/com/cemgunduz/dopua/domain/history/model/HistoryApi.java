package com.cemgunduz.dopua.domain.history.model;

import com.cemgunduz.dopua.domain.scum.controller.input.DopuaCreationInput;
import com.cemgunduz.dopua.domain.history.persistence.HistoryDto;

import java.util.List;

/**
 * Created by cgunduz on 3/29/2016.
 */
public interface HistoryApi {

    /**
     * Creates a log and saves the history on mongo
     *
     * @param dopuaCreationInput
     */
    void record(DopuaCreationInput dopuaCreationInput);

    /**
     * Returns the list of latest entries on history
     *
     * @return
     */
    List<HistoryDto> retrieveHistory();
    List<HistoryDto> retrieveHistory(int limit);
}
