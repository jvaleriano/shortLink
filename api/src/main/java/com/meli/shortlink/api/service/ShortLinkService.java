package com.meli.shortlink.api.service;

import com.meli.shortlink.api.domain.ShortLink;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    Optional<ShortLink> findOne(UUID id);

    /**
     * Delete the "id" shortLink.
     *
     * @param id the id of the entity.
     */
    void delete(UUID id);
}
