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
import java.util.Objects;

@Path("/")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @Inject
    public ShortLinkController(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @GET
    @Path("/{id}")
    public Response getAll(@PathParam String id) throws URISyntaxException {
        ShortLink shortLink = shortLinkService.get(id);
        if(Objects.isNull(shortLink)){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.seeOther(new URI(shortLink.getUrl())).build();
    }

}
