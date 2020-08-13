package com.meli.shortlink.api.service.impl;

import com.meli.shortlink.api.domain.ShortLink;
import com.meli.shortlink.api.repository.ShortLinkRepository;
import com.meli.shortlink.api.service.ShortLinkService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ShortLink}.
 */
@Service
public class ShortLinkServiceImpl implements ShortLinkService {

    private final Logger log = LoggerFactory.getLogger(ShortLinkServiceImpl.class);

    private final ShortLinkRepository shortLinkRepository;

    private final int ID_LENGTH = 6;

    public ShortLinkServiceImpl(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    @Override
    public synchronized ShortLink create(String url) {
        String s = RandomStringUtils.randomAlphabetic(ID_LENGTH);
        while (shortLinkRepository.findById(s).isPresent() ){
            s = RandomStringUtils.randomAlphabetic(ID_LENGTH);
        }
        ShortLink shortLink = new ShortLink(s,url);
        log.debug("Request to save ShortLink : {}", shortLink);
        return shortLinkRepository.save(shortLink);
    }


    @Override
    public ShortLink save(ShortLink shortLink) {
        log.debug("Request to save ShortLink : {}", shortLink);
        return shortLinkRepository.save(shortLink);
    }

    @Override
    public List<ShortLink> findAll() {
        log.debug("Request to get all ShortLinks");
        return shortLinkRepository.findAll();
    }


    @Override
    public Optional<ShortLink> findOne(String id) {
        log.debug("Request to get ShortLink : {}", id);
        return shortLinkRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ShortLink : {}", id);
        shortLinkRepository.deleteById(id);
    }
}
