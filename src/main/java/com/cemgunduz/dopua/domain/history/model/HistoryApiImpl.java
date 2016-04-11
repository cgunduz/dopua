package com.cemgunduz.dopua.domain.history.model;

import com.cemgunduz.dopua.domain.scum.controller.input.DopuaCreationInput;
import com.cemgunduz.dopua.domain.history.persistence.HistoryDao;
import com.cemgunduz.dopua.domain.history.persistence.HistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by cgunduz on 3/29/2016.
 */

@Service
public class HistoryApiImpl implements HistoryApi {

    private static final Logger logger = Logger.getLogger("HistoryApiImpl");

    @Autowired
    HistoryDao historyDao;

    @Autowired
    ScumNameSwapper nameSwapper;

    @Override
    public void record(DopuaCreationInput dopuaCreationInput) {

        HistoryDto historyDto = HistoryFactory.fromInput(dopuaCreationInput);

        String awarderUsername = nameSwapper.swap(historyDto.getAwarderId());
        String rewardedUsername = nameSwapper.swap(historyDto.getRewardedId());

        historyDto.setAwarderUsername(awarderUsername);
        historyDto.setRewardedUsername(rewardedUsername);

        historyDao.save(historyDto);

        logRecordMessage(dopuaCreationInput);
    }

    @Override
    public List<HistoryDto> retrieveHistory() {

        return retrieveHistory(4);
    }

    @Override
    public List<HistoryDto> retrieveHistory(int limit) {

        PageRequest request = new PageRequest(0, limit, new Sort(Sort.Direction.DESC, "createdDate"));
        List<HistoryDto> historyList = historyDao.findAll(request).getContent();

        return historyList;
    }

    private void logRecordMessage(DopuaCreationInput dopuaCreationInput)
    {
        Date today = new Date();

        StringBuilder logMessageBuilder = new StringBuilder();
        logMessageBuilder.append("As of ")
                .append(today.getTime())
                .append(" user with the id : ")
                .append(dopuaCreationInput.getAwarderId())
                .append(" has awarded ")
                .append(dopuaCreationInput.getAmount())
                .append(" to user with id : ")
                .append(dopuaCreationInput.getRewardedId())
                .append(" due to the reason : ")
                .append(dopuaCreationInput.getReason());

        logger.info(logMessageBuilder.toString());
    }
}
