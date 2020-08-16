/*
 * Copyright DataStax, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.meli.shortlink.server.repository;

import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * A Quarkus component that creates DAOs and make them available as container beans for injection.
 */
public class ShortLinkDaoProducer {

    private final ShortLinkDao shortLinkDao;

    private final ShortLinkDaoReactive shortLinkDaoReactive;


    @Inject
    public ShortLinkDaoProducer(QuarkusCqlSession session) {
        ShortLinkMapper mapper = new ShortLinkMapperBuilder(session).build();
        shortLinkDao = mapper.shortLinkDao();
        shortLinkDaoReactive = mapper.shortLinkDaoReactive();
    }


    @Produces
    @ApplicationScoped
    public ShortLinkDao produceShortLinkDao() {
        return shortLinkDao;
    }

    @Produces
    @ApplicationScoped
    public ShortLinkDaoReactive produceShortLinkDaoReactive() {
        return shortLinkDaoReactive;
    }

}
