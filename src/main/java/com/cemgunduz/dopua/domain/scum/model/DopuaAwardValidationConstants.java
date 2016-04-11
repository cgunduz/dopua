package com.cemgunduz.dopua.domain.scum.model;

import com.cemgunduz.dopua.domain.scum.persistence.ScumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cgunduz on 4/7/2016.
 */
@Component
public class DopuaAwardValidationConstants {

    public static final Integer INTERVAL = 7;
    public static final Integer DOPUA_PER_INTERVAL = 5;
}
