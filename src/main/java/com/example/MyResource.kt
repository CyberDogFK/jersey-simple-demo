package com.example;

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun getIt(): String {
        return "Got it!";
    }

    @PUT
    @Path("put")
    @Produces("text/html")
    @Consumes("text/plain")
    fun putIt(message: String): Response {
        return Response.ok("<h1>Hello $message</h1>").build()
    }

    @GET
    @Path("smooth")
    @Produces("text/html")
    fun parametrizeMe(@QueryParam("step") step: Int): Response {
        return Response.ok("<p>Win step is $step").build()
    }
}
