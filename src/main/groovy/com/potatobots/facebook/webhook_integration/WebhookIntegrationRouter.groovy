package com.potatobots.facebook.webhook_integration

import com.potatobots.facebook.webhook_integration.handlers.GetHandler
import com.potatobots.facebook.webhook_integration.handlers.PostHandler
import io.vertx.ext.web.Router

class WebhookIntegrationRouter {

    Router router

    WebhookIntegrationRouter(Router router) {
        this.router = router
    }

    def route() {
        router.get('/webhook').handler GetHandler.handle
        router.post('/webhook').handler(PostHandler.handle)
    }

    static create(Router router) {
        new WebhookIntegrationRouter(router)
    }

}
