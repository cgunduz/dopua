package com.cemgunduz.dopua.domain.history.model;

import com.cemgunduz.dopua.domain.scum.controller.input.DopuaCreationInput;
import com.cemgunduz.dopua.domain.history.persistence.HistoryDto;

/**
 * Created by cgunduz on 4/5/2016.
 */
public class HistoryFactory {

    static HistoryDto fromInput(DopuaCreationInput dopuaCreationInput)
    {
        return new HistoryDto(dopuaCreationInput.getAwarderId(), dopuaCreationInput.getRewardedId(),
                dopuaCreationInput.getAmount(), dopuaCreationInput.getReason());
    }
}
