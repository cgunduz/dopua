package com.cemgunduz.dopua.domain.scum.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by cgunduz on 3/24/2016.
 */
public interface ScumDao extends MongoRepository<ScumDto,Integer> {

    public List<ScumDto> findByUsername(String username);


}
