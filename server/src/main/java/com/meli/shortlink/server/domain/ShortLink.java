package com.meli.shortlink.server.domain;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

/**
 * A ShortLink.
 */
@Entity()
public class ShortLink {

    @PartitionKey
    private String id;

    private String url;

    public ShortLink() {
    }

    public ShortLink(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
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
        int result = id.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
