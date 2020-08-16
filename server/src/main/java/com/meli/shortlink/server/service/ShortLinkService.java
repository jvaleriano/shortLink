package com.meli.shortlink.server.service;

import com.meli.shortlink.server.domain.ShortLink;
import com.meli.shortlink.server.repository.ShortLinkDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ShortLinkService {

    @Inject
    ShortLinkDao dao;

    public List<ShortLink> get(String id) {
        return dao.findById(id).all();
    }
}