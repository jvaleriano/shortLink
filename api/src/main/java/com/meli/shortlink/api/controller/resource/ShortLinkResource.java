package com.meli.shortlink.api.controller.resource;

import com.meli.shortlink.api.controller.exception.BadRequestException;
import com.meli.shortlink.api.domain.ShortLink;
import com.meli.shortlink.api.service.ShortLinkService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.meli.shortlink.api.domain.ShortLink}.
 */
@RestController
@RequestMapping(value = "/api")
public class ShortLinkResource {

    private final Logger log = LoggerFactory.getLogger(ShortLinkResource.class);

    private final ShortLinkService shortLinkService;

    public ShortLinkResource(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    /**
     * {@code POST  /short-links} : Create a new shortLink.
     *
     * @param shortLink the shortLink to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shortLink, or with status {@code 400 (Bad Request)} if the shortLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/short-links")
    public ResponseEntity<ShortLink> createShortLink(@RequestBody ShortLink shortLink) throws URISyntaxException {
        log.debug("REST request to save ShortLink : {}", shortLink);
        if (shortLink.getId() != null) {
            throw new BadRequestException("shortLinkId","A new shortLink cannot already have an ID");
        }
        ShortLink result = shortLinkService.create(shortLink.getUrl());
        return ResponseEntity.created(new URI("/api/short-links/" + result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /short-links} : Updates an existing shortLink.
     *
     * @param shortLink the shortLink to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shortLink,
     * or with status {@code 400 (Bad Request)} if the shortLink is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shortLink couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/short-links")
    public ResponseEntity<ShortLink> updateShortLink(@RequestBody ShortLink shortLink) throws URISyntaxException {
        log.debug("REST request to update ShortLink : {}", shortLink);
        if (shortLink.getId() == null) {
            throw new BadRequestException("shortLinkId","Invalid can't be null");
        }
        ShortLink result = shortLinkService.save(shortLink);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code GET  /short-links} : get all the shortLinks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shortLinks in body.
     */
    @GetMapping("/short-links")
    public List<ShortLink> getAllShortLinks() {
        log.debug("REST request to get all ShortLinks");
        return shortLinkService.findAll();
    }

    /**
     * {@code GET  /short-links/:id} : get the "id" shortLink.
     *
     * @param id the id of the shortLink to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shortLink, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/short-links/{id}")
    public ResponseEntity<ShortLink> getShortLink(@PathVariable String id) {
        log.debug("REST request to get ShortLink : {}", id);
        Optional<ShortLink> shortLink = shortLinkService.findOne(id);
        return shortLink.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * {@code DELETE  /short-links/:id} : delete the "id" shortLink.
     *
     * @param id the id of the shortLink to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/short-links/{id}")
    public ResponseEntity<Void> deleteShortLink(@PathVariable String id) {
        log.debug("REST request to delete ShortLink : {}", id);
        shortLinkService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
