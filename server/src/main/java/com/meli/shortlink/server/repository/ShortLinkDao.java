package com.meli.shortlink.server.repository;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.meli.shortlink.server.domain.ShortLink;

@Dao
public interface ShortLinkDao {

    @Select
    ShortLink findById(String id);

}
