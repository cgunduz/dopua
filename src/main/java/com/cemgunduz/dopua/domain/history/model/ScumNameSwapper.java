package com.cemgunduz.dopua.domain.history.model;

import com.cemgunduz.dopua.domain.scum.model.Scum;
import com.cemgunduz.dopua.domain.scum.model.ScumApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by cgunduz on 4/5/2016.
 */

@Component
public class ScumNameSwapper {

    private static final Logger logger = Logger.getLogger("Name Retrieval");

    @Autowired
    ScumApi scumApi;

    public String swap(Integer scumId)
    {
        Scum scum = scumApi.findScum(scumId);
        if(scum == null)
        {
            String errorMessage = "Id not found for : ".concat(scumId.toString());
            logger.severe(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return scum.getUsername();
    }
}
