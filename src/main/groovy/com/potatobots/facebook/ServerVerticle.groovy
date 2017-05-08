package com.potatobots.facebook

import com.potatobots.facebook.config.Env
import com.potatobots.facebook.health.HealthRouter
import com.potatobots.facebook.verticles.PoolingVerticle
import io.vertx.core.AbstractVerticle
import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.CorsHandler
import org.apache.logging.log4j.LogManager

class ServerVerticle extends AbstractVerticle {

    static final LOGGER = LogManager.getLogger ServerVerticle

    @Override
    void start(Future future) {
        LOGGER.info 'Starting verticle'

        Router router = Router.router(vertx)

        def cors = CorsHandler.create('*')
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedHeader('Content-Type')

        router.route().handler(cors)

        HealthRouter.create(router).route()

        vertx.deployVerticle(PoolingVerticle.name)

        vertx.createHttpServer()
                .requestHandler(router.&accept)
                .listen(Env.port(), Env.host(), handleResult.curry(future))
    }

    def handleResult = { Future future, AsyncResult result ->
        if (result.succeeded()) {
            LOGGER.info 'Done'
            LOGGER.info "Server running on http://${Env.host()}:${Env.port()}"
            future.complete()
        } else {
            def ex = result.cause()
            LOGGER.error(ex.message, ex)
            future.fail(ex)
        }
    }

}
