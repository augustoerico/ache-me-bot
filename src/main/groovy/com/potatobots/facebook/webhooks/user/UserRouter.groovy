package com.potatobots.facebook.webhooks.user

import com.potatobots.facebook.webhooks.user.handlers.GetHandler
import com.potatobots.facebook.webhooks.user.handlers.PostHandler
import io.vertx.ext.web.Router

class WebhookUserRouter {

    static WEBHOOK_PATH = '/webhooks/user'

    Router router

    WebhookUserRouter(Router router) {
        this.router = router
    }

    def route() {
        router.get(WEBHOOK_PATH).handler GetHandler.handle
        router.post(WEBHOOK_PATH).handler PostHandler.handle
    }

    static create(Router router) {
        new WebhookUserRouter(router)
    }

}
