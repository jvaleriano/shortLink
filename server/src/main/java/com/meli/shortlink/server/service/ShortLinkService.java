package com.meli.shortlink.server.service;

import com.meli.shortlink.server.domain.ShortLink;
import com.meli.shortlink.server.repository.ShortLinkDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ShortLinkService {

    @Inject
    private ShortLinkDao dao;

    public ShortLink get(String id) {
        return dao.findById(id);
    }
}