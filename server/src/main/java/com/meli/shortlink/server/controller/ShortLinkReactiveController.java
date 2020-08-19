package com.meli.shortlink.server.controller;

import com.meli.shortlink.server.service.ShortLinkReactiveService;
import io.smallrye.mutiny.Multi;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

// TODO: finish reactive impl
public class ShortLinkReactiveController {

    private final ShortLinkReactiveService shortLinkService;

    @Inject
    public ShortLinkReactiveController(ShortLinkReactiveService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @GET
    @Path("/{id}")
    public Publisher redirect(@PathParam String id) {
        System.out.println("search: " + id);
        return Multi.createFrom().publisher(shortLinkService.get(id)).map(
                item -> {
                    try {
                        System.out.println(item.getUrl());
                        return Response.seeOther(new URI(item.getUrl())).build();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

}
