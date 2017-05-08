package com.potatobots.facebook.webhook_integration.handlers

import com.potatobots.facebook.config.Env
import io.vertx.ext.web.RoutingContext

class GetHandler {

    static handle = { RoutingContext context ->
        def response = context.response()
        response.putHeader('content-type', 'application/json')
                .end(Env.facebookWebhookToken())
    }

}
