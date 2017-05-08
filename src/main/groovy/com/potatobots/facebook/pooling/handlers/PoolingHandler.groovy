package com.potatobots.facebook.pooling.handlers

import com.potatobots.facebook.config.Env
import groovy.json.JsonSlurper
import io.vertx.core.Handler
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpClient
import io.vertx.core.http.RequestOptions
import org.apache.logging.log4j.LogManager

class PoolingHandler {

    static final LOGGER = LogManager.getLogger PoolingHandler

    HttpClient httpClient
    String accessToken

    PoolingHandler(HttpClient httpClient, String accessToken) {
        this.httpClient = httpClient
        this.accessToken = accessToken
    }

    Handler handle = {
        LOGGER.info 'Handling interval call'

        RequestOptions options = new RequestOptions([
                host: 'graph.facebook.com',
                port: 443,
                ssl: true,
                uri: "/v2.9/${Env.facebookGroupId()}/feed" +
                        "?access_token=$accessToken"
        ])
        httpClient.getNow(options) { response ->
            response.bodyHandler(bodyHandler)
        }

        LOGGER.info 'Done'
    }

    def bodyHandler = { Buffer buffer ->
        def body = new JsonSlurper().parseText(buffer.toString())
    }

}
