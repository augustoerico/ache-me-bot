package com.potatobots.facebook

import io.vertx.core.Vertx
import org.apache.logging.log4j.LogManager

class Application {

    static final LOGGER = LogManager.getLogger Application

    static main(args) {
        Vertx vertx = Vertx.vertx()
        vertx.with {
            deployVerticle(ServerVerticle.name) {
                LOGGER.info 'Server verticle deployed'
            }
        }
    }

}
