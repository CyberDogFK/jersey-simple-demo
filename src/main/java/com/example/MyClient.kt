package com.example

import kotlinx.coroutines.future.await
import java.util.concurrent.CompletionException
import java.util.concurrent.CompletionStage
import javax.ws.rs.client.ClientBuilder

class MyClient {
    suspend fun demo(): String {
        val client = ClientBuilder.newClient()
        val target = client.target("https://google.com")
        val result = target.request()
            .rx()
            .get(String::class.java)
            .exceptionally {
                "Wrong!"
            }

        return result.await()
    }
}