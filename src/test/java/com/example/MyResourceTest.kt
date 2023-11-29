package com.example;

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget

import org.glassfish.grizzly.http.server.HttpServer

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class MyResourceTest {

    private lateinit var server: HttpServer
    private lateinit var target: WebTarget

    @BeforeEach
    fun setUp() {
        // start the server
        server = Main.startServer()
        // create the client
        val c: Client = ClientBuilder.newClient()

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI)
    }

    @AfterEach
    public fun tearDown() {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    fun testGetIt() {
        val responseMsg: String = target.path("myresource").request().get(String::class.java)
        assertEquals("Got it!", responseMsg)
    }
}
