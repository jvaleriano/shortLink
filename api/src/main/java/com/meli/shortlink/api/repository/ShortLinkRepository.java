package com.meli.shortlink.api.repository;

import com.meli.shortlink.api.domain.ShortLink;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data Cassandra repository for the ShortLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShortLinkRepository extends CassandraRepository<ShortLink, String> {
}
