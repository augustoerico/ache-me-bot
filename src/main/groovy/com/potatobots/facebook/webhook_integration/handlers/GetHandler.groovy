package com.potatobots.facebook.webhook_integration.handlers

import io.vertx.ext.web.RoutingContext

class GetHandler {

    static handle = { RoutingContext context ->
        def response = context.response()
        response.putHeader('content-type', 'application/json')
                .end("{\"status\":\"OK - GET\",\"checkedAt\":\"${new Date()}\"}")
    }

}
