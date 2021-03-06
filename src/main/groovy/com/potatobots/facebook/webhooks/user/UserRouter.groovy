package com.potatobots.facebook.webhooks.user

import com.potatobots.facebook.webhooks.GetHandler
import io.vertx.ext.web.Router

class UserRouter {

    static WEBHOOK_PATH = '/webhooks/user'

    Router router

    UserRouter(Router router) {
        this.router = router
    }

    def route() {
        router.get(WEBHOOK_PATH).handler GetHandler.handle
        router.post(WEBHOOK_PATH).handler PostHandler.handle
    }

    static create(Router router) {
        new UserRouter(router)
    }

}
