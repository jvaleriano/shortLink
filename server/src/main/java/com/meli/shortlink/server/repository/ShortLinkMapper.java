package com.meli.shortlink.server.repository;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface ShortLinkMapper {

    @DaoFactory
    ShortLinkDao shortLinkDao();

    @DaoFactory
    ShortLinkDaoReactive shortLinkDaoReactive();
}

