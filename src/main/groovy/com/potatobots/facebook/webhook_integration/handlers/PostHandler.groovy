package com.potatobots.facebook.webhook_integration.handlers

import com.potatobots.facebook.config.Env
import io.vertx.ext.web.RoutingContext
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class PostHandler {

    static final Logger LOGGER = LogManager.getLogger PostHandler

    static handle = { RoutingContext context ->
        LOGGER.info "[POST] ${context.normalisedPath()}"
        def body = context.getBodyAsJson().map
        LOGGER.info "body=$body"
        def response = context.response()
        if (body.'hub.verify_token' == Env.facebookWebhookToken()) {
            response.putHeader('content-type', 'application/json')
                    .end(body.'hub.challenge' as String)
        } else {
            response.setStatusCode(400).end()
        }
    }
}
