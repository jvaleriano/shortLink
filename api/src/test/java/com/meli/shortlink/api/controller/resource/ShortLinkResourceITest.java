package com.meli.shortlink.api.controller.resource;

import com.meli.shortlink.api.AbstractCassandraTest;
import com.meli.shortlink.api.ApiApplication;
import com.meli.shortlink.api.TestUtil;
import com.meli.shortlink.api.domain.ShortLink;
import com.meli.shortlink.api.repository.ShortLinkRepository;
import com.meli.shortlink.api.service.ShortLinkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ShortLinkResource} REST controller.
 */
@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
public class ShortLinkResourceITest extends AbstractCassandraTest {

    private static final String DEFAULT_URL = "https://www.mercadolibre.com.ar/ofertas";
    private static final String UPDATED_URL = "https://www.mercadolibre.com.ar/tiendas-oficiales#nav-header";

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    @Autowired
    private ShortLinkService shortLinkService;

    @Autowired
    private MockMvc restShortLinkMockMvc;

    private ShortLink shortLink;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShortLink createEntity() {
        ShortLink shortLink = new ShortLink();
            shortLink.setUrl(DEFAULT_URL);
        return shortLink;
    }

    @BeforeEach
    public void initTest() {
        shortLinkRepository.deleteAll();
        shortLink = createEntity();
    }

    @Test
    public void createShortLink() throws Exception {
        int databaseSizeBeforeCreate = shortLinkRepository.findAll().size();
        // Create the ShortLink
        restShortLinkMockMvc.perform(post("/api/short-links")
            .contentType(MediaType.APPLICATION_JSON)

            .content(TestUtil.convertObjectToJsonBytes(shortLink)))
            .andExpect(status().isCreated());

        // Validate the ShortLink in the database
        List<ShortLink> shortLinkList = shortLinkRepository.findAll();
        assertThat(shortLinkList).hasSize(databaseSizeBeforeCreate + 1);
        ShortLink testShortLink = shortLinkList.get(shortLinkList.size() - 1);
        assertThat(testShortLink.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    public void createShortLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shortLinkRepository.findAll().size();

        // Create the ShortLink with an existing ID
        shortLink.setId("XXMMDD");

        // An entity with an existing ID cannot be created, so this API call must fail
        restShortLinkMockMvc.perform(post("/api/short-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shortLink)))
            .andExpect(status().isBadRequest());

        // Validate the ShortLink in the database
        List<ShortLink> shortLinkList = shortLinkRepository.findAll();
        assertThat(shortLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = shortLinkRepository.findAll().size();
        // set the field null
        shortLink.setUrl(null);

        // Create the ShortLink, which fails.

        restShortLinkMockMvc.perform(post("/api/short-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shortLink)))
            .andExpect(status().isBadRequest());

        List<ShortLink> shortLinkList = shortLinkRepository.findAll();
        assertThat(shortLinkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllShortLinks() throws Exception {
        // Initialize the database
        shortLink.setId("XXDDMM");
        shortLinkRepository.save(shortLink);

        // Get all the shortLinkList
        restShortLinkMockMvc.perform(get("/api/short-links"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shortLink.getId().toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)));
    }
    
    @Test
    public void getShortLink() throws Exception {
        // Initialize the database
        shortLink.setId("XXDDMM");
        shortLinkRepository.save(shortLink);

        // Get the shortLink
        restShortLinkMockMvc.perform(get("/api/short-links/{id}", shortLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shortLink.getId().toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL));
    }
    @Test
    public void getNonExistingShortLink() throws Exception {
        // Get the shortLink
        restShortLinkMockMvc.perform(get("/api/short-links/{id}", UUID.randomUUID().toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateShortLink() throws Exception {
        // Initialize the database
        shortLink.setId("XXDDMM");
        shortLinkService.save(shortLink);

        int databaseSizeBeforeUpdate = shortLinkRepository.findAll().size();

        // Update the shortLink
        ShortLink updatedShortLink = shortLinkRepository.findById(shortLink.getId()).get();
        updatedShortLink.setUrl(UPDATED_URL);

        restShortLinkMockMvc.perform(put("/api/short-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedShortLink)))
            .andExpect(status().isOk());

        // Validate the ShortLink in the database
        List<ShortLink> shortLinkList = shortLinkRepository.findAll();
        assertThat(shortLinkList).hasSize(databaseSizeBeforeUpdate);
        ShortLink testShortLink = shortLinkList.get(shortLinkList.size() - 1);
        assertThat(testShortLink.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    public void updateNonExistingShortLink() throws Exception {
        int databaseSizeBeforeUpdate = shortLinkRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShortLinkMockMvc.perform(put("/api/short-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shortLink)))
            .andExpect(status().isBadRequest());

        // Validate the ShortLink in the database
        List<ShortLink> shortLinkList = shortLinkRepository.findAll();
        assertThat(shortLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteShortLink() throws Exception {
        // Initialize the database
        shortLink.setId("XXDDMM");
        shortLinkService.save(shortLink);

        int databaseSizeBeforeDelete = shortLinkRepository.findAll().size();

        // Delete the shortLink
        restShortLinkMockMvc.perform(delete("/api/short-links/{id}", shortLink.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShortLink> shortLinkList = shortLinkRepository.findAll();
        assertThat(shortLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
