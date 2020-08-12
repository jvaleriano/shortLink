package com.meli.shortlink.api;

import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ApiApplication.class)
class ApiApplicationTests extends AbstractCassandraTest {

	@Autowired
	private Session session;

	@Test
	public void shouldListCassandraUnitKeyspace() throws Exception {
		Metadata metadata = session.getCluster().getMetadata();
		assertThat(metadata.getKeyspace(CASSANDRA_UNIT_KEYSPACE)).isNotNull();
	}

}
