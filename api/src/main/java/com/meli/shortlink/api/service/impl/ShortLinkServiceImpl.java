package com.meli.shortlink.api.service.impl;

import com.meli.shortlink.api.domain.ShortLink;
import com.meli.shortlink.api.repository.ShortLinkRepository;
import com.meli.shortlink.api.service.ShortLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing {@link ShortLink}.
 */
@Service
public class ShortLinkServiceImpl implements ShortLinkService {

    private final Logger log = LoggerFactory.getLogger(ShortLinkServiceImpl.class);

    private final ShortLinkRepository shortLinkRepository;

    public ShortLinkServiceImpl(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
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
    public Optional<ShortLink> findOne(UUID id) {
        log.debug("Request to get ShortLink : {}", id);
        return shortLinkRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        log.debug("Request to delete ShortLink : {}", id);
        shortLinkRepository.deleteById(id);
    }
}
