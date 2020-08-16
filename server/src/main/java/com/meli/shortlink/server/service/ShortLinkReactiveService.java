package com.meli.shortlink.server.service;

import com.meli.shortlink.server.domain.ShortLink;
import com.meli.shortlink.server.repository.ShortLinkDaoReactive;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ShortLinkReactiveService {

    @Inject
    ShortLinkDaoReactive dao;

    public Multi<ShortLink> get(String id) {
        return dao.findById(id);
    }

}