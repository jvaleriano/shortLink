package com.meli.shortlink.api.service;

import com.meli.shortlink.api.domain.ShortLink;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ShortLink}.
 */
public interface ShortLinkService {

    /**
     * Save a shortLink.
     *
     * @param shortLink the entity to save.
     * @return the persisted entity.
     */
    ShortLink save(ShortLink shortLink);

    /**
     * Create a shortLink.
     *
     * @param url to create shortLink.
     * @return the persisted entity.
     */
    ShortLink create(String url);

    /**
     * Get all the shortLinks.
     *
     * @return the list of entities.
     */
    List<ShortLink> findAll();


    /**
     * Get the "id" shortLink.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ShortLink> findOne(String id);

    /**
     * Delete the "id" shortLink.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
