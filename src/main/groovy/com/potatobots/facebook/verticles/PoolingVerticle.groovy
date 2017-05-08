package com.potatobots.facebook.verticles

import com.potatobots.facebook.config.Env
import com.potatobots.facebook.pooling.handlers.PoolingHandler
import groovy.json.JsonSlurper
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.HttpClient
import io.vertx.core.http.RequestOptions
import org.apache.logging.log4j.LogManager

class PoolingVerticle extends AbstractVerticle {

    static final LOGGER = LogManager.getLogger PoolingVerticle

    HttpClient httpClient

    @Override
    void start(Future future) {
        LOGGER.info 'Starting verticle'

        httpClient = vertx.createHttpClient()
        RequestOptions options = new RequestOptions([
                host: 'graph.facebook.com',
                port: 443,
                ssl : true,
                uri : "/oauth/access_token" +
                        "?client_id=${Env.facebookAppId()}" +
                        "&client_secret=${Env.facebookAppSecret()}" +
                        "&grant_type=client_credentials"
        ])

        httpClient.getNow(options) { response ->
            response.bodyHandler(bodyHandler.curry(future))
        }
    }

    def bodyHandler = { Future future, Buffer buffer ->
        def body = new JsonSlurper().parseText(buffer.toString())

        if (body.access_token) {
            LOGGER.info 'Got Facebook access token'
            vertx.setPeriodic Env.poolingInterval(),
                    new PoolingHandler(httpClient, body.access_token as String).handle
            LOGGER.info 'Done'
            future.complete()
        } else if (body.error) {
            def ex = new RuntimeException(body.error as String)
            LOGGER.error 'Error while getting Facebook access token', ex
            future.fail ex
        } else {
            def ex = new RuntimeException('Unknown error while getting Facebook access token')
            future.fail ex
        }
    }

}
