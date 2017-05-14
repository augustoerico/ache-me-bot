package com.potatobots.facebook.webhooks.group

import com.potatobots.facebook.webhooks.GetHandler
import io.vertx.ext.web.Router

class GroupRouter {

    static final WEBHOOK_PATH = '/webhooks/group'

    Router router

    GroupRouter(Router router) {
        this.router = router
    }

    def route() {
        router.get(WEBHOOK_PATH).handler GetHandler.handle
    }

    static create(Router router) {
        new GroupRouter(router)
    }

}
