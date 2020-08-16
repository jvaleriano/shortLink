package com.meli.shortlink.server.repository;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.quarkus.runtime.api.reactive.mapper.MutinyMappedReactiveResultSet;
import com.meli.shortlink.server.domain.ShortLink;


@Dao
public interface ShortLinkDaoReactive {

    @Select
    MutinyMappedReactiveResultSet<ShortLink> findById(String id);
}
