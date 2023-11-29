package com.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig

import java.io.IOException
import java.net.URI

/**
 * Main class.
 *
 */
class Main {
    // Base URI the Grizzly HTTP server will listen on
    companion object {
        @JvmStatic
        val BASE_URI: String = "http://localhost:8080/";

        /**
         * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
         * @return Grizzly HTTP server.
         */
        @JvmStatic
        fun startServer(): HttpServer {
            // create a resource config that scans for JAX-RS resources and providers
            // in com.example package
            val rc: ResourceConfig = ResourceConfig().packages("com.example")

            // create and start a new instance of grizzly http server
            // exposing the Jersey application at BASE_URI
            return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc)
        }

        /**
         * Main method.
         * @param args
         * @throws IOException
         */
        @JvmStatic
        @Throws(IOException::class)
        fun main(args: Array<String>) = runBlocking {
            val server: HttpServer = startServer()
            println(
                String.format(
                    "Jersey app started with endpoints available at "
                            + "%s%nHit Ctrl-C to stop it...", BASE_URI
                )
            )
            val result = MyClient()
                .demo()
            coroutineScope {
                val job = launch {
                    delay(1000L)
                    println("Some")
                }
            }

            println(result)
            System.`in`.read()
            server.stop();
        }
    }
}
