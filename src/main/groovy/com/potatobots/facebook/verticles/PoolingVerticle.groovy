package com.potatobots.facebook.verticles

import com.potatobots.facebook.config.Env
import com.potatobots.facebook.pooling.handlers.PoolingHandler
import groovy.json.JsonSlurper
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpClient
import io.vertx.core.http.RequestOptions
import org.apache.logging.log4j.LogManager

class PoolingVerticle extends AbstractVerticle {

    static final LOGGER = LogManager.getLogger PoolingVerticle

    @Override
    void start(Future future) {
        LOGGER.info 'Starting verticle'

        HttpClient httpClient = vertx.createHttpClient()
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
            response.bodyHandler { bodyJson ->
                def body = new JsonSlurper().parseText(bodyJson.toString())
                LOGGER.info "$body"
            }
        }

        vertx.setPeriodic(Env.poolingInterval(), PoolingHandler.handle)
    }

}
