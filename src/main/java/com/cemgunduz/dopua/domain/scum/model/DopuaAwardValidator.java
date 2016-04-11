package com.cemgunduz.dopua.domain.scum.model;

import com.cemgunduz.dopua.domain.scum.controller.input.DopuaCreationInput;
import com.cemgunduz.dopua.domain.scum.persistence.ScumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by cgunduz on 4/7/2016.
 */
@Component
public class DopuaAwardValidator {

    @Autowired
    ScumDao scumDao;

    public Boolean isValidCreationRequest(DopuaCreationInput dopuaCreationInput)
    {
        if(!inputIsValid(dopuaCreationInput))
            return false;

        Integer awarderId = dopuaCreationInput.getAwarderId();
        Integer rewardedId = dopuaCreationInput.getRewardedId();
        Integer amount = dopuaCreationInput.getAmount();

        return isPositiveAward(amount) && isRewardedValid(awarderId, rewardedId) && !isLimitExceeded(awarderId, amount);
    }

    private Boolean inputIsValid(DopuaCreationInput dopuaCreationInput)
    {
        return !dopuaCreationInput.isEmpty();
    }

    // TODO : Fix
    private List<Dopua> getGivenDopuaList(Integer scumId)
    {
        List<Scum> scumList = ScumFactory.fromDto(scumDao.findAll());

        List<Dopua> dopuaList = new ArrayList<>();
        for(Scum scum : scumList)
        {
            dopuaList.addAll(scum.getDopuaListAwardedBy(scumId));
        }

        return dopuaList;
    }

    private Boolean isLimitExceeded(Integer scumId, Integer awardedDopuaAmount)
    {
        List<Dopua> dopuaList = getGivenDopuaList(scumId);
        Long now = new Date().getTime();
        Long expectedMaximum = now - TimeUnit.DAYS.toMillis(DopuaAwardValidationConstants.INTERVAL);

        int totalDopuasWithinInterval = 0;
        for(Dopua dopua : dopuaList)
        {
            if(dopua.getCreationDate().getTime() > expectedMaximum)
            {
                totalDopuasWithinInterval++;
                if(totalDopuasWithinInterval + awardedDopuaAmount > DopuaAwardValidationConstants.DOPUA_PER_INTERVAL)
                    return true;
            }
        }

        return false;
    }

    private Boolean isPositiveAward(Integer awardedDopuaAmount)
    {
        return awardedDopuaAmount > 0;
    }

    private Boolean isRewardedValid(Integer awarderId, Integer rewardedId)
    {
        return !awarderId.equals(rewardedId);
    }
}
