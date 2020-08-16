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
package com.meli.shortlink.server.controller;

import com.meli.shortlink.server.service.ShortLinkReactiveService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/testr")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShortLinkReactiveController {

  @Inject
  ShortLinkReactiveService fruitService;

  @GET
  @Path("/{id}")
  public void getAll(@PathParam String id) {
    System.out.println("search: "+id );
    fruitService.get(id).subscribe().with(
            item -> {
              try {
                  System.out.println(item.getUrl());
                Response.seeOther( new URI(item.getUrl())).build();
              } catch (URISyntaxException e) {
                e.printStackTrace();
              }
            },
            Throwable::printStackTrace);

  }

}
