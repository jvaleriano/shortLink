package com.meli.shortlink.api.config;

import com.meli.shortlink.api.AbstractCassandraTest;
import com.meli.shortlink.api.config.cassandra.CassandraConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class CassandraConfigurationIT extends CassandraConfiguration {

    /**
     * Override how to get the port to connect to the Cassandra cluster.
     * <p>
     * This uses the TestContainers API to get the mapped port in Docker.
     */
    @Override
    protected int getPort(CassandraProperties properties) {
        return AbstractCassandraTest.CASSANDRA_CONTAINER.getMappedPort(AbstractCassandraTest.CASSANDRA_TEST_PORT);
    }
}
