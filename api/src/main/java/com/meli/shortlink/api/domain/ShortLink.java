package com.meli.shortlink.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

/**
 * A ShortLink.
 */
@Table("shortLink")
public class ShortLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    private String shortUrl;

    private String url;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public ShortLink shortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
        return this;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getUrl() {
        return url;
    }

    public ShortLink url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShortLink)) {
            return false;
        }
        return id != null && id.equals(((ShortLink) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShortLink{" +
            "id=" + getId() +
            ", shortUrl='" + getShortUrl() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
