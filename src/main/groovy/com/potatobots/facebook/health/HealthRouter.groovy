package com.potatobots.facebook.health

import com.potatobots.facebook.health.handlers.GetHandler
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router

class HealthRouter {

    Router router

    HealthRouter(Router router) {
        this.router = router
    }

    def route() {
        router.get('/health').handler GetHandler.handle
    }

    static create(Router router) {
        new HealthRouter(router)
    }

}
