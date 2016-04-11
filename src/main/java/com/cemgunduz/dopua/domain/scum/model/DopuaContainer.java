package com.cemgunduz.dopua.domain.scum.model;

import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cgunduz on 3/23/2016.
 */
public class DopuaContainer {

    private List<Dopua> dopuaList = new ArrayList<>();

    @Transient
    private Map<Integer, Integer> awardMap;

    @Transient
    private Integer peakPoint = 0;

    @Transient
    private Integer peakAwarder;

    public void addToTaken(Dopua dopua)
    {
        dopuaList.add(dopua);
    }

    public List<Dopua> getDopuaListAwardedBy(Integer scumId)
    {
        List<Dopua> result = new ArrayList<>();
        for(Dopua dopua : dopuaList)
        {
            if(dopua.isAwardedBy(scumId))
                result.add(dopua);
        }

        return result;
    }

    public Integer getMaxAwardedScum()
    {
        if(awardMap == null)
        {
            awardMap = new HashMap<>();
            for(Dopua dopua : dopuaList)
            {
                Integer awarderId = dopua.getAwarder().getId();
                incrementAwardMap(awarderId);
            }
        }

        return peakAwarder;
    }

    public void incrementAwardMap(Integer awarderId)
    {
        if(awardMap == null) awardMap = new HashMap<>();

        if(awardMap.containsKey(awarderId))
        {
            Integer newAmount = awardMap.get(awarderId) + 1;
            awardMap.put(awarderId, newAmount);
            return;
        }

        awardMap.put(awarderId, 1);
        updatePeakPoint(awarderId);
    }

    public Integer getTotalAmountOfDopua()
    {
        Integer total = 0;
        for(Dopua dopua : dopuaList)
            total += dopua.getAmount();

        return total;
    }

    private void updatePeakPoint(Integer awarderId)
    {
        if(awardMap.get(awarderId) > peakPoint)
        {
            peakAwarder = awarderId;
            peakPoint = awardMap.get(awarderId);
        }
    }

    public List<Dopua> getDopuaList() {
        return dopuaList;
    }

    public void setDopuaList(List<Dopua> dopuaList) {
        this.dopuaList = dopuaList;
    }
}
