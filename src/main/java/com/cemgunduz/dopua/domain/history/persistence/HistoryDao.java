package com.cemgunduz.dopua.domain.history.persistence;


import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by cgunduz on 4/5/2016.
 */
public interface HistoryDao extends MongoRepository<HistoryDto,Integer> {

}
