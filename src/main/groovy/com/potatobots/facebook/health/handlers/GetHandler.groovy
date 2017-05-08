package com.potatobots.facebook.health.handlers

import io.vertx.ext.web.RoutingContext

class GetHandler {

    static handle = { RoutingContext context ->
        def response = context.response()
        response.putHeader('content-type', 'application/json')
                .end("{\"status\":\"OK\",\"checkedAt\":\"${new Date()}\"}")
    }
}
