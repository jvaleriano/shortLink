package com.meli.shortlink.server.controller;

import com.meli.shortlink.server.domain.ShortLink;
import com.meli.shortlink.server.service.ShortLinkService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/")
public class ShortLinkController {

    @Inject
    private ShortLinkService shortLinkService;

    @GET
    @Path("/{id}")
    public Response getAll(@PathParam String id) throws URISyntaxException {
        ShortLink list = shortLinkService.get(id);
        return Response.seeOther(new URI(list.getUrl())).build();
    }

}
