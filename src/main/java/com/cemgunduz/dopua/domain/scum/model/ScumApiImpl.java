package com.cemgunduz.dopua.domain.scum.model;

import com.cemgunduz.dopua.domain.scum.controller.input.DopuaCreationInput;
import com.cemgunduz.dopua.domain.scum.persistence.ScumDao;
import com.cemgunduz.dopua.domain.scum.persistence.ScumDto;
import com.cemgunduz.dopua.domain.history.model.HistoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by cgunduz on 3/24/2016.
 */

@Service
public class ScumApiImpl implements ScumApi {

    @Autowired
    ScumDao scumDao;

    @Autowired
    HistoryApi historyApi;

    @Autowired
    DopuaAwardValidator dopuaAwardValidator;

    private static final Logger logger = Logger.getLogger("Scum Api");

    @Override
    public Scum findScum(Integer id) {

        ScumDto scumDto = scumDao.findOne(id);
        return ScumFactory.fromDto(scumDto);
    }

    @Override
    public Scum findScum(String username) {

        List<ScumDto> result = scumDao.findByUsername(username);
        if(result.size() != 1) throw new IllegalArgumentException("Invalid username : ".concat(username));

        return ScumFactory.fromDto(result.get(0));
    }

    @Override
    public List<Scum> findAll() {

        List<Scum> result = new ArrayList<>();
        List<ScumDto> scumDtoList = scumDao.findAll();

        for(ScumDto dto : scumDtoList)
            result.add(ScumFactory.fromDto(dto));

        Collections.sort(result, new Comparator<Scum>() {

            @Override
            public int compare(Scum o1, Scum o2) {

                return o2.getTotalDopuaRecieved() - o1.getTotalDopuaRecieved();
            }
        });

        return result;
    }

    @Override
    public void giveDopua(DopuaCreationInput dopuaCreationInput) {

        // TODO : Return meaningful error response here
        if(!dopuaAwardValidator.isValidCreationRequest(dopuaCreationInput))
        {
            logger.severe("Invalid creation input : ".concat(dopuaCreationInput.toString()));
            return;
        }

        Scum awarder = findScum(dopuaCreationInput.getAwarderId());
        Scum rewarded = findScum(dopuaCreationInput.getRewardedId());
        awarder.awardDopua(dopuaCreationInput.getAmount(), rewarded , dopuaCreationInput.getReason());
        scumDao.save(ScumFactory.toDto(rewarded));

        historyApi.record(dopuaCreationInput);
    }
}
